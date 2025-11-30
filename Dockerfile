#BUILD
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copiamos todo el proyecto
COPY . .

# Compilamos el proyecto y generamos el jar
RUN mvn clean package -DskipTests


#RUN TIME
FROM openjdk:24-ea-1-jdk-slim

ARG JAR_FILE=target/app-0.0.1.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT [ "java","-jar","app.jar" ]