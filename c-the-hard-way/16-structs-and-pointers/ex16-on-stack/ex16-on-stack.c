#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>

struct Person {
    char *name;
    int age;
    double weight;
    double height;
};

struct Person create_Person(
    char *name, int age, double weight, double height
    ) {
    struct Person this;
    
    this.name = strdup(name);
    this.age = age;
    this.weight = weight;
    this.height = height;

    return this;
}

void print_Person(struct Person person) {
    printf("Name: %s\n", person.name);
    printf("\tAge   : %i\n", person.age);
    printf("\tWeight: %f\n", person.weight);
    printf("\tHeight: %f\n", person.height);
}

int main(int argc, char* argv[]) {

    // make two people
    struct Person tim = create_Person("Tim", 40, 120.0, 180.0);
    struct Person nu = create_Person("Nu", 38, 80.0, 150.0);

    // print out the persons and their location in memory
    print_Person(tim);
    print_Person(nu);

    // mutate some of the values and then pring them again
    tim.age += 1;
    tim.weight = 90.0;
    nu.age += 1;
    nu.weight = 70.0;

    print_Person(tim);
    print_Person(nu);

    return 0;
}
