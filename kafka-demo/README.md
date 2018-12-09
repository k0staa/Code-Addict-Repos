# Kafka Streams Demo

Aim of the project is to show how to process streams of data using Kafka Streams in Spring Boot 2 application. Whole idea is described in my blog post [Code-Addict](http://code-addict.pl/kafka-simple-fraud-detection/)

## Functionalities:

- Read from streams of data and search for certain suspicious entry in time window
- Saving data to MongoDB
- Read in stream data from MongoDB using Spring WebFlux and Server-Sent events

## Dependencies
Project is very simple and uses following dependencies:
 - Spring Boot 2.0
 - Kafka Streams
 - Kafka reactor
 - Spring WebFlux
 - Kotlin
 - Spring MongoDB Reactive

## Running

First please run Kafka and MongoDB using `docker-compose up` command in `docker` directory.  You can run application using gradle `./gradlew build` and `./gradlew bootRun` commands. Please see blog post and configure the appropriate program parameters.


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
