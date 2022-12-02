FROM adoptopenjdk/maven-openjdk8 AS build

WORKDIR /app
COPY src src
COPY ./pom.xml .
RUN ls
RUN mvn clean install

FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar backend_app.jar
RUN ls
EXPOSE 8084

ENTRYPOINT ["java", "-jar", "/app/backend_app.jar"]