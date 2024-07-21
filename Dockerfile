FROM ubuntu:latest AS build

RUN apt-get update && apt-get install -y openjdk-21-jdk maven
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:21-slim

EXPOSE 8080

COPY --from=build /target/videoplatform-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
