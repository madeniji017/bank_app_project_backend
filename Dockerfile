FROM openjdk:8-jdk-alpine AS build
RUN mkdir -p /java
WORKDIR /java
COPY . .
RUN ./mvnw -B clean package -Dmaven.test.skip=true --file pom.xml
RUN ls -ahl
FROM openjdk:8-jdk-alpine
RUN mkdir -p /apps
WORKDIR /apps
RUN pwd
COPY --from=build /java/target/*.jar backend_app.jar
EXPOSE 4000
ENTRYPOINT ["java","-jar","/apps/backend_app.jar"]
#"--spring.config.location=file:///apps/config/application.properties"