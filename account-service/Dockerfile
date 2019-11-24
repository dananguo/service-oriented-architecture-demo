FROM openjdk:8-jdk-alpine
ADD account-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080 
ENV PROFILES="default"

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active=${PROFILES}"]
