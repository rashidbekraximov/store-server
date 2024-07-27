# Stage 1: Build the application
FROM maven:3.8.5-openjdk-11-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and any other files needed for dependency resolution
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Copy the rest of the application source code
COPY src ./src

# Package the application
RUN mvn package

# Stage 2: Create the runtime image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the executable jar file from the build stage to the runtime stage
COPY --from=build /app/target/*.jar store.jar

# Expose the port the application runs on
EXPOSE 1515

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "store.jar"]
