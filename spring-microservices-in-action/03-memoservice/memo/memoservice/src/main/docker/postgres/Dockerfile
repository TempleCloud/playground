FROM postgres:9.6.2-alpine

RUN mkdir -p /docker-entrypoint-initdb.d
ADD init-user-db.sh /docker-entrypoint-initdb.d/init-user-db.sh