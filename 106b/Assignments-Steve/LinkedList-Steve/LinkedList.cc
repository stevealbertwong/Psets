/*
Linked List is just an array in C++ but from ground up

Vector, list, map are all abstraction, or one varaiable that 
let you reach all data

*/
#include "LinkedList.h"

LinkedList::LinkedList(){
	// front has to remain unchanged
	front = NULL; // initialize member variable to NULL
	
	/* or you could initialize to a node with 0 as data */
	// front = new ListNode;
	// front->data = 0
	// front->next = NULL
}

LinkedList::~LinkedList(){

}

void LinkedList::Add(int value){
	// initialize first node
	if (front = NULL){
		front = new ListNode(value);		
		// front->next = NULL;	// X explicitly say null -> already null by default
	}

	else{
		// traverse to last node where current->next is NULL
		ListedNode* current = front;
		while (current->next != NULL){
			current = current->next;
			current->next = new ListNode(value);						
		}
	}
}


int LinkedList::Get(int index){
	ListedNode* current = front;
	for (int i = 0; i < index; ++i)
	{
		current = current->next;
	}
	return current->data;
}

int LinkedList::Insert(int index, int value){
	ListNode* temp = new ListNode(value);
	
	ListNode* current = front;
	for (int i = 0; i < index-1; ++i) // walk the list (int index - 1) steps
	{
		current = current->next;
	}

	temp->next = current->next;
	current->next = temp;
}






void LinkedList::remove(int index){
	
	ListNode* current = front;
	for (int=0; i<index-1; i++){
		current = current->next;
	}
	current->next = current->next->next; // SO GENUIUS

};