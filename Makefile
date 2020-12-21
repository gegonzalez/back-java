ENV = . ./env

g = $(ENV) && ./gradlew

DOCKER_COMPOSE_FILE = "docker-compose.yml"

DOCKER = docker-compose --file $(DOCKER_COMPOSE_FILE)

build:
	export SPRING_PROFILES_ACTIVE=test && $(g) build

run:
	$(g) bootRun

debug:
	$(g) bootRun --debug-jvm

stop:
	@-pkill -INT -f "$$(pwd)/gradle"

clean:
	$(g) clean

test:
	docker-compose --file docker-compose-test.yml up -d && export SPRING_PROFILES_ACTIVE=test && $(g) test

ide:
	$(g) cleanIdea idea

# Docker Helper #
up:
	$(DOCKER) up -d

down:
	$(DOCKER) down -t 0

logs:
	$(DOCKER) logs -f

docker-build:
	docker build -t back-build .

database-provision:
	docker exec mongodb-local bash -c './database/import.sh localhost'

database-down:
	docker rm -f mongodb-local
