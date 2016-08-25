/*
 * File: OurSet.cpp
 *
 * Implementation details for OurSet.
 */

#include "OurSet.h"
#include <iostream>
using namespace std;

// The constructor of our set needs to initialize the instance variables to be
// reasonable values. If we don't set root to NULL and numElements to 0, they
// will hold garbage values.
OurSet::OurSet() {
    root = NULL;
    numElements = 0;
}

// We didn't get to implementing the destructor in class. It serves as a wrapper
// function which calls deleteHelper, which will recursively clean up the tree.
OurSet::~OurSet() {
    deleteHelper(root);
}

// deleteHelper takes in a node it should clean up. Since this node may have
// binary search trees hanging off of it (accessible through left/right
// pointers), we have to make sure we recursively clean up both of those trees
// first before freeing the memory for curr itself.
void OurSet::deleteHelper(Node* curr) {
    if (curr != NULL) {
        deleteHelper(curr->left);
        deleteHelper(curr->right);
        delete curr;
    }
}

// size simply returns the number of elements in the tree, which we're tracking.
// Here's a recursive way to count up the elements, which we could use if we
// didn't have numElements:
//
//   int OurSet::sizeHelper(Node* curr) {
//       if (curr == NULL) {
//           return 0;
//       }
//
//       return sizeHelper(curr->left) + sizeHelper(curr->right) + 1;
//   }
//
// We can recursively describe the size of a binary search tree as the number of
// elements in the left sub-tree, plus the number of elements in the right sub-
// tree, plus one for curr itself.
int OurSet::size() {
    return numElements;
}

// isEmpty returns what you think it returns.
bool OurSet::isEmpty() {
    return size() == 0;
}

// contains looks up the desired value in the tree. It does so iteratively. Here
// is an alternate recursive approach:
//
//   bool OurSet::containsHelper(Node* curr, string value) {
//       if (curr == NULL) {
//           return false;
//       }
//       if (value == curr->value) {
//           return true;
//       }
//
//       if (value < curr->value) {
//           return containsHelper(curr->left, value);
//       } else {
//           return containsHelper(curr->right, value);
//       }
//   }
//
// Here, we can say that a binary search tree contains the desired value if:
//   a) the root node, curr, has that value OR
//   b) one of the two sub-trees has that value
//
// The definition of a binary search tree constrains us to only searching one of
// the two sub-trees, based on how value compares to curr->value.
bool OurSet::contains(string value) {
    Node* curr = root;
    while (curr != NULL) {
        if (value == curr->value) {
            return true;
        } else if (value < curr->value) {
            curr = curr->left;
        } else {
            curr = curr->right;
        }
    }
    
    return false;
}

// add is defined recursively. It can be implemented iteratively, but the result
// ends up being rather ugly. Instead, we'll treat add as a wrapper function
// which invokes addHelper, the recursive function which does the adding.
void OurSet::add(string value) {
    addHelper(root, value);
}

// addHelper finds the first available place in the binary search tree where
// value belongs. As a result, we can't stop searching until we discover that
// value is already in the tree (in which case, we don't insert it at all), or
// until we reach a point where curr is NULL.
//
// When curr is NULL, we've found where the new node belongs. However, there's
// a subtlety here: the previous recursive call invoked addHelper on some node's
// left or right child. Either way, we want to re-assign that left/right pointer
// to point to the new node we're inserting. In order to accomplish this, we
// need to pass curr in as a pointer by reference.
void OurSet::addHelper(Node*& curr, string value) {
    if (curr == NULL) {
        curr = new Node;
        curr->value = value;
        curr->left = NULL;
        curr->right = NULL;
        numElements++;
    }
    
    if (value == curr->value) {
        return;
    } else if (value < curr->value) {
        addHelper(curr->left, value);
    } else {
        addHelper(curr->right, value);
    }
}

// printAll should print the contents of the Set in alphabetical order. It
// serves as a wrapper function around printAllHelper, which recursively does
// the printing.
void OurSet::printAll() {
    printAllHelper(root);
}

// printAllHelper works as follows: in order to make sure curr is printed in the
// right place, we need to make sure everything smaller than curr->value is
// printed first; then we can print curr->value; finally, we can print
// everything that's larger than curr->value. The smaller values are in curr's
// left sub-tree, while the larger values are in curr's right sub-tree.
void OurSet::printAllHelper(Node* curr) {
    if (curr != NULL) {
        printAllHelper(curr->left);
        cout << curr->value << endl;
        printAllHelper(curr->right);
    }
}
