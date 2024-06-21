# Subscriptions Application

This is a Java application built with Gradle and Spring Boot. This project is intended for managing subscriptions.

## Prerequisites

- Java 17
- Gradle
- Docker (optional)

## Building the Project

1. Navigate to the project directory in your terminal.

2. Run the following command to build the project:

```bash
./gradlew build
```

## Running the Project

```bash
java -jar build/libs/subscriptions-0.0.1-SNAPSHOT.jar
```

## Building the Docker Image
If you prefer to use Docker, you can build a Docker image for the project:

```bash
docker build -t subscriptions .
```

## Running with Docker
You can also run the application using Docker:

```bash
docker run --rm -p 8080:8080 subscriptions
```
