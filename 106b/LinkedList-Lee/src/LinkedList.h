/*
 * CS 106B, Cynthia Lee
 *
 * LinkedList is an example of a second implementation of a basic data structure.
 * A LinkedList is a sequential collection of integers stored with 0-based integer
 * indexes and internally represented as a list of linked node structures.
 * 
 * See LinkedList.cpp for detailed documentation of each member.
 *
 * Code based on original by Marty Stepp
 */

#ifndef _linkedlist_h
#define _linkedlist_h

#include <iostream>
#include <string>
using namespace std;


class LinkedList {
public:
    LinkedList();
    ~LinkedList();
    
    void add(int value);
    void clear();
    int get(int index) const;
    void insert(int index, int value);
    bool isEmpty() const;
    void remove(int index);
    void set(int index, int value);
    int size() const;
    
    friend ostream& operator <<(ostream& out, LinkedList& list);

private:

    /*
     * The internal structure representing a single node of data.
     */
    struct ListNode {
        int data;         // element stored in each node
        ListNode* next;   // pointer to the next node (NULL if none)

        ListNode(int d = 0, ListNode* n = NULL) {
            data = d;
            next = n;
        }
    };

    ListNode* front;
    
    /*
     * This helper throws a string exception if the given index is not between
     * the given min/max indexes, inclusive.
     */
    void checkIndex(int index, int min, int max) const;
};

#endif
