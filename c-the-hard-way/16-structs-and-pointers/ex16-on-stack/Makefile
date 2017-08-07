CFLAGS= -std=c99 -Wall
PROGRAM= ex16-on-stack
ARGS= 

run: compile
	./$(PROGRAM)

valgrind:
	valgrind -v --leak-check=full --show-leak-kinds=all ./$(PROGRAM)

lint:
	splint $(PROGRAM).c

clean:
	rm -f $(PROGRAM)
	rm -Rf $(PROGRAM).dSYM

#---------------------------------------------------------------------------------------------------
# cc / llvm clang

compile-debug: CFLAGS += -g
compile-debug: compile

compile: $(PROGRAM).c
	@echo "compiling with cc (clang)..."
	cc $(CFLAGS) $(PROGRAM).c -o $(PROGRAM)

debug: compile-debug
	lldb $(PROGRAM)

#---------------------------------------------------------------------------------------------------
# gcc

compile-debug-gcc: CFLAGS += -g
compile-debug-gcc: compile-gcc

compile-gcc: $(PROGRAM).c
	@echo "compiling with gcc..."
	gcc $(CFLAGS) $(PROGRAM).c -o $(PROGRAM)

debug-gdb: compile-debug-gcc
	gdb --batch --ex run --ex bt --ex q --args $(PROGRAM) $(ARGS)

