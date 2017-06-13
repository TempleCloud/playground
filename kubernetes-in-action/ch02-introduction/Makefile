build:
	docker build -t kubia .

push:
	docker tag kubia templecloud/kubia
	docker push templecloud/kubia

run:
	docker run -p 8080:8080 -d templecloud/kubia

run-local:
	docker run --name kubia-container -p 8080:8080 -d kubia

inspect-local:
	docker inspect kubia-container

exec-local:
	docker exec -it kubia-container bash

stop-local:
	docker stop kubia-container

rm-local:
	docker rm kubia-container
