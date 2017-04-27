/*
28 Jan 2017

http://stackoverflow.com/questions/197757/printing-pointers-in-c

http://web.mit.edu/10.001/Web/Course_Notes/c_Notes/tips_printf.html
*/

#include <stdio.h>

int main(int argc, char const *argv[])
{
	/* code */
	char s[] = "asd";
	char *c = "hello"; // read-only string
	char** p = &s[2];
	char **cc = &c;
	char ***ccc = &cc;

	printf("%s\n", s); // print string
	printf("%c\n", s[0]); // print string
	printf("%c\n", s[1]); // print string
	printf("%c\n", s[2]); // print string
	printf("%p\n", &s); // print pointer
	printf("%p\n", s); // print pointer
	// printf("%s\n", &s); // print string + warning format specifies type 'char *' but the argument has type 'char (*)[4]'
	printf("%s\n", &s[0]); // print pointer -> no warning
	printf("%p\n", &s[1]); // print pointer
	printf("%p\n", &s[2]); // print pointer
	printf("%p\n", p);
	printf("%s\n", c);
	printf("%p\n", c);
	printf("%p\n", cc);
	printf("%p\n", ccc);
	
	return 0;
}