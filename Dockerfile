FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/be-0.0.1-SNAPSHOT.jar be.jar
CMD ["java", "-jar", "be.jar"]