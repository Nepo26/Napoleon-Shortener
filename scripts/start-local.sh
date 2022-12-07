#!/bin/bash
#- Start Localstack
#- Run Terraform and initialize infrastucture
#- Start Application

CURRENT_DIR=$(pwd)

cd ..

PROJECT_ROOT=$(pwd)

# Go to local-environment and start Localstack

cd "$PROJECT_ROOT/local-environment/" || exit
docker compose up -d

cd "$CURRENT_DIR" || exit

# Wait Localstack to be healthy
while [ "$( docker inspect -f {{.State.Health.Status}} $container_id )" != "healthy" ]; do
  sleep 2;
done

# Set up database
cd "$PROJECT_ROOT/infra/terraform/localstack/database" || exit
terraform apply -y

# Start Application
cd "$PROJECT_ROOT"
docker build . --file Localstack.dockerfile --tag napoleon:latest
docker run -d -p 4215:4215 --name napoleon napoleon:latest


