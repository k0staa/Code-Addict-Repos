#!/bin/bash
source ./get_user_token.sh

echo "Using acccess token: $ACCESS_TOKEN"

curl -H "Authorization: Bearer $ACCESS_TOKEN"  http://localhost:8080/secured 
