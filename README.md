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

1. Create the file `.env` and set the environment variables:
    - `DB_HOST`
    - `DB_PORT`
    - `DB_DATABASE`
    - `DB_USERNAME`
    - `DB_PASSWORD`
2. Execute the command `make build up` and `make database-provision` to load data.
3. Open the link <http://127.0.0.1:8080/health>

## Run these commands
- **make run**
- **make debug**
- **make stop**
- **make test**
- **make clean**
