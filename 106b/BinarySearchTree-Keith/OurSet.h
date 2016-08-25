/*
 * File: OurSet.h
 *
 * The purpose of a header file is to hold declarations for other files to use
 */

#ifndef OurSet_Included
#define OurSet_Included

#include <string>
using namespace std;

class OurSet {
public:
    // Constructor and destructor to make sure our Set is initialized and
    // destroyed properly.
    OurSet();
    ~OurSet();
    
    // size returns the number of elements currently stored in the set.
    // isEmpty returns whether or not the size is zero.
    int size();
    bool isEmpty();
    
    // contains returns true if and only if the specified value can be found in
    // the set.
    bool contains(string value);
    
    // add inserts the specified value into the set.
    void add(string value);
    
    // printAll prints each element of the set, in sorted order.
    void printAll();

private:
    // The Node defines the structure which holds a single element in the binary
    // search tree. It contains two links (left and right); left is a pointer to
    // the root of a binary search tree with all elements smaller than value,
    // while right is a pointer to the root of a binary search tree with all
    // elements greater than value.
    struct Node {
        string value;
        Node* left;
        Node* right;
    };
    
    // root is a pointer to the topmost Node in this binary search tree. This is
    // analogous to the head pointer in a linked list; you can use it to reach
    // all of the other elements in the tree.
    Node* root;
    int numElements;
    
    void addHelper(Node*& curr, string value);
    void printAllHelper(Node* curr);
    void deleteHelper(Node* curr);
};

#endif
