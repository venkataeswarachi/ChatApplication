FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

# Make mvnw executable
RUN chmod +x mvnw

# Build the app
RUN ./mvnw clean package -DskipTests

# Copy JAR to fixed name
RUN cp target/*.jar app.jar

EXPOSE 8090

# Run app
ENTRYPOINT ["java","-jar","app.jar"]
