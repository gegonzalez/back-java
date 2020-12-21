FROM openjdk:11.0-jdk-slim AS build
COPY . /src
WORKDIR /src
RUN ./gradlew build

EXPOSE 8080

ENTRYPOINT exec java -Duser.timezone=UTC -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} -Djava.security.egd=file:/dev/./urandom -Xmx1024m -jar app.jar
