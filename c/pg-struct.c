/*

*/

#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>

struct Person
{
	int age;
	int height;
	char* name;
	int weight;
};


// why pointer size goes from 8 to 12 in struct ?? 
// => memory alignment !!!!!! 

struct P
{
	int name;	
	int namess;
	int namesss;
	int* na;
	int* nam;

};

struct Play
{
	int name;
	int* nam;

} ppp;


int main(int argc, char const *argv[])
{
	// statically allocate struct
	struct Person per;
	struct P pp;		
	printf("before size : %lu\n", sizeof(pp)); // or %zu
	printf("size of int ptr : %lu\n", sizeof(pp.nam)); // or %zu
	printf("size of int : %lu\n", sizeof(pp.name)); // or %zu
	per.age = 20;
	per.height = 180;
	per.weight = 60;
	per.name = "steve";
	per.name = "lemur hi";
	printf("%p\n", per);
	printf("%s\n", per.name);
	printf("%d\n", per.age);
	printf("%d\n", *(&per.age+1)); // accessing struct 2nd element
	printf("%p\n", (&per.age+1));
	printf("%p\n", (&per.age+2)); // does not work as int ptr arithmetic


	printf("after size : %lu\n", sizeof(pp));
	return 0;
};


	
