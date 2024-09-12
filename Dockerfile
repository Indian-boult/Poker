# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the local project files to the container
COPY . .

# Install Maven
RUN apt-get update && apt-get install -y maven

# Compile the project with Maven, run tests, and package it into a JAR file
RUN mvn clean test package

# Expose port 8080 (or the port your app is configured to use)
EXPOSE 8080

# Set the entry point to run the application JAR
CMD ["java", "-jar", "target/poker-1.0-SNAPSHOT.jar"]
