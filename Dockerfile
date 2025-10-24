# Use OpenJDK 17
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy all files
COPY . .

# Make mvnw executable
RUN chmod +x mvnw

# Build the project
RUN ./mvnw clean package -DskipTests

# Expose Spring Boot default port
EXPOSE 8090

# Run the jar
ENTRYPOINT ["java","-jar","target/*.jar"]
