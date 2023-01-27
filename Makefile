APP_EXECUTION_FILE = target/BaseDeDatosWebMS.war
DOCKER_COMPOSE  = docker-compose -f .deployment/docker/docker-compose.yml
DOCKER_COMPOSE_BASIC = docker-compose -f .deployment/docker/docker-compose-basic.yml
DOCKER_COMPOSE_SINGLE = docker-compose -f .deployment/docker/docker-compose-single.yml
UNAME := $(shell uname)

COLOR_RESET   = \033[0m
COLOR_INFO    = \033[32m
COLOR_COMMENT = \033[33m

ifeq ($(UNAME), Linux)
MVN                = ./mvnw
else
MVN                = mvnw.cmd
endif

ifeq ($(UNAME), Darwin)
MVN                = ./mvnw
endif

##@ Helpers
.PHONY: help

help:  ## Display this help
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[1;34m<target>\033[0m\n"} /^[a-zA-Z_-]+:.*?##/ { printf "  \033[1;34m%-15s\033[0m %s\n", $$1, $$2 } /^##@/ { printf "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)

##@ Running
.PHONY: init
init: build docker-main-start ## Build and start application (docker + spring boot)

.PHONY: run
run: docker-main-start ## Start application (docker + spring boot)

.PHONY: start
start: build docker-single-start ## Build and start application (docker with no dependencies)

.PHONY: docker
docker: docker-basic-start ## Start application containers, run spring-boot on your own

.PHONY: stop
stop: docker-stop ## Stop application

##@ Building
.PHONY: build
build: ## Package project (WAR or jar)
	$(MVN) -Dmaven.test.skip=true clean package

.PHONY: install
install: ## Install project artifact to local maven repository (m2)
	$(MVN) -Dmaven.test.skip=true clean install

.PHONY: clean
clean: ## Clean project generated files
	$(MVN) clean

##@ Testing
.PHONY: test
test: ## Run project tests
	$(MVN) clean test

##@ Docker (operations with docker containers)

.PHONY: docker-clean
docker-clean: ## Remove all unused Docker containers, networks, images
	docker system prune -f


# Aux functions
docker-basic-start:
	$(DOCKER_COMPOSE_BASIC) up -d

docker-single-start:
	$(DOCKER_COMPOSE_SINGLE) up --build

stop-app:
	echo parar jar

docker-main-start:
	$(DOCKER_COMPOSE) up --build

docker-stop:
	$(DOCKER_COMPOSE) stop
	$(DOCKER_COMPOSE_BASIC) stop
