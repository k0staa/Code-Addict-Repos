# Siddhi - Demo - Simple stream analyzing application 

Aim of the project is to show how to analyze stream of data using Siddhi and Spring Boot 2 application. Whole idea is described in my blog 
post [Code-Addict](http://code-addict.pl/siddhi-anylze-stream/)

## Functionalities:
There are two controllers implemented in application. First should be used to write some text data which will be send
to RabbitMQ queue (analyzed by Siddhi) and second is just used for streaming potential alarms from the queue where Siddhi writes.

## Dependencies
Project is very simple and uses following dependencies:
 - Spring Boot 2.2.4
 - Spring WebFlux
 - Kotlin
 - RabbitMQ (as separate docker container)
 - Siddhi (as separate docker container)

## Running
First please run gradle build command with docker image build task:
`./gradlew distDocker`
Now you can start docker by run simple docker-compose command:
`cd docker && docker-compose up`
Open `localhost:8080/alarms/stream` and `localhost:8080/message.html` in web browser 
and write something in  `localhost:8080/message.html` text area. If you write alarm five times in 30 seconds time window you should
see alarm message in alarms stream. 

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
