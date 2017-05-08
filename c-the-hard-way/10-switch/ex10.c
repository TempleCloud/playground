#include <stdio.h>

int main(int argc, char **argv) {

    if (argc < 2) {
        printf("At least one argument is required!\n");
        return -1;
    }

    for (int v = 1; v < argc; v++) {
        char letter;
        for (int i = 0; letter = argv[v][i], letter != '\0'; i++) {
            switch (letter) {
                case 'a':
                case 'A':
                    printf("%d 'A'\n", i);
                    break;
                case 'e':
                case 'E':
                    printf("%d 'E'\n", i);
                    break;
                case 'i':
                case 'I':
                    printf("%d 'I'\n", i);
                    break;
                case 'o':
                case 'O':
                    printf("%d 'O'\n", i);
                    break;
                case 'u':
                case 'U':
                    printf("%d 'U'\n", i);
                    break;
                case 'y':
                case 'Y':
                    if (i > 2) {
                        printf("%d 'Y'\n", i);
                    }
                    break;
                default:
                    printf("%d: letter is not a vowel: %c (%d)\n", i, letter, letter);
            }
        }
    }
}
