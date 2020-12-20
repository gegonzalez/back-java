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
	export SPRING_PROFILES_ACTIVE=test && $(g) test

ide:
	$(g) cleanIdea idea

# Docker Helper #
up:
	$(DOCKER) up -d

down:
	$(DOCKER) down -t 0

logs:
	$(DOCKER) logs -f

docker-test:
	$(DOCKER) run --rm test

docker-build:
	docker build -t back-build .

docker-run:
	docker run --env-file ./.env -it --rm -p 8080:8080 --link mongodb-local --name api-back back-build