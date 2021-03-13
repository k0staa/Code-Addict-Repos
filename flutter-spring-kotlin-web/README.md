# Flutter + Kotlin WebFlux API + JWT Keycloak Demo

Aim of the project is to show few technologies. The aim of this project is to show several technologies and their configuration:
 - Kotlin API with Spring WebFlux and enabled security. Api will work as Oauth2 resource server.
 - Flutter for Web GUI with authentication
 - Keycloak used as an authentication server

Whole idea and details are described in my blog post [Code-Addict](http://code-addict.pl/flutter-spring-kotlin-web/)

## Functionalities:
The GUI application authenticates in the Keycloak and receives the JWT token used in the API application for authorization.

## Dependencies
Project uses following dependencies:
 - Spring Boot 2.4.2
 - Spring WebFlux
 - Kotlin 1.4.21
 - Keycloak 12.0.3
 - Flutter 2

## Running
1. API application - please run gradle command to build  docker image :
```
cd kotlin-api
./gradlew jibDockerBuild`
```
2. GUI application
To run Flutter application you can simply use `flutter run` command with Flutter SDK installed on your machine. For details please check my blog post.
You can also build docker container with application using below command:
```
docker build -t flutter-kotlin-gui .
```

3. Now you can start all docker containers using docker-compose command:
`docker-compose up`
Go to `http://localhost` and see how it works.

## Licence

Project uses a MIT licence .

MIT License

Copyright (c) 2021 Michal Kostewicz

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
