#include <stdio.h>

//--------------------------------------------------------------------------------------------------

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
    
    return 0;
}
