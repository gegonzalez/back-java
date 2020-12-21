FROM openjdk:11.0-jre-slim
ENV LANG C.UTF-8

EXPOSE 8080

COPY build/libs/*.jar /api/app.jar
WORKDIR /api

ENTRYPOINT exec java -Duser.timezone=UTC -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} -Djava.security.egd=file:/dev/./urandom -Xmx1024m -jar app.jar
