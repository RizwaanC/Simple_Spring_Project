# Use a lightweight JDK 8 image as the base image
FROM adoptopenjdk:8-jre-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file and other necessary files to the container
COPY target/<your-spring-boot-jar>.jar /app/app.jar
COPY src/main/resources/application.properties /app/application.properties

# Expose the port on which the Spring Boot application is running
EXPOSE 8080

# Run the Spring Boot application when the container starts
CMD ["java", "-jar", "/app/app.jar"]