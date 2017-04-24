#include <stdio.h>
#include <stdlib.h>

//--------------------------------------------------------------------------------------------------

/* a very basic C program... */
int main(int argc, char *argv[]) {
   
    int distance = 100;
    float power = 2.345f;
    double super_power = 56789.45321;
    char initial = 'R';
    char first_name[] = "Tim";
    char last_name[] = "Langford";

    printf("You are %d miles away.\n", distance);
    printf("You have %f levels of power.\n", power);
    printf("You have %f awesome super powers.\n", super_power);
    printf("You have a first name of: %s.\n", first_name);
    printf("You have a last name of: %s.\n", last_name);
    printf("Your full name is: %s %c %s.\n", first_name, initial, last_name);

    int bugs = 100;
    double bug_rate = 1.2;

    printf("You have a %d bugs at an imaginary rate of %f.\n", bugs, bug_rate);

    long universe_of_defects = 1L * 1024L * 1024L * 1024L;
    printf("The entire universe has %ld bugs.\n", universe_of_defects);

    double expected_bugs = bugs * bug_rate;
    printf("You are expected to have %f bugs.\n", expected_bugs);
    
    double part_of_universe = expected_bugs / universe_of_defects;
    printf("That is only a %e portion of the universe.\n", part_of_universe);

    // some wierd UB stuff...
    char null_byte = '\0';
    int care_percentage = bugs * null_byte;
    printf("Which means you should care %d%%.\n", care_percentage);

    // mess with the length of the first name to over-write the inital or do something else wierd.
    first_name[4] = '$'; 
    printf("Your first broken full name is: %s %c %s.\n", first_name, initial, last_name);

    // mess with the the last name to destroy the string temrination char or do something else wierd.
    last_name[8] = 64; 
    printf("Your second broken full name is: %s %c %s.\n", first_name, initial, last_name);

    return 0;
}
