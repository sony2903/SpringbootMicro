# Use AdoptOpenJDK as the base image
FROM adoptopenjdk/openjdk11:alpine-jre

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container at path /app
COPY target/services-0.0.1-SNAPSHOT.jar /app/services-0.0.1-SNAPSHOT.jar

# Copy application.properties to the container
COPY src/main/resources/application.properties /app/application.properties
# Copy application.properties to the container
COPY src/main/resources/application-dev.properties /app/application-dev.properties

# Expose the port that your application runs on (default for Spring Boot is 8080)
EXPOSE 9998

# Command to run your application when the container starts
CMD ["java", "-jar", "-Dspring.profiles.active=dev", "services-0.0.1-SNAPSHOT.jar"]
