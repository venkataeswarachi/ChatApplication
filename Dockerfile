# Use OpenJDK 17 base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Give execution permission for mvnw
RUN chmod +x mvnw

# Build the project
RUN ./mvnw dependency:go-offline
RUN ./mvnw clean package -DskipTests

# Copy the built jar to app.jar
COPY target/*.jar app.jar

# Expose port 8080 (default Spring Boot port)
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java","-jar","app.jar"]
