# API

An example of a very simple api written in Kotlin using Spring Boot 2 and Spring Web Flux. The application has one secured endpoint and one unsecured. The Spring Boot Oauth 2 resource server is used for security.

You can find more details in my blog post [Code-Addict](http://code-addict.pl/flutter-spring-kotlin-web/)

## Running project
This project use Spring Boot 2, so in order to run it you can simply use `./gradlew bootRun` command. 

## Building Docker image with application
You can build application docker image using `jib` gradle plugin. Just use following command in root of this project:
```
./gradlew jibDocerBuild
```

You can run container using following command:
```
docker run -p 80:80 flutter-kotlin-api
```

