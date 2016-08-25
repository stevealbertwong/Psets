/*
 * CS 106B, Cynthia Lee
 *
 * LinkedList.cpp implements the LinkedList class behavior declared in LinkedList.h.
 * Today's version implements all functionality except removal.
 *
 * Code based on original by Marty Stepp
 */

#include "LinkedList.h"

/*
 * Constructs a new empty list.
 */
LinkedList::LinkedList() {
    front = NULL;
}

/*
 * This destructor frees the memory that was allocated internally by the list.
 */
LinkedList::~LinkedList() {
    // TODO
}

/*
 * Appends the given value to the end of the list
 * Hardest part: traversal 
 */
void LinkedList::add(int value) {

    if (front == NULL) {
        // front is a ListNode*        
        // new ListNode(value) -> creates ListNode in memory and returns a ListNode* 
        front = new ListNode(value);


    } else {
        // *front is the actual front
        // *current is for traversal to the end of LinkedList to add ListNode
        ListNode *current = front;
        // if we change this statement into (current!=NULL)
        // traverse as usual since current is never NULL
        // but at the last node current will become NULL
        // i.e. at last node 
        // for (current->next != NULL) -> current stores a memory address and not over written
        // for (current != NULL) -> current becomes NULL

        // INTUITION: you want to get the last current->next to store new ListNode's memory address
        // stop current at the point where current->next = NULL
        // then updates current->next
        while (current->next != NULL) {
            current = current->next;
        }


        current->next = new ListNode(value);
    }

}


/*
 * Removes all values from the list.
 */
void LinkedList::clear() {
    // TODO
}






/*
 * Returns the value at the given 0-based index of the list.
 * Throws an integer exception if the given index is not between
 * [0 - size - 1], inclusive.
 */
int LinkedList::get(int index) const {
    // (1st node)
    ListNode* current = front;
    // (2nd, 3rd, 4th node) so only 3 times to get the 4th node
    for (int i = 0; i < index; i++) {
        current = current->next;
    }
    // current is pointing at the i'th node
    return current->data;
}







/*
 * Adds the given value just before the given 0-based index in the list,
 * shifting subsequent elements right as necessary to make room.
 * Throws an integer exception if the given index is not between
 * [0 - size], inclusive.  (If size is passed, appends to the end.)
 */
void LinkedList::insert(int index, int value) {
    if (index == 0) {
        ListNode* oldFront = front;
        front = new ListNode(value);
        front->next = oldFront;
    } else {
        ListNode* current = front;
        for (int i = 0; i < index-1; i++) {
            current = current->next;
        }
        // current is pointing at the (index-1)st node;
        // insert just AFTER current
        
        ListNode* newNode = new ListNode(value);
        newNode->next = current->next;
        current->next = newNode;
    }
}







/*
 * Returns true if there are no elements in the list.
 */
bool LinkedList::isEmpty() const {
    return front == NULL;
}

/*
 * Removes the element at the given 0-based index from the list,
 * shifting subsequent elements left as necessary to cover its slot.
 * Throws an integer exception if the given index is not between
 * [0 - size - 1], inclusive.
 */
void LinkedList::remove(int index) {
    // TODO
}

/*
 * Stores the given value at the given 0-based index in the list.
 * Throws an integer exception if the given index is not between
 * [0 - size - 1], inclusive.
 */
void LinkedList::set(int index, int value) {
    ListNode* current = front;
    for (int i = 0; i < index; i++) {
        current = current->next;
    }
    // current is pointing at the i'th node
    current->data = value;
}



/*
 * Returns the number of elements in the list.
 */
int LinkedList::size() const {
    int count = 0;
    ListNode* current = front;
    while (current != NULL) {
        count++;
        current = current->next;
    }
    return count;
}

/*
 * This helper throws an integer exception if the given index is not between
 * the given min/max indexes, inclusive.
 */
void LinkedList::checkIndex(int index, int min, int max) const {
    if (index < min || index > max) {
        throw index;
    }
}




/*
 * Prints a string representation of the list such as "{10, 20, 30, 40}".
 */
ostream& operator <<(ostream& out, LinkedList& list) {
    out << "{";

    LinkedList::ListNode *current = list.front;
    if (current != NULL) {
        out << current->data;
        while (current->next != NULL) {
            current = current->next;
            out << ", " << current->data;
        }
    }

    out << "}";
    return out;
}
