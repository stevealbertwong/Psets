/*
see: https://www.badprog.com/c-type-what-are-uint8-t-uint16-t-uint32-t-and-uint64-t

uint8_t: unsgined char // document the intent storing small numbers rather than char
uint16_t: unsigned short


int32_t: int type that is precisely 32 bits, as long could be 32 or 64 bits 
depending on compiler, os, hardware


*/
#include <stdio.h>
#include <math.h>
#include <stdlib.h>

int main(int argc, char const *argv[])
{
	char* b = "a";
	uint8_t a = *b;
	uint8_t c = 258;
	printf("%lu\n", sizeof(b));
	printf("%d\n", c);
	
	
	return 0;
}