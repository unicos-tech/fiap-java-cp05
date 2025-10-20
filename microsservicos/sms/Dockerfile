# Use Eclipse Temurin (Java 21) as base image
FROM eclipse-temurin:21-jre

# Set working directory
WORKDIR /

# Copy the JAR file into the container
COPY ./build/libs/*.jar app.jar

EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
