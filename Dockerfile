FROM maven:3.8.4-openjdk-17 AS build
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-slim
COPY --from=build /target/*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]