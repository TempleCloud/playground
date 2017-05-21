#include <stdio.h>
#include <ctype.h>
#include <string.h>

// forward declarations
int can_print_it(char ch);
void print_letters(char arg[]);
void print_letters_2(int arg_len, char arg[]);

void print_arguments(int argc, char *argv[]) {
    for (int i = 0; i < argc; i++) {
        print_letters(argv[i]);
    }
}

void print_letters(char arg[]) {
    for (int i = 0; arg[i] != '\0'; i++) {
        char ch = arg[i];
        if (can_print_it(ch)) {
            printf("'%c' == '%d'", ch, ch);
        }
    }
    printf("\n");
}

void print_arguments_2(int argc, char *argv[]) {
    for (int i = 0; i < argc; i++) {
        int len = strlen(argv[i]);
        print_letters_2(len, argv[i]);
    }
}

void print_letters_2(int arg_len, char arg[]) {
    for (int i = 0; i < arg_len; i++) {
        char ch = arg[i];
        if (can_print_it(ch)) {
            printf("'%c' == '%d'", ch, ch);
        }
    }
    printf("\n");
}

int can_print_it(char ch) {
    return isalpha((int) ch) || isblank((int) ch);
}


int main(int argc, char *argv[]) {
    print_arguments(argc, argv);
    printf("\n --- \n\n");
    print_arguments_2(argc, argv);
    return 0;
}
