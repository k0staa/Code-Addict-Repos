# Apache Camel with Spring Boot 2.1 Demo app

Aim of the project is to show how to use Apache Camel with SQL component in Spring Boot 2 application. Whole idea is described in my blog 
post [Code-Addict](http://code-addict.pl/hazelcast-distributed-cache/)

## Functionalities:
The application launches the camel route which in a given time interval downloads data from the database.

## Dependencies
Project is very simple and uses following dependencies:
 - Spring Boot 2.1.1
 - Apache Camel 2.24.1
 - Apache Camel SQL Component
 - Spring Boot JDBC
 - MS SQL

## Running
You can first run ms sql docker container and liquibase scripts, please check README.md in db-schema folder.

To run application use gradle:
```
./gradlew bootRun
```

## Licence

Project uses a MIT licence .

MIT License

Copyright (c) 2019 Michal Kostewicz

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
