instance=localhost:8080

# -------------------------------------------------------------------------------------------------
# memoservice build commands

build:
	mvn clean package docker:build

clean:
	mvn clean
	docker rmi templecloud/memo-service:0.0.1
	docker rmi templecloud/memo-config-service:0.0.1

dc-start:
	docker-compose -f ./docker/common/docker-compose.yml up

check:
	@curl ${instance}/v1/user/fake-user-id/memo/fake-memo-id ;echo

dc-stop:
	docker-compose -f ./docker/common/docker-compose.yml down


# -------------------------------------------------------------------------------------------------
# memoservice check commands

ms-echo:
	@curl ${instance}/v1/echo/ECHO!;echo

ms-memo:
	@curl ${instance}/v1/user/fake-user-id/memo/fake-memo-id;echo


# -------------------------------------------------------------------------------------------------
# spring boot server commands
#
# https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html
#

shutdown-spring:
	@curl -s ${instance}/health | jq

check-actuator:
	@curl -s ${instance}/actuator | jq

check-beans:
	@curl -s ${instance}/beans | jq

check-autoconfig:
	@curl -s ${instance}/autoconfig | jq

check-auditevents:
	@curl -s ${instance}/auditevents | jq

check-configprops:
	@curl -s ${instance}/configprops | jq

check-dump:
	@curl -s ${instance}/dump | jq

check-health:
	@curl -s ${instance}/health | jq

check-info:
	@curl -s ${instance}/info | jq

check-loggers:
	@curl -s ${instance}/loggers | jq

check-mappings:
	@curl -s ${instance}/mappings | jq

check-metrics:
	@curl -s ${instance}/metrics | jq

check-trace:
	@curl -s ${instance}/trace | jq

#check-flyway:
#	@curl -s ${instance}/flyway | jq

#check-liquibase:
#	@curl -s ${instance}/liquibase | jq


