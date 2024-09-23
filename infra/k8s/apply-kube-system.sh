#!/bin/bash

# Set the base directory for the manifests and Helm values
SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
BASE_DIR="$SCRIPT_DIR/kube-system"

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

# Deploy Metrics Server using Helm
echo -e "\nInstalling Metrics Server via Helm..."
helm repo add metrics-server https://kubernetes-sigs.github.io/metrics-server/
helm repo update

helm upgrade --install metrics-server metrics-server/metrics-server \
  --namespace kube-system \
  --version 3.12.1 \
  --values "$BASE_DIR/values.yaml" \
  --wait \
  --timeout 600s

echo -e "\nMetrics Server deployed successfully."
