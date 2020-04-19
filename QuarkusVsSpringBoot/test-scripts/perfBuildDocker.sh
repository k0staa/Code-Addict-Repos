#!/bin/bash
docker network create quarkus-vs-spring-net
docker build -t quarkus-vs-spring-wrk .
