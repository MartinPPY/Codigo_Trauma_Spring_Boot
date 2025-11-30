FROM openjdk:24-ea-1-jdk-slim

ARG JAR_FILE=target/app-0.0.1.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT [ "java","-jar","app.jar" ]