#!/bin/bash
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"data":"SlimShady"}' \
  http://localhost:8080/api
