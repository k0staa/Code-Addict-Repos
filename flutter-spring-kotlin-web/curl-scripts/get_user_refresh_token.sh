#!/bin/bash

REFRESH_TOKEN=token

curl -d "client_id=login-app&refresh_token=$REFRESH_TOKEN&grant_type=refresh_token" -H "Content-Type: application/x-www-form-urlencoded" -X POST http://localhost:8081/auth/realms/kotlin-flutter-demo-realm/protocol/openid-connect/token 
