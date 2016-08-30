/*
 * CS 106B, Marty Stepp
 * Updates for SPR2016 by Cynthia Lee
 *
 * ArrayList.cpp implements the ArrayList class behavior declared in ArrayList.h.
 *
 * We did not have time in class to implement several features:
 * - resizing when array is full
 * - << operator
 * - destructor
 * But their solutions are shown here for completeness.
 */

#include "ArrayList.h"
#include "strlib.h"
using namespace std;

/*
 * Constructs a new empty list (capacity 10).
 */

static const int INITIAL_CAPACITY = 10;

ArrayList::ArrayList() {
    myCapacity = INITIAL_CAPACITY;
    mySize = 0;
    myElements = new int[myCapacity];
}

/*
 * Destructor is called when an ArrayList object is destroyed
 * (when the closing } brace is reached in the function where
 * it is declared).
 */
ArrayList::~ArrayList() {
    delete [] myElements;
    // we don't need to update mySize and myCapacity because
    // this object is ceasing to exist right now anyway
}

/*
 * Appends the given value to the end of the list.
 */
void ArrayList::add(int value) {
    checkResize();
    myElements[mySize] = value;
    mySize++;
}

/*
 * Removes all elements from the list.
 */
void ArrayList::clear() {
    mySize = 0;
}

/*
 * Returns the value at the given 0-based index of the list.
 */
int ArrayList::get(int index) const {
    return myElements[index];
}

/*
 * Adds the given value just before the given 0-based index in the list,
 * shifting subsequent elements right as necessary to make room.
 * Throws a string exception if the index is out of bounds.
 */
void ArrayList::insert(int index, int value) {
    checkIndex(index, 0, mySize);
    checkResize();
    for (int i = mySize; i > index; i--) {
        myElements[i] = myElements[i - 1];
    }
    myElements[index] = value;
    mySize++;
}

/*
 * Returns true if there are no elements in the list.
 */
bool ArrayList::isEmpty() const {
    return mySize == 0;
}

/*
 * Removes the element at the given index from the list,
 * shifting elements left to make room.
 * Throws a string exception if the index is out of bounds.
 */
void ArrayList::remove(int index) {
    checkIndex(index, 0, mySize - 1);
    for (int i = index; i < mySize; i++) {
        myElements[i] = myElements[i + 1];
    }
    mySize--;
}

/*
 * Stores the given value at the given index in the list.
 * Throws a string exception if the index is out of bounds.
 */
void ArrayList::set(int index, int value) {
    checkIndex(index, 0, mySize - 1);
    myElements[index] = value;
}

/*
 * Returns the number of elements in the list.
 */
int ArrayList::size() const {
    return mySize;
}

/*
 * Prints the list to the given output stream, in a format such as:
 * {42, -7, 19, 106}
 */
ostream& operator <<(ostream& out, ArrayList& list) {
    out << "{";
    if (!list.isEmpty()) {
        out << list.get(0);   // fencepost
        for (int i = 1; i < list.size(); i++) {
            out << ", " << list.get(i);
        }
    }
    out << "}";
    return out;
}

/*
 * Throws a string exception if the given index is not between
 * min and max, inclusive.
 */
void ArrayList::checkIndex(int index, int min, int max) const {
    if (index < min || index > max) {
        throw "Index out of bounds: " + integerToString(index);
    }
}

/*
 * Grows the internal array to twice its current size if it has
 * run out of space.
 */
void ArrayList::checkResize() {
    if (mySize == myCapacity) {
        // copy all elements into a bigger array
        int* bigger = new int[2 * myCapacity];
        for (int i = 0; i < mySize; i++) {
            bigger[i] = myElements[i];
        }
        
        // swap in the new bigger array for the old one
        delete[] myElements;
        myElements = bigger;
        myCapacity *= 2;
    }
}
