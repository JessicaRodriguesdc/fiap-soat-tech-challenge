#!/bin/bash

# Set the base directory for the manifests
SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
BASE_DIR="$SCRIPT_DIR/techchallenge"

if ! command -v kubectl &> /dev/null
then
    echo "Stoping the script! The command kubectl could not be found"
    exit 1
fi

# Apply the namespace first
echo -e "\nApplying namespace..."
kubectl apply -f "$BASE_DIR/namespace.yaml"

TIMEOUT=15
START_TIME=$(date +%s)

# Wait until the namespace is available before proceeding
echo "Waiting for namespace to be in Active status..."
while [[ $(kubectl get namespace techchallenge -o jsonpath='{.status.phase}') != "Active" ]]; do
    CURRENT_TIME=$(date +%s)
    ELAPSED_TIME=$(( CURRENT_TIME - START_TIME ))

    if [[ $ELAPSED_TIME -ge $TIMEOUT ]]; then
        echo "Timeout reached: Namespace techchallenge did not become Active within $TIMEOUT seconds."
        exit 1
    fi
    echo "Namespace not yet Active..."
    sleep 1
done

# Apply secrets after the namespace
echo -e "\nApplying secrets..."
for secret in "$BASE_DIR/secret"/*.yaml; do
  echo "Applying $secret..."
  kubectl apply -f "$secret"
done

# Apply storage class
echo -e "\nApplying StorageClass..."
for storageclass in "$BASE_DIR/storageclass"/*.yaml; do
  echo "Applying $storageclass..."
  kubectl apply -f "$storageclass"
done

# Apply persistent volume
echo -e "\nApplying PV and PVC..."
for persistentvolume in "$BASE_DIR/persistentvolume"/*.yaml; do
  echo "Applying $persistentvolume..."
  kubectl apply -f "$persistentvolume"
done

# Apply stateful set
echo -e "\nApplying statefulset..."
for sts in "$BASE_DIR/statefulset"/*.yaml; do
  echo "Applying $sts..."
  kubectl apply -f "$sts"
done

# Apply deployments after the namespace and secrets
echo -e "\nApplying deployments..."
for deployment in "$BASE_DIR/deployment"/*.yaml; do
  echo "Applying $deployment..."
  kubectl apply -f "$deployment"
done

# Apply services after the namespace and deployments
echo -e "\nApplying services..."
for service in "$BASE_DIR/service"/*.yaml; do
  echo "Applying $service..."
  kubectl apply -f "$service"
done

# Apply ingress after namespace and deployments
echo -e "\nApplying ingress..."
for ingress in "$BASE_DIR/ingress"/*.yaml; do
  echo "Applying $ingress..."
  kubectl apply -f "$ingress"
done

# Apply HPA after namespace and services
echo -e "\nApplying HPA (Horizontal Pod Autoscaler)..."
for hpa in "$BASE_DIR/hpa"/*.yaml; do
  echo "Applying $hpa..."
  kubectl apply -f "$hpa"
done

echo -e "\nAll resources applied successfully."