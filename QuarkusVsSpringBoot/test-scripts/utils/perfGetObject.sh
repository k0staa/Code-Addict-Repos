#!/bin/bash
URL="http://$1/api/SlimShady"

if [ $# -eq 0 ]
  then
	  echo "No arguments supplied. Provide URL (container name) with PORT number!"
    exit
fi

echo  "Running wrk with 2 threads and 11 connections over period of 10 seconds. Using URL: $URL"
echo "If you can't connect remember to use docker containers internal port numbers for example quarkus-vs-spring-app-quarkus-native:8080"

docker run --rm -v `pwd`:/scripts -w /scripts --net quarkus-vs-spring-net quarkus-vs-spring-wrk:latest -t2 -c10 -d10s -s ./utils/wrk-get.lua $URL
