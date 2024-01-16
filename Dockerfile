FROM eclipse-temurin:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the Maven POM file to the container
COPY pom.xml .

# Copy the entire project to the container
COPY . .

# Build the application using Maven
RUN mvn clean package

# Set the volume for temporary files
VOLUME /tmp

# Copy the JAR file from the target directory to the root of the container
COPY target/*.jar app.jar

# Entry point for running the application
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Expose the port that the application will run on
EXPOSE 8080
