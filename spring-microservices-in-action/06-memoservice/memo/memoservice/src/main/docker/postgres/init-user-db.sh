#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE USER memoservice WITH PASSWORD 'memoservice';
    CREATE DATABASE memo_local;
    GRANT ALL PRIVILEGES ON DATABASE memo_local TO memoservice;
EOSQL

# psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
#     CREATE USER memoservice;
#     CREATE DATABASE memo_local;
#     GRANT ALL PRIVILEGES ON DATABASE memo_local TO memoservice;
# EOSQL
