/*
Byte ordering of multibyte numerical data in memory
Microprocessor architectures commonly use two different methods to store the individual bytes of multibyte numerical data in memory. This difference is referred to as “byte ordering” or “endian nature.”

http://www.geeksforgeeks.org/little-and-big-endian-mystery/

shows the byte representation of int, float and pointer

int hex => type cast into char => format print %.2x

depends on CPU not OS but mostly
microsoft, unix/linux => little endian since uses intel x86
network byte order => big endian => ields in the protocols of the Internet protocol suite, such as IPv4, IPv6, TCP, and UDP, are transmitted in big-endian order. For this reason, big-endian byte order is also referred to as network byte order.
BM® z/Architecture® and S/390® mainframes and Motorola microprocessors => big endian

An operating system can be ported to work on many hardware architectures, which means that an OS can work on different endians.
Intel x86 processors store a two-byte integer with the least significant byte first, followed by the most significant byte. This is called little-endian byte ordering. 
Other CPUs, such as the PowerPC CPU, store a two-byte integer with its most significant byte first, followed by its least significant byte. This is called big-endian byte ordering.

see: https://betterexplained.com/articles/understanding-big-and-little-endian-byte-order/
*/
#include <stdio.h>

/* function to show bytes in memory, from location start to start+n*/
void show_mem_rep(char *start, int n) 
{
	int i;
	for (i = 0; i < n; i++)
        // precision vs widthfield => in short .2 means fill with 0, 2 means fill with space
        // print a hex no. with precision of 2 => there will be at least 2 hex digits in the result, if the representation of the value has less digits, it is prefixed with 0's
        // %x works too
        // 0x%2x" the 2 defines the "fieldwidth": if the hex representation of the value consists of less than 2 digits, it is prefixed with spaces to end up with a fieldwidth of 2. 
		printf(" %.2x", start[i]);
        // printf(" 0x%.2x", start[i]);
	printf("\n");
}

/*Main function to call above function for 0x01234567*/
int main()
{
    // int i = 0x01234567; // 01 : 1 byte, 23 : 1 byte etc. + 0x indicates its hex
    int i = 4660; // 0x1234 (hex)
    // int i = 0x123456789123; // int overflow
    
    printf("%lu", sizeof(i)); // int is always 4
    printf("\n");
    // typecast => character pointer is pointing to an integer i
    // => Since size of character is 1 byte when the character pointer 
    // is de-referenced it will contain only first byte of integer
    show_mem_rep((char *)&i, sizeof(i));
    // getchar();
    return 0;
}