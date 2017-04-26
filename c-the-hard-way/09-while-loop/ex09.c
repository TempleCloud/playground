#include <stdio.h>

int main(int argc, char *argv[]) {

    int num = 32;
    int i = 0;
    while (i < num) {
        printf("%i ", i);
        i++;
    }
    printf("\n");
    while (i > 0) {
        i--;
        printf("%i ", i);
    }
    printf("\n");
    while (i < num) {
        printf("%i ", i);
        if (i == 5) {
            printf("<breaking>");
            break;
        }
        i++;
    }
    printf("\n");
}
