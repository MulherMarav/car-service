FROM openjdk:17-jdk-slim-buster
EXPOSE 8080
#expose - apenas informativo, não expoem efetivamente
ADD /target/github-service-0.0.1-SNAPSHOT.jar github-service.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "github-service.jar"]