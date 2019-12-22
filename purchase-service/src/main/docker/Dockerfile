FROM openjdk:8-jdk-alpine
ADD purchase-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8096
ENV PROFILES="default"

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active=${PROFILES}"]
