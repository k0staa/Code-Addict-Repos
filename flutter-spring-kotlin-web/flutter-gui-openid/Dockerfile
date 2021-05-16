# Stage 1 : build production code
FROM ubuntu:20.04 AS build
ARG DEBIAN_FRONTEND=noninteractive
## Prerequisites
RUN apt-get update && apt-get install -y unzip xz-utils git openssh-client curl && apt-get upgrade -y 

## Install flutter beta
RUN curl -L https://storage.googleapis.com/flutter_infra/releases/stable/linux/flutter_linux_2.0.1-stable.tar.xz | tar -C /opt -xJ
ENV PATH="$PATH":"/opt/flutter/.pub-cache/bin:/opt/flutter/bin"

COPY . /app
WORKDIR /app/

RUN flutter build web

## Stage 2 : create the docker final image
FROM nginx:alpine
WORKDIR /usr/share/nginx/html
COPY --from=build /app/build/web ./
COPY nginx/nginx.conf /etc/nginx/nginx.conf
