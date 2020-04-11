# Quarkus VS Spring Boot - quick comparison of the speed of two technologies 

Aim of the project is to show speed comparison beetween Spring Boot application and Quarkus JVM and Native application. Whole idea is described in my blog 
post [Code-Addict](hhttp://code-addict.pl/quarkus-vs-spring/)

## Functionalities:
For the purpose of speed tests, two identical applications were created in Spring Boot and Quarkus and using the Kotlin language.
Both applications use the H2 database but database is placed on a separate docker container.

## Dependencies
Project is very simple and uses following dependencies:
1. Spring Boot application:
 - Spring Boot 2.2.4
 - Spring Boot Web starter
 - Spring Boot Data JDBC starter
 - Rest-Assured (simple IT tests)
2. Quarkus application:
 - Quarkus 1.3.1.Final
 - Rest-Assured (simple IT tests)
3. Common:
 - Kotlin
 - H2 (as separate docker container)
 - Project is configured to use JDK 11 but you can probably change that (`build.gradle.kts`) to different JDK.

## Running
1. Quarkus app:
You can run quarkus app in JVM or as Native application.
- In JVM (dev mode) - just run `./gradlew quarkusDev`
- In JVM (JAR) - you can build JAR `./gradlew build` and run from it `java -jar ./build/QuarkusSimpleAPI-1.0.0-SNAPSHOT-runner.jar`
- Native (using Docker) - I prepared docker configuration in order to simplify build process. You can use `./build_native.sh` script in project dir and then `./run_native.sh`.
- Native , you need to install GrallVM and run `./gradlew clean build -Dquarkus.package.type=native` and you can run `QuarkusSimpleAPI-1.0.0-SNAPSHOT-runner` as normal application.
2. Srping Boot app:
You can simply run `./gradlew bootRun` or build JAR using `./gradlew build` command and then run it using: `java -jar ./build/SpringSimpleAPI-1.0.0-SNAPSHOT.jar`

## Licence

Project uses a MIT licence .

MIT License

Copyright (c) 2020 Michal Kostewicz

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
