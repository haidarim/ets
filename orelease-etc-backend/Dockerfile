# Stage 1: Build
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /back-end

COPY mvnw .
COPY mvnw.cmd .
COPY .mvn/ .mvn/
COPY pom.xml .
COPY src ./src

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline
RUN ./mvnw package -DskipTests

# Debugging: List files in the target directory
RUN ls -l /back-end/target

# Stage 2: Create a minimal image
FROM openjdk:17-jdk-slim

WORKDIR /back-end

# Copy the built JAR file
COPY --from=build /back-end/target/ets-backend-0.0.1-SNAPSHOT.jar ets_back.jar

# Set default environment variables
ENV POSTGRES_HOST=localhost \
    POSTGRES_PORT=5432 \
    POSTGRES_DB=testdb \
    POSTGRES_USER=dev_user \
    POSTGRES_PASSWORD=010101 \
    JWT_PR_KEY=../../../pr_key.pem \
    JWT_PU_KEY=../../../public.key

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "ets_back.jar"]
