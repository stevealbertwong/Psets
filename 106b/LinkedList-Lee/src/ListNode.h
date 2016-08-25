/*
 * CS 106B, Cynthia Lee
 *
 * This file declares a "ListNode" structure to represent a single element
 * of data in a list and a pointer to another node.
 *
 * Code based on original by Marty Stepp
 */

#ifndef _listnode_h
#define _listnode_h

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

#endif
