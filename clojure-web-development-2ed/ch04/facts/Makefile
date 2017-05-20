
run:
	lein run

# repl
#
# (require '[facts.db.core :refer :all])
# (mount/start #'facts.db.core/*db*)
# (doc save-fact!)
repl:
	lein repl :connect 7000

create-db:
	lein migratus create facts

migrate-db:
	lein run migrate

test:
	lein test

auto-test:
	lein test-refresh
