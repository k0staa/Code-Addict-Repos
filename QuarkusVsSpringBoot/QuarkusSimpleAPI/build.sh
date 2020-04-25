#!/bin/bash

docker build -f src/main/docker/Dockerfile.multistage -t app-quarkus-jvm .
