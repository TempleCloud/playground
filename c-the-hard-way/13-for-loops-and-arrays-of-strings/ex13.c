#include <stdio.h>

int main(int argc, char *argv[]) {
    int i = 0;

    // go through each string in argv
    // why am I skipping argv[0]?
    for (i = 1; i < argc; i++) {
        printf("arg %d: %s\n", i, argv[i]);
    }

    // let's make our own array of strings
    char *counties[] = {
        "Bristol", "Somerset",
        "Sussex", "London"
    };

    int num_counties = 4;

    for (i = 0; i < num_counties; i++) {
        printf("state %d: %s\n", i, counties[i]);
    }

    return 0;
}
