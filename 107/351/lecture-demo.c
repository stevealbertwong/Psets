
/*
Array

1. Array represents adjacent memory location storing same type of data object

2. representing string using array
see ASCIL
null terminated string
"Harry Potter" = Harry Potter\0  -> 72 97 114 114 121 32 80 111 116 116 101 114 0



*/
int big_array[128]; // allocates 512 adjacent bytes in memory at 0x0000
int* array_ptr;


// big_array + i = &big_array[i] = &big_array[0] + i*sizeof(big_array[0])

// pointing at the start of array, since this is declared as an array
// c knows not meaning the value as there are 128 values 
// i.e. address of first location
array_ptr = big_array; // 0x0000
array_ptr = &big_array[0]; // same as pointing at the start of array, 0x0000
array_ptr = &big_array[3]; // 0x000c
array_ptr = &big_array[0]+3; // 0x000c, + 3 * sizeof(int), c knows when adding to ptr, dont mean the value
array_ptr = &big_array + 3; // 0x000c
*array_ptr = *array_ptr + 1; // increment the value at 4th element of the array at index 3
array_ptr = big_array[130]; // 0x0208, point to out of bound, but C doesnt care, give you back the location





// "Harry Potter" = Harry Potter\0  -> 72 97 114 114 121 32 80 111 116 116 101 114 0
char HP[13] = "Harry Potter";
char null_string = HP[13]; // "\0"

/* 
cse 351 array lecture 
printing address + hex representation of value
*/
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


/*

Bit-level operator
& intersection
| union
^ symmetric difference
~ complement

Logic operator &&, ||, !
anything non-zero is true
zero is always false 
false only if entire int vector is zero 

null pointer = 0x00000000
if (p && *p++) // is equal to if (p) {*p++};

*/

// bitwise operator &, |, ^, ~


char a,b,c,d,e;
a = (char) 0x00; // 00000000
b = ~a; // 11111111
a = (char)0x41; // 0x41 = 01000001
b = ~a; // 10111110 = 0xBE
b = (char) 0x55; //0x55 = 01010101
c = a | b; // 0x55 01010101
d = a & b; // 0x41 01000001
e = a ^ b; // 11101011



/*
Section 3

1. ISA (instruction set architecture)
e.g. x86 ISA 
CISC i.e. complex instruction set computer

- interface between hardware and software
- parts of the processor design one needs to understand to write assembly code
	>> "what is directly visble to software"
	>> e.g. cache size, core frequency -> X architecture as you dont know when writing assembly code
	>> e.g. number of register -> YES is part of ISA, need to know when writing assembly code
- set of instructions to compiler to machine code 
- defines system's state (e.g. program counter, registers, all contents of memory)
	>> program counter: instruction/program that is being executed 
- defines instructions CPU can execute 
	>> e.g. add, sub, mov
- defines effect of each of instruction have on system state
- encoding is mapping between add -> 10101 (instruction -> bit sequence that determines that instruction)
- AMD 
	>> clone of x86, same ISA
	>> high level abstraction is the same

IA 32 
- 32 bits x86

x86-64
- all assignments are in x86-64

2. Assembly Programmer's view




3. C, Assembly, Machine code

- Compiler, Assembler, Linker



 Registers 
- basic unit of storage inside CPU
- width of registers -> 32 bits vs 64 bits



*/

// asssembly code demo
#include <stdio>

int sum(int x, int y){
	return (x+y);
};

int main(int argc, char const *argv[])
{
	sum(1,2);
	return 0;
}

// command: gcc -01 -S code.c
// 01: optimized, -S: assembly, produce code.s

// translated assem code
sum:
	pushl %ebp
	movl %esp, %ebp // moving data/content of esp to ebp 
	movl 12(%ebp), %eax // reading data i.e. parameter from memory
	add 8(%ebp), %eax
	movl %ebp, %esp
	popl %ebp
	ret


/* Assembly

BASIC TYPES OF INSTRUCTIONS
1. Perform arithmetic function on registers or memory data
2. Transfer data between memory and register
	>> load data from memory into register
	>> store register data into memory
3. Transfer control
	>> control means instruction being executed at any given time
	>> flow of intruction being executed 
	>> unconditinal jump from/to procedure e.g, if/else 
	>> conditional branches

BASIC DATA TYPES




MOVE INSTRUCTIONS, REGISTERS AND OPERANDS

1. 6 general purpose + 2 special registers

Special
- %esp
- %ebp

General Purpose
- 
- 

2. Moving Data
- movl Source,Dest: move 4 bytes long word
- movw: 2 bytes word
- movb: 1 byte

3. Operands Types IA32
- Immediate: constant integer data-> usually source not dest 
	>> e.g. movl $0x4, %eax -> immediate to register
	>> movl $-147, (%eax) -> imm to memory
- Register: 8 integer registers e.g. %eax, %edx
	>> e.g. movl %eax, %edx
	>> movl %eax, (%edx)
- Memoery: 4 consecutive bytes of memory at address given by register 
	>> (%eax) this refers to data containing the address 
	>> address modes i.e. ways to specify an address
	>> e.g. movl (%eax), %edx


4. Memory Addressing Modes
- mapping of values into register is a choice of compiler


*/

// Example

var_a = 0x4;
*ptr_a = -147;
var_d = var_a;




// Memory Addressing Mode demo
int arithmetic(int x, int y, int z){
	int t1 = x + y;
	int t2 = t1 + z;
	int t3 = x + 4;
	int t4 = y * 48;
	int t5 = t3 + t4;
	int rval = t2 * t5;

arithmetic:
	movl 8(%ebp), %eax // %eax = x
	movl 12(%ebp), %edx // %edx = y
	leal (%eax, %edx), %ecx // t1 = x + y
	xxx // int t3 = x + 4
	sall // int t4 = y * 48
	addl 16(%ebp), %ecx // int t2 = t1 + z
	leal 4(%edx, %eax), %eax // t5 = t3 + t4 	i.e. 4+x + t4
	imul %ecx, %eax // int rval = t2 * t5







}



/* Buffer Overflow Tutorial

Buffer for user input is often stored on the stack
Overflowing buffer and injecting code

Overrunning the buffer typically causes the program states to be corrupted.
Program State: return address and data structure stored on the stack

*/



















