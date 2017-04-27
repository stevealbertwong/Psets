/*
http://www.thegeekstuff.com/2012/10/gcc-compiler-options/?utm_source=feedburner
gcc -E main.c > main.i -> preprocessed output

gcc -S main.c > main.s -> assembly level output

gcc -E -C main.c -> only compiled code without any linking

gcc -save-temps main.c

*/

#include <stdio.h>

int main(int argc, char const *argv[])
{
	printf("hello world\n");
	return 0;
}