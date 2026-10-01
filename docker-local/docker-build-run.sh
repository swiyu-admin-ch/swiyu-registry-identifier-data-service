#!/usr/bin/env bash

# This yml file is used to test the Dockerfile locally. see Readme for more details.

set -euo pipefail

IMAGE="swiyu-registry-identifier-data-service:local"
SECRETS_FILE="docker-local/database-secret-credentials.yml"

cd ..

echo "==> Building jar..."
./mvnw clean package -DskipTests

echo "==> Building Docker image..."
docker build -t "$IMAGE" .

# Postgres (identifier-registry-db) runs on this network from the swiyu-service-manager
NETWORK="swiyu-core-business-service_default"

echo "==> Running container on port 8190 -> 8080 (Ctrl+C to stop)..."
docker run --rm \
  --network "$NETWORK" \
  -p 8190:8080 \
  -v "$(pwd)/$SECRETS_FILE:/vault/secrets/database-credentials.yml:ro" \
  "$IMAGE"
