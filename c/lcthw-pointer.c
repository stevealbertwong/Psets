/*
lcthw ex15
4 ways to print array

The stack typically has a much smaller size than the heap; usually it's something like 8MB => segfault

All arrays are references. The value of an array is its address.

TODO:
typecasting
pointer into function
pass by reference
pointer arithmetic
malloc dynamic vs static allocation
void vs other data type
strcpy vs strdup vs "name" => free 
uint8_t vs other datatype

*/
#include <stdio.h>

int main(int argc, char const *argv[])
{
	int num_ptr[] = {10,20,30};
	int* num_ptr1 = num_ptr;
	num_ptr1[0] = 218; // pointer access array
	int num_arr[10] = {0}; // initialize to all 0
	num_arr = num_ptr1; // array access pointer
	num_arr[2] = 89;

	int** num_ptr_ptr = &num_ptr1;
	int*** num_ptr_ptr_ptr = &num_ptr_ptr;
	printf("%p\n", num_ptr1);
	printf("%p\n", num_ptr_ptr);

	int count = sizeof(num_ptr) / sizeof(int); // ptr is not array since size is different
	printf("%d\n", count);

	/**********************************************************/
	// 1st : just index
	for (int i = 0; i < count; ++i)
	{
		printf("%d\n", num_ptr[i]); 
	}
	
	// 2nd : pointer-arithmetic
	for (int i = 0; i < count; ++i)
	{
		printf("%d\n", *(num_ptr1+i)); 
	}
	
	// 3rd: using pointer's index
	for (int i = 0; i < count; ++i)
	{
		printf("%d\n", num_ptr1[i]); // ptr like array
	}

	// 4th : super complex comparing ptr address difference
	for (int* new_ptr = num_ptr; (new_ptr - num_ptr) < count; ++new_ptr)
	{
		printf("%d\n", *new_ptr);
		printf("%p\n", new_ptr); 
		printf("%p\n", num_ptr);
		int diff = new_ptr - num_ptr; // 4 in decimal/hex turns into 1 => ptr difference in the address it contains => pointer arithmetic
		printf("diff: %d\n", diff);
	}



	
	/**********************************************************/
	

	return 0;
}