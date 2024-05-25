FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean install -DskipTests
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/challenge-0.0.1-SNAPSHOT.jar /app/application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]

EXPOSE 8085