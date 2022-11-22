FROM openjdk:8-jdk-alpine AS build

#RUN mkdir -p /java

#WORKDIR /java

COPY . .

RUN ./mvnw -B clean package -Dmaven.test.skip=true --file pom.xml



RUN ls -ahl


FROM openjdk:8-jdk-alpine

RUN mkdir -p /apps

#RUN mkdir -p /apps/truststore

#RUN mkdir -p /apps/config

WORKDIR /apps

RUN pwd

COPY --from=build /java/target/*.jar backend_app.jar

#COPY --from=build /java/src/main/resources/application.properties /apps/config/application.properties

# COPY src/main/resources/* /apps/config/

RUN pwd

RUN ls -ahl && ls -ahl /apps/config
#VOLUME /apps/config

EXPOSE 4000
ENTRYPOINT ["java","-jar","/apps/backend_app.jar"]
#"--spring.config.location=file:///apps/config/application.properties"