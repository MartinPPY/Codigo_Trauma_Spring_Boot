# ===== STAGE 1: BUILD =====
FROM maven:3.9.11-eclipse-temurin-21 AS build

WORKDIR /app

# Copiamos todo el proyecto
COPY . .

# Compilamos el proyecto y generamos el jar
RUN mvn clean package -DskipTests


# ===== STAGE 2: RUNTIME =====
FROM openjdk:24-ea-1-jdk-slim

WORKDIR /app

# Copiamos el jar desde el stage anterior
COPY --from=build /app/target/app-0.0.1.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
