/*
When writing a class...
1. think about private variables
*/
#include "ArrayList.h"


// Constructor and Destructor

ArrayList::ArrayList(){
	arr = new int[10]();
	count = capacity = 0;
}

ArrayList::~ArrayList(){

}

// Add
void ArrayList::Add(int value){
	if (count = 0){
		int* arr = new int[capacity]();
		arr[0] = value;
	}

	else {
		int[count] = value;
		count++;
	}

}

// Get
int ArrayList::Get(int index){
	return arr[index];
}


// Set

// size
int size(){
	return capacity;

	for (int i = 0, i < capacity, i++){

	}
}

// remove

// toString
char  

