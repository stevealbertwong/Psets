#ifndef LINKEDLIST_H
#define LINKEDLIST_H value

class LinkedList
{
public:
	LinkedList();
	~LinkedList();

private:
	// like a class within a class
	struct ListNode
	{
		int data;
		ListNode* next;
	};
	
	ListNode* front;
};
#endif