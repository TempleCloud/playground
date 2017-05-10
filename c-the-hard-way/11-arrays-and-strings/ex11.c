#include <stdio.h>
#include <string.h>

int main(int argc, char **argv) {

    int numbers[4] = { '0' };
    printf("numbers as digit elements: %d %d %d %i\n", numbers[0], numbers[1], numbers[2], numbers[3]);
    printf("numbers as int elements: %i %i %i %i\n", numbers[0], numbers[1], numbers[2], numbers[3]);
    printf("numbers as char elements: %c %c %c %c\n", numbers[0], numbers[1], numbers[2], numbers[3]);
    printf("numbers as string: %s\n", numbers);
    
    char name[4] = { 'a' };
    printf("name as digit elements: %d %d %d %d\n", name[0], name[1], name[2], name[3]);
    printf("name as int elements: %i %i %i %i\n", name[0], name[1], name[2], name[3]);
    printf("name as char elements: %c %c %c %c\n", name[0], name[1], name[2], name[3]);
    printf("name as string: %s\n", name);

    int numbers_2[4];
    numbers_2[0] = 1;
    numbers_2[1] = 2;
    numbers_2[2] = 3;
    numbers_2[3] = 4;
    printf("numbers_2 as digit elements: %d %d %d %i\n", numbers_2[0], numbers_2[1], numbers_2[2], numbers_2[3]);
    printf("numbers_2 as int elements: %i %i %i %i\n", numbers_2[0], numbers_2[1], numbers_2[2], numbers_2[3]);
    printf("numbers_2 as char elements: %c %c %c %c\n", numbers_2[0], numbers_2[1], numbers_2[2], numbers_2[3]);
    printf("numbers_2 as string: %s\n", numbers_2);
     
    char name_2[4];
    name_2[0] = 'T';
    name_2[1] = 'i';
    name_2[2] = 'm';
    name_2[3] = '\0';
    printf("name_2 as digit elements: %d %d %d %d\n", name_2[0], name_2[1], name_2[2], name_2[3]);
    printf("name_2 as int elements: %i %i %i %i\n", name_2[0], name_2[1], name_2[2], name_2[3]);
    printf("name_2 as char elements: %c %c %c %c\n", name_2[0], name_2[1], name_2[2], name_2[3]);
    printf("name_2 as string: %s\n", name_2);
    
    // break name_2 by removing null at end
    name_2[3] = 'X';     
    printf("broken-1 name_2 as digit elements: %d %d %d %d\n", name_2[0], name_2[1], name_2[2], name_2[3]);
    printf("broken-1 name_2 as int elements: %i %i %i %i\n", name_2[0], name_2[1], name_2[2], name_2[3]);
    printf("broken-1 name_2 as char elements: %c %c %c %c\n", name_2[0], name_2[1], name_2[2], name_2[3]);
    printf("broken-1 name_2 as string: %s\n", name_2);
    
    // break name_2 by adding a null in the middle
    name_2[2] = '\0';     
    printf("broken-2 name_2 as digit elements: %d %d %d %d\n", name_2[0], name_2[1], name_2[2], name_2[3]);
    printf("broken-2 name_2 as int elements: %i %i %i %i\n", name_2[0], name_2[1], name_2[2], name_2[3]);
    printf("broken-2 name_2 as char elements: %c %c %c %c\n", name_2[0], name_2[1], name_2[2], name_2[3]);
    printf("broken-2 name_2 as string: %s\n", name_2);

    char *name_3 = "Tim";
    printf("name_3 as digit elements: %d %d %d %d\n", name_3[0], name_3[1], name_3[2], name_3[3]);
    printf("name_3 as int elements: %i %i %i %i\n", name_3[0], name_3[1], name_3[2], name_3[3]);
    printf("name_3 as char elements: %c %c %c %c\n", name_3[0], name_3[1], name_3[2], name_3[3]);
    printf("name_3 as string: %s\n", name_3);

    // convert the name_3 array into an int!!!
    char *src = &name_3;
    int* name_3_int;
    memcpy(src, name_3_int, 4);
    printf("name_3 as memcpy int: %i\n", *name_3_int);

    // break name_3 by over-writing the null terminator
    // name_3[3] = 'X';
    // Bus error: 10
    // printf("broken name_3 as string: %s\n", name_3);

    // break with bad initializer
    char name_4[4] = {'T','i','m','X', 'X'};
    printf("broken name_4 as string: %s\n", name_4);

    int name_5[4] = {'T','i','m','X', 'X'};
    printf("broken name_5 as string: %s\n", name_5);


}
