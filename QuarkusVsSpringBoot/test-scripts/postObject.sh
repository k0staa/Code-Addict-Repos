#!/bin/bash

URL="http://localhost:$1/api"

if [ $# -eq 0 ]
  then
    echo "No arguments supplied. Provide PORT number!"
    exit
fi

echo  "Using URL: $URL"
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"data":"SlimShady"}' \
  $URL
