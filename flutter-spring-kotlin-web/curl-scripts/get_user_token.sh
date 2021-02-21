#!/bin/bash

TOKEN_RESPONSE=$(curl -d "client_id=login-app&username=user&password=password&grant_type=password" -H "Content-Type: application/x-www-form-urlencoded" -X POST http://localhost:8081/auth/realms/kotlin-flutter-demo-realm/protocol/openid-connect/token)

echo $TOKEN_RESPONSE

ACCESS_TOKEN=$(echo $TOKEN_RESPONSE | jq -j '.access_token')

echo "In order to use ACCESS_TOKEN in rest of the script please run this script using '. ./get_user_token.sh'"

