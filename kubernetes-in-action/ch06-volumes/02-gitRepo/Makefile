
create-pod:
	kubectl create -f gitrepo-volume-pod.yml 

tunnel:
	kubectl port-forward gitrepo-volume-pod  8080:80

test:
	@curl http://localhost:8080
