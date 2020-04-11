#!/bin/bash
URL="http://localhost:$1/api/SlimShady"

if [ $# -eq 0 ]
  then
    echo "No arguments supplied. Provide PORT number!"
    exit
fi

echo  "Using URL: $URL"


curl $URL
