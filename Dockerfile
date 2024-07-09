# Use AdoptOpenJDK 11 as the base image
FROM adoptopenjdk:11-jdk-hotspot

# Set the working directory in the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container
COPY target/*.jar soliq.jar

# Expose the port that the Spring Boot application uses (default is 8080)
EXPOSE 1515

# Specify the command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "soliq.jar"]
