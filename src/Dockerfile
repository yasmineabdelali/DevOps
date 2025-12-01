# Étape 1 : construire le projet avec Maven
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : créer l'image finale pour exécution
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar student-management.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","student-management.jar"]
