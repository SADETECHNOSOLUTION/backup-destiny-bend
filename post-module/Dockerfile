# Stage 1: Build stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application, skipping tests to speed up Render deployment
RUN mvn clean package -DskipTests

# Stage 2: Run stage
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Create the uploads directory (matches your file.upload-dir=uploads/ property)
RUN mkdir -p uploads

# The port your user-module runs on
EXPOSE 8083

# JVM optimizations for memory-constrained environments like Render
# -XX:+UseG1GC: Good for microservices
# -Xmx512m: Limits heap memory to prevent container crashes
ENTRYPOINT ["java", "-Xmx512m", "-XX:+UseG1GC", "-jar", "app.jar"]