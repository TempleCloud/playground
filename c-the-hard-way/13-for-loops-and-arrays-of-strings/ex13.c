#include <stdio.h>

int main(int argc, char *argv[]) {

	int i = 0;

	for (i = 1; i < argc; i++) {
		printf("argv[%i] : %s\n", argc, argv[i]); 
	}

	int num_counties = 4;
    char *counties[] = {"Bristol", "Avon", "Somerset", "Sussex"};
	for (int j = 0; j < num_counties; j++) {
		printf("counties[%i] : %s\n", j, counties[j]);
	}

	return 0;
}
