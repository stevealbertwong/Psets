/*
28jan2017

http://www.thegeekstuff.com/2012/10/gcc-compiler-options/?utm_source=feedburner
-Wall -> shows flags

gcc -c gccflag.c -> gccflag.o (only compiled code without linking) -> why compiled code without linking (object file) cannot run?
gcc gccflag.c -> a.out
*/

#include<stdio.h>

int main(void)
{
   int i;
   printf("\n The Geek Stuff [%d]\n", i);
   return 0;
}