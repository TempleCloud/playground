#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[]) {
    if (argc == 1) {
        printf("You do not have any arguments.\n");
    }
    else if (argc > 0 
             && argc < 4 
             && strncmp(argv[1], "waffle", 6) != 0
             && strncmp(argv[1], "pancake", 7) != 0) {
        printf("Your arguments were: ");
        for (int i = 1; i < argc; i++) {
            if (i <= argc) {
                printf("%s ", argv[i]);
            } else {
                printf("%s", argv[i]);
            }
        }
        printf("\n");
    }
    else {
        printf("You have too many arguments or an initial \"pancake\" or \"waffle\". \n");
    }
}
