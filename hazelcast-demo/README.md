# Hazelcast Demo

Aim of the project is to show how to use Hazelcast in Spring Boot 2 application. Whole idea is described in my blog 
post [Code-Addict](http://code-addict.pl/hazelcast-distributed-cache/)

## Functionalities:
There are two applications run in separate Docker containers. One is writing to Hazelcast map, and second is reading 
from it 
and stream to 
web 
controller.

## Dependencies
Project is very simple and uses following dependencies:
 - Spring Boot 2.1.1
 - Hazelcast
 - Spring WebFlux
 - Kotlin

## Running
First please run gradle build command with docker image build task:
`./gradlew build appDocker`
Now you can start docker by run simple docker-compose command:
`cd docker && docker-compose up`
Go to `localhost:8080/hazelcast/stream` and see how it works.

## Licence

Project uses a MIT licence .

MIT License

Copyright (c) 2018 Michal Kostewicz

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
