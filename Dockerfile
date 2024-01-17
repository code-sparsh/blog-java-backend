# Stage 1: Build Stage
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the Maven POM file to the container
COPY pom.xml .

# Copy the entire project to the container
COPY . .

# Build the application using Maven
RUN mvn clean package -DskipTests

# Stage 2: Runtime Stage
FROM eclipse-temurin:17-jdk-alpine AS runtime

# Set the working directory
WORKDIR /app

# Copy only the necessary artifacts from the build stage
COPY --from=build /app/target/*.jar app.jar

# Entry point for running the application
ENTRYPOINT ["java", "-jar", "app.jar"]

# Expose the port that the application will run on
EXPOSE 8080
