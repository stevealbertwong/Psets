void LinkedList::add(int value){
while(current->next!=NULL){
	current = current->next; // walk till the end of the list
};
current->next = new ListNode(value);

int LinkedList::get(int index){
	ListNode* current = front; 	
	for (int i =0, i<index, i++){ // walk the list for (int index) steps 
		current = current->next;		
	}
	return current->data // dereference pointer to retrieve value	
};


void LinkedList::insert(int index, int value){
ListNode* current = front;
for (int i = 0, i< index-1, i++){ // walk the list (int index - 1) steps
	ListNode* temp = new ListNode(value);
	temp->next = current->next // point the new node to next node
	current->next = temp // update the current node to point to new node	
};

void LinkedList::remove(int index){
	
	ListNode* current = front;
	for (int=0; i<index-1; i++){
		current = current->next;
	}
	current->next = current->next->next; // SO GENUIUS

};