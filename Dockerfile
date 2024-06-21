# Use the official Gradle image as a base image
FROM gradle:7.2.0-jdk17 as builder

# Set the working directory
WORKDIR /home/gradle/project

# Copy the project into the Docker image
COPY --chown=gradle:gradle . .

# Build the project inside the Docker image
RUN gradle build --no-daemon

# Use the official OpenJDK image as a base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from the builder image
COPY --from=builder /home/gradle/project/build/libs/*.jar ./app.jar

# Expose port 8080
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]