build:
	mvn clean package docker:build

clean:
	mvn clean
	docker rmi templecloud/echo-service:0.0.1

start:
	docker-compose -f ./docker/common/docker-compose.yml up

check:
	@curl localhost:8080/echo/alive ;echo

stop:
	docker-compose -f ./docker/common/docker-compose.yml down

