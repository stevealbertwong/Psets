// cse 351 array lecture 
// printing address + hex representation of value

#include <stdio.h>

void show_bytes(int* p, int len){
	
	for (int i = 0; i < len; ++i)
	{
		// %x: hex %p: pointer *(p+1):gabbage value 
		// \t:tab \n:newline %.5x: shows 5 digits of hex value
		printf("%p\t%.5x\t%x\n", p+i, *(p+i), *(p+i));

	};

};

void show_int(int x){
	// (char*) &x 
	show_bytes((int*) &x, sizeof(int));
}

int main(int argc, char const *argv[])
{
	int a = 12345; // hex representation of the value-> 0x00003039
	int* pointer = &a; // have to initialize pointer
	show_bytes(pointer, 10); // print ptr addr + value
	printf("here is show_int\n");
	show_int(a);

	return 0;
}