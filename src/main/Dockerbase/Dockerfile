# Stage 1 (to create a "build" image, ~140MB)
FROM openjdk:8-jdk-alpine3.7 AS builder
COPY . /src/java
WORKDIR /src/java
RUN [ "javac", "SpringbootBankAppApplication.java" ]
EXPOSE 4000
ENTRYPOINT [ "java", "SpringbootBankAppApplication" ]