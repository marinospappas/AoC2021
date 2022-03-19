#include <stdio.h>
#include <stdlib.h>
#include <string.h>

extern int alu_start(char *);
extern int aluinput_09(char *);
char * calculateInp_1_8_max();
char * calculateInp_9_14_max();

char * no_solution = "00000000000000";
int z = 0;
//unsigned char input[14] = { 9, 4, 3, 9, 9, 8, 9, 8, 9, 4, 9, 9, 5, 9 };

void main() {
	char *s1 = calculateInp_1_8_max();
	if (s1 == (char *)0) {
		printf("%s\n", no_solution);
		exit (1);
	}
	char *s2 = calculateInp_9_14_max();
	if (s2 == (char *)0) {
		printf("%s\n", no_solution);
		exit (1);
	}
	printf("%s%s\n",s1,s2);
	exit (0);
}

unsigned char i3_4_arr[] = { 39, 28, 17 };
unsigned char i6_7_arr[] = { 89,78,67,56,45,34,23,12 };

char * calculateInp_1_8_max() {
    for (unsigned char i1 = 9; i1 >= 1; --i1)
        for (unsigned char i2 = 9; i2 >= 1; --i2)
            for (int k3_4 = 0; k3_4 < 3; ++k3_4) {
                unsigned char i3_4 = i3_4_arr[k3_4];
                for (unsigned char i5 = 9; i5 >= 1; --i5)
                    for (int k6_7 = 0; k6_7 < 8; ++k6_7) {
                        unsigned char i6_7 = i6_7_arr[k6_7];
                        for (unsigned char i8 = 9; i8 >= 1; --i8) {
                            unsigned char input[] = { i1, i2, i3_4/10, i3_4%10, i5, i6_7/10, i6_7%10, i8 };
                            z = alu_start(input);
                            if (z == 0) {
				char *s = malloc(20);
				sprintf (s, "%d%d%d%d%d%d", i1, i2, i3_4, i5, i6_7, i8);
				return s;
                            }
                        }
                    }
            }
    return (char *)0;
}

unsigned char i9_10_arr[] = { 94, 83, 72, 61 };
unsigned char i12_13_arr[] = { 95, 84, 73, 62, 51 };

char *calculateInp_9_14_max() {
    for (int k9_10 = 0; k9_10 < 4; ++k9_10) {
	unsigned char i9_10 = i9_10_arr[k9_10];
        for (unsigned char i11 = 9; i11 >= 1; --i11)
            for (int k12_13 = 0; k12_13 < 5; ++k12_13) {
                unsigned char i12_13 = i12_13_arr[k12_13];
                for (unsigned char i14 = 9; i14 >= 1; --i14) {
		    unsigned char input[] = { i9_10/10, i9_10%10, i11, i12_13/10, i12_13%10, i14 };
		    z = aluinput_09(input);
		    if (z == 0) {
			char *s = malloc(20);
			sprintf (s, "%d%d%d%d", i9_10, i11, i12_13, i14);
			return s;
		    }
                }
            }
     }
    return (char *)0;
}
