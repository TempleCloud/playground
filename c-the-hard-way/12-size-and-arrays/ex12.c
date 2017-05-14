#include <stdio.h>

int main(int argc, char *argv[]) {

    int area[] = { 10, 20 ,30, 40, 50 };
    char name[] = "Tim";
    char full_name[] = { 
        'T', 'i', 'm', 
        ' ', 
        'L', 'a', 'n', 'g', 'f', 'o', 'r', 'd' , '\0'};

    printf("The sizeof an int                   : %ld\n", sizeof(int));
    printf("The sizeof the area                 : %ld\n", sizeof(area));
    printf("The number of ints in area          : %ld\n", sizeof(area) / sizeof(int));
    printf("The sizeof first area cell is       : %ld\n", sizeof(area[0]));
    printf("The sizeof second area cell is      : %ld\n", sizeof(area[1]));

    printf("name                                : %s\n", name);
    printf("The sizeof a char                   : %ld\n", sizeof(char));
    printf("The sizeof the name                 : %ld\n", sizeof(name));
    printf("The number of chars in name         : %ld\n", sizeof(name) / sizeof(char));
    printf("The sizeof first nane cell is       : %ld\n", sizeof(name[0]));
    printf("The sizeof second name cell is      : %ld\n", sizeof(name[1]));
    
    printf("full_name                           : %s\n", full_name);
    printf("The sizeof the full_name            : %ld\n", sizeof(full_name));
    printf("The number of chars in full_name    : %ld\n", sizeof(full_name) / sizeof(char));
    printf("The sizeof first full_name cell is  : %ld\n", sizeof(full_name[0]));
    printf("The sizeof second full_name cell is : %ld\n", sizeof(full_name[1]));

}
