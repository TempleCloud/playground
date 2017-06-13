create:
	kubectl create -f noddy-service.yml
	kubectl create -f noddy-pod.yml
	kubectl scale pods noddy-pod --replicas=3

delete:
	kubectl delete pods --all
	kubectl delete service --all

