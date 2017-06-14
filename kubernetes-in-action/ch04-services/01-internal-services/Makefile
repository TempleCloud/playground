create:
	kubectl create -f kubia-service.yml
	kubectl create -f kubia-pod.yml
	kubectl scale pods kubia-pod --replicas=3

delete:
	kubectl delete pods --all
	kubectl delete service --all

