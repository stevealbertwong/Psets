/*
When writing a class...
1. think about private variables
*/
#include "ArrayList.h"


// Constructor and Destructor
ArrayList::ArrayList(){

	// like global variables
	arr = new int[10]();
	count = 0;
	capacity = 10;
}


// destructor: called when object is deleted by program
// freeing dynamically allocated memory
// ArrayList gets destroyed when main hits the end of scope }
// i.e. this code runs after main() finished 
ArrayList::~ArrayList(){
	delete[] arr;
}

// Add
void ArrayList::Add(int value){
	
	CheckResize(); // old arr deleted + arr pointing at bigger arr

	int[count] = value;
	count++;

	
}


// Insert
void ArrayList::Insert(int value, int index){
	for (int i = 0; i < count - index + 1; ++i)
	{
		arr[count - i] = arr[count - 1 - i]
	}
	arr[index -1] = value;
	count ++;	
}

// Get
int ArrayList::Get(int index){
	return arr[index];
}


// Set
void ArrayList::Set(int value, int index){
	arr[index] = value;
}

// Remove
void ArrayList::Remove(int index){
	for (int i = index; i < count; ++i)
	{
		arr[i] = arr[i+1];
	}
	count --;
}

// size
int size(){
	return capacity;

	for (int i = 0, i < capacity, i++){

	}
}

// remove

// toString
char  

// just copy the entire code you written
void ArrayList::CheckResize(){
	
	if (count = capacity){	
		capacity = capacity *2;
		int* temp = new int[capacity]();
		
		for (int i = 0; i < count; ++i)
		{
			temp[i] = arr[i]			
		}
		delete[] arr;
		arr =  temp;
	}
}



