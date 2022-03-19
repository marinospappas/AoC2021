#include <stdio.h>
#include <stdlib.h>
#include <string.h>

extern int alu_start(char *);
extern int aluinput_09(char *);

char * no_solution = "00000000000000";
int z = 0;
unsigned char input[14] = { 9, 4, 3, 9, 9, 8, 9, 8, 9, 4, 9, 9, 5, 9 };

void main(int argc, char *argv[]) {
	long counter = 100000;
	if (argc == 2)
		counter = atol(argv[1]);
	printf("counter = %ld\n", counter);
	for (long i = 0; i < counter; ++i) {
		alu_start(input);
		z = aluinput_09(input+8);
	}
	printf("%d\n", z);
	exit (0);
}

