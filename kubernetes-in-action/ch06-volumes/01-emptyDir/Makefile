
build:
	docker build -t templecloud/fortune .
	docker push templecloud/fortune

create-pod:
	kubectl create -f fortune-pod.yaml 

tunnel:
	kubectl port-forward fortune 8080:80

test:
	@while true; do echo; echo; echo ----------; echo; echo; curl http://localhost:8080; sleep 10; done
