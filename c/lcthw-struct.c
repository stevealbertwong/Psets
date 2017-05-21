/*
ex16

static per.name
dynamic : per->name



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

struct Person* create_person(char* name, int age, int height, int weight){

	struct Person* per = malloc( sizeof(struct Person) );
	// per->name = name; // strdup(name) => WHY ???? if just string cannot free ?????
	per->name = strdup(name);
	per->age = age;
	per->height = height;
	per->weight = weight;
	return per;

};

// ** free all pointer related stuff in struct
void destroy_person(struct Person* per){
	assert(per != NULL);
	free(per->name);
	free(per);
};


int main(int argc, char const *argv[])
{
	struct Person* steven = create_person("steven", 28, 180, 60);
	assert(steven != NULL);
	
	printf("%s\n", steven->name);
	destroy_person(steven);
	// printf("%d\n", steven->age);

	return 0;
};