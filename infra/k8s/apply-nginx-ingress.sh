#!/bin/bash

# Set the base directory for the manifests and Helm values
SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
BASE_DIR="$SCRIPT_DIR/nginx-ingress"

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

# Create the ingress-nginx namespace
echo "Applying ingress-nginx namespace..."
kubectl apply -f "$BASE_DIR/namespace.yaml"

TIMEOUT=15
START_TIME=$(date +%s)

# Wait until the namespace is available before proceeding
echo "Waiting for namespace to be in Active status..."
while [[ $(kubectl get namespace ingress-nginx -o jsonpath='{.status.phase}') != "Active" ]]; do
    CURRENT_TIME=$(date +%s)
    ELAPSED_TIME=$(( CURRENT_TIME - START_TIME ))

    if [[ $ELAPSED_TIME -ge $TIMEOUT ]]; then
        echo "Timeout reached: Namespace ingress-nginx did not become Active within $TIMEOUT seconds."
        exit 1
    fi
    echo "Namespace not yet Active..."
    sleep 1
done

# Deploy NGINX Ingress Controller using Helm
echo "Installing NGINX Ingress Controller via Helm..."
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update

helm upgrade --install nginx-ingress ingress-nginx/ingress-nginx \
  --namespace ingress-nginx \
  --version 4.8.1 \
  --values "$BASE_DIR/values.yaml" \
  --set controller.service.type=LoadBalancer \
  --set controller.publishService.enabled=true \
  --wait \
  --timeout 600s

echo "NGINX Ingress Controller deployed successfully."
