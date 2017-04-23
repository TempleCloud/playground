#include <stdio.h>
#include <stdlib.h>

//--------------------------------------------------------------------------------------------------

void dodgey_method() {
    char** abused_mem = malloc(28);
    for (int i = 0; i < 1000; i++) {
        if (i > 500) {
            abused_mem = NULL;
        }
        printf("%i", abused_mem[i]);
    }
}

/* a very basic C program... */
int main(int argc, char *argv[]) {
    // another comment...
    int age = 40;
    char* the_truth = "super cool!"; 
    
    printf("Tim, now you are %i years old!\n", age);
    printf("Tim, now you are %s\n", the_truth);
    printf("Tim, now you are not %d years old!\n", age / 3);
    printf("Tim, now you are not %f years old!\n", age * 0.3f);
    printf("Tim, this will produce a compiler warning and show this '%i' when printed  \n", argv);

    dodgey_method();
    
    return 0;
}
