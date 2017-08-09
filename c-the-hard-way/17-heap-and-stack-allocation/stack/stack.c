#include <stdio.h>
#include <stdlib.h>

//---------------------------------------------------------------------------------------------------
// cells
//
struct Cell {
    int value;
    struct Cell *previous;
};

struct Cell *new_Cell() {
    struct Cell *cell = malloc(sizeof(struct Cell));
    cell->value = 0;
    cell->previous = NULL;
    return cell;
}

void destroy_Cell(struct Cell *cell) {
    free(cell);
}

void print_Cell(struct Cell *cell) {
    printf("%i", cell->value);
}

void print_Cells(struct Cell *top_cell) {
    if (top_cell) {
        print_Cell(top_cell);
        if (top_cell->previous) {
            printf(", ");
        }
        print_Cells(top_cell->previous);
    }
}

void iter_print_Cells(struct Cell *top_cell) {
    if (top_cell != NULL) {
        struct Cell *this_cell = top_cell;
        do {
            print_Cell(this_cell);
            if (this_cell->previous) {
                printf(", ");
            }
            this_cell = this_cell->previous;
        } while (this_cell);
    }
}


//---------------------------------------------------------------------------------------------------
// stacks
//

struct Stack {
    int size;
    struct Cell *top;
};

void push(struct Stack *stack, int value);
int peek(struct Stack *stack);
int pop(struct Stack *stack);
int size(struct Stack *stack);

struct Stack *new_Stack() {
    struct Stack *stack = malloc(sizeof(struct Stack));
    stack->size = 0;
    stack->top = NULL;
    return stack;
}

void print_Stack(struct Stack *stack) {
    printf("Top->[");
    print_Cells(stack->top);
    printf("]");
    printf(" - size: %i\n", stack->size);
}

void iter_print_Stack(struct Stack *stack) {
    printf("Top->[");
    iter_print_Cells(stack->top);
    printf("]");
    printf(" - size: %i\n", stack->size);
}

void destroy_Stack(struct Stack *stack) {
    while (stack->size > 0) {
        pop(stack);
    }
    free(stack);
}

void push(struct Stack *stack, int value) {
    struct Cell *cell = new_Cell();
    cell->value = value;
    cell->previous = stack->top;
    stack->top = cell;
    stack->size++;
}

int pop(struct Stack *stack) {
    if (stack->size == 0) {
        printf("ERROR: Exceeded stack minimum bound.");
        exit(1);
    } else {
        struct Cell *top = stack->top;
        stack->top = stack->top->previous;
        stack->size--;
        int result = top->value;
        destroy_Cell(top);
        return result;
    }
}

int peek(struct Stack *stack) {
    if (stack->size == 0) {
        printf("ERROR: Exceeded stack minimum bound.");
        exit(1);
    } else {
        return stack->top->value; 
    }
}

int size(struct Stack *stack) {
    return stack->size;
}


//---------------------------------------------------------------------------------------------------
// main
//

int main(int argc, char *argv[]) {

    struct Stack *stack = new_Stack();
    push(stack, 1);
    push(stack, 2);
    push(stack, 3);
    push(stack, 4);
    push(stack, 5);
    
    printf("initial stack (recursive print): ");
    print_Stack(stack);
    
    printf("initial stack (iterative print): ");
    iter_print_Stack(stack);

    int v1 = pop(stack);
    printf("first pop: %i\n", v1);

    int v2 = peek(stack);
    printf("first peek: %i\n", v2);

    int v3 = pop(stack);
    printf("second pop: %i\n", v3);

    printf("first modified stack:");
    print_Stack(stack);

    printf("first push - 666\n");
    push(stack, 666);
    printf("second modified stack: ");
    print_Stack(stack);

    destroy_Stack(stack);

    return 0;
}
