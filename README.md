# Backend Java - Spring boot

## Tech Stack

- [Java 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
- [Spring Boot](https://projects.spring.io/spring-boot/)
- [Mongo DB](https://www.mongodb.com/)

## Requirements

* [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [Gradle](https://gradle.org/)
* [Docker](https://www.docker.com/)

## How to install and run

1. Create the file `.env` and set the environment variables. These are suggested values.
    - `DB_HOST`=localhost
    - `DB_PORT`=27017
    - `DB_DATABASE`=database 
    - `DB_USERNAME`=user
    - `DB_PASSWORD`=password
2. Execute the command `make build docker-build up` and `make database-provision` to load data.
3. Open the link <http://127.0.0.1:8080/health>

Note: If `make build` fails, try `gradle wrapper --gradle-version 6.7.1` and try again.

## Run these commands
- **make up**: executes API and BD services
- **make down**: stops docker containers
- **make database-provision**: provision data for the API
- **make debug**
- **make test**: executes unit and integration tests with BD for tests
- **make clean**
