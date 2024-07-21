# Stage 1: Build stage
FROM ubuntu:latest AS build

# Install OpenJDK 21 and Maven
RUN apt-get update && \
    apt-get install -y openjdk-21-jdk maven

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file and source code to the container
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Final stage
FROM openjdk:21-slim

# Set the working directory inside the container
WORKDIR /app

# Expose port 8080
EXPOSE 8080

# Copy the JAR file from the build stage
COPY --from=build /app/target/videoplatform-0.0.1-SNAPSHOT.jar app.jar

# Define the entry point for the application
ENTRYPOINT ["java", "-jar", "app.jar"]
