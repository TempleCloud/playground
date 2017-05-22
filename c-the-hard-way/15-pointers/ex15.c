#include <stdio.h>

int main(int argc, char *argv[]) {

    // test arrays
    int ages[] = { 40, 38, 64, 66};
    char *names[] = {
        "Tim", "Nu", "Mum", "Dad"
    };

    // size of ages
    int ages_size = sizeof(ages) / sizeof(int);

    // 1. array index method
    for (int i = 0; i < ages_size; i++) {
        printf("%s has rotated around the Sun %i times!\n", 
                names[i], ages[i]);
    }

    printf("---\n");

    // 2. Pointer index method
    
    int *cur_age = ages;
    char **cur_name = names;

    for (int i = 0; i < ages_size; i++) {
        printf("%s has rotated around the Sun %i times!\n", 
                *(cur_name + i) , *(cur_age + i));
    }

    printf("---\n");

    // 3. Pointers can be used contiguously as an array

    for (int i = 0; i < ages_size; i++) {
        printf("%s has rotated around the Sun %i times!\n", 
                cur_name[i], cur_age[i]);
    }

    printf("---\n");

    // 4. Pointers can be used in a crazy complex way!

    for (cur_name = names, cur_age = ages; (cur_age - ages) < ages_size; cur_age++) {
        printf("%s has rotated around the Sun %i times!\n", 
                *cur_name, *cur_age);
    }


    // Pointer Lexicon ------
    
    // an integer pointer
    int *ptr;
    // dereference the value of a pointer
    int ptr_value = *ptr;
    // increment the address and then dereference the value
    int next_ptr_value = *(ptr + 1);
    // get the address of the pointer
    // int ptr_addr = &ptr;
    // create a pointer to a variable
    int some_val = 3;
    int *some_val_ptr = &some_val;
    // increment the pointer address
    ptr++;



}


