FROM openjdk:8
ADD target/backend-1.0.0-SNAPSHOT.jar backend-1.0.0-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "backend-1.0.0-SNAPSHOT.jar"]