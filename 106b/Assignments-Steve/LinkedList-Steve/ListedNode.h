/* 
A struct is basically a class, making a new data type
but usually smaller than class
by default have only public member variables and no functions

honestly you could use a class, but convention is for a little baby object
we use struct

A historical holdover from C which does not have class

DOES NOT have constructor/initialized global variables

Recursive class?
*/
#ifndef LISTNODE_H
#define LISTNODE_H value


struct ListNode
{
	int data;
	ListNode* next
};

#endif