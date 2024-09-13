#!/bin/bash

# Set the base directory for the manifests and Helm values
SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
BASE_DIR="$SCRIPT_DIR/monitoring"

if ! command -v kubectl &> /dev/null
then
    echo "Stoping the script! The command kubectl could not be found"
    exit 1
fi

if ! command -v helm &> /dev/null
then
    echo "Stoping the script! The command helm could not be found"
    exit 1
fi

# Create the monitoring namespace
echo "Applying monitoring namespace..."
kubectl apply -f "$BASE_DIR/namespace.yaml"

# Wait until the namespace is available before proceeding
echo "Waiting for namespace to be in Active status..."
while [[ $(kubectl get namespace monitoring -o jsonpath='{.status.phase}') != "Active" ]]; do
    CURRENT_TIME=$(date +%s)
    ELAPSED_TIME=$(( CURRENT_TIME - START_TIME ))

    if [[ $ELAPSED_TIME -ge $TIMEOUT ]]; then
        echo "Timeout reached: Namespace monitoring did not become Active within $TIMEOUT seconds."
        exit 1
    fi
    echo "Namespace not yet Active..."
    sleep 1
done

# Deploy kube-prometheus-stack using Helm
echo "Installing kube-prometheus-stack via Helm..."
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo update

helm upgrade --install kube-prometheus-stack prometheus-community/kube-prometheus-stack \
  --namespace monitoring \
  --version 51.2.0 \
  --values "$BASE_DIR/kube-prometheus-stack/values.yaml" \
  --wait \
  --timeout 600s

# Apply the Grafana ConfigMap
echo "Applying Grafana ConfigMap..."
kubectl apply -f "$BASE_DIR/grafana/configmap.yaml"

# Wait for the ConfigMap to be available
echo "Waiting for Grafana ConfigMap to be applied..."
kubectl get configmap grafana-config -n monitoring --timeout=60s

# Deploy Grafana using Helm
echo "Installing Grafana via Helm..."
helm repo add grafana https://grafana.github.io/helm-charts
helm repo update

helm upgrade --install grafana grafana/grafana \
  --namespace monitoring \
  --version 8.5.1 \
  --values "$BASE_DIR/grafana/values.yaml" \
  --wait \
  --timeout 600s

echo "All resources applied successfully."