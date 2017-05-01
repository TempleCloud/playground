#!/bin/bash -e

# https://hub.docker.com/_/postgres/
#
docker run \
  --name memo-service-postgres \
  -p 5432:5432 \
  -d templecloud/memo-service-postgres:0.0.1

# check connection
#
# docker exec memo-service-postgres psql -U memoservice -d memo_local -h 127.0.0.1 -p 5432 -c "select 1"
# psql -U memoservice -d memo_local -h localhost -p 5432 -c "select 1"
