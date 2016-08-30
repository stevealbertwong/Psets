/*
 * CS 106B, Marty Stepp
 * Updates for SPR2016 by Cynthia Lee
 *
 * ArrayList is our example of implementing a basic data structure.
 * The list can store only ints for now.
 * See ArrayList.cpp for documentation of each member.
 */

#ifndef _arraylist_h
#define _arraylist_h

#include <iostream>
#include <string>
using namespace std;

class ArrayList {
public:
    // constructor and destructor
    ArrayList();
    ~ArrayList();
    
    // member functions (methods)
    void add(int value);
    void clear();
    int get(int index) const;
    void insert(int index, int value);
    bool isEmpty() const;
    void remove(int index);
    void set(int index, int value);
    int size() const;
    void debug() const;

private:
    // member variables (fields)
    int* myElements;   // array storing element data
    int mySize;        // number of elements in the array
    int myCapacity;    // array's length
    
    // helper methods
    void checkIndex(int index, int min, int max) const;
    void checkResize();
};

// overloaded operators
ostream& operator <<(ostream& out, ArrayList& list);

#endif
