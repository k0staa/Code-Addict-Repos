#!/bin/bash

docker build -f src/main/docker/Dockerfile.native.multistage -t quarkus_simple_api_native .