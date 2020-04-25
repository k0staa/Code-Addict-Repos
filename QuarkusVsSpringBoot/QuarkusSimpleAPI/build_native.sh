#!/bin/bash

docker build -f src/main/docker/Dockerfile.native.multistage -t app-quarkus-native .
