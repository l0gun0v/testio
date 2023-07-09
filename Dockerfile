FROM openjdk:17

WORKDIR /app

COPY build.gradle .
COPY settings.gradle .
COPY gradlew .

# Copy the source code
COPY src/ src/
COPY gradle/ gradle/
RUN ./gradlew build

EXPOSE 80

# Run the Spring Boot application
CMD ["java", "-jar", "build/libs/social_network.jar"]
