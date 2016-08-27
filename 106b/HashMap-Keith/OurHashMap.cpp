#include "OurHashMap.h"
#include "error.h"
using namespace std;

/* * * * * Helper Functions * * * * */

/* 
 * Function hashCode
 * Usage: int code = hashCode(key)
 * ---------------------------------------------------------
 * An hash function for strings. Adapted from code online at
 * http://www.daniweb.com/software-development/cpp/threads/231987/string-hash-function
 * This function is called a SAX hash function (Shift-Add-XOR) and is
 * great at distributing strings evenly.
 */
const int kHashMask = 0x7FFFFFFF;
int hashCode(string key) {
    int hashVal = 0;
    for (int x = 0; x < key.length(); ++x) {
        hashVal ^= (hashVal << 5) +
                   (hashVal >> 2) +
                   key[x];
    }
    return hashVal & kHashMask;
}

/* * * * * Implementation of OurHashMap. * * * * */

const int kNumBuckets = 7;

/* Constructor sets up the array of buckets and sets up the
 * size to the default.
 */
OurHashMap::OurHashMap() {
	numBuckets = kNumBuckets;
	numElems = 0;
	
	/* Allocate space for the right number of buckets, then
	 * go and set the pointers in each bucket to NULL (because
	 * initially there is nothing in each bucket.
	 */
	buckets = new Cell* [numBuckets];
	for (int i = 0; i < numBuckets; i++) {
		buckets[i] = NULL;
	}
}

/* Destructor cleans up each individual linked list, then
 * deallocates the top-level array.
 */
OurHashMap::~OurHashMap() {
	/* Deallocate each linked list. */
	for (int i = 0; i < numBuckets; i++) {
		/* This code is the canonical "delete all cells in a linked
		 * list" code.
		 */
		Cell* curr = buckets[i];
		while (curr != NULL) {
			Cell* next = curr->next;
			delete curr;
			curr = next;
		}
	}
	
	/* Delete the top-level array. */
	delete[] buckets;
}

int OurHashMap::size() {
	return numElems;
}

bool OurHashMap::isEmpty() {
	return size() == 0;
}

bool OurHashMap::containsKey(string key) {
	/* Determine which bucket this key, if it exists here, must be in. */
	int bucketIndex = hashCode(key) % numBuckets;
	
	/* Use a standard "for loop over a linked list" to see if it's here. */
	for (Cell* curr = buckets[bucketIndex]; curr != NULL;
	     curr = curr->next) {
		if (curr->key == key) return true;
	}
	return false;
}

int OurHashMap::get(string key) {
	/* This logic is very similar to what we have in containsKey,
	 * except that it triggers an error if nothing is found.
	 */
	int bucketIndex = hashCode(key) % numBuckets;
	for (Cell* curr = buckets[bucketIndex]; curr != NULL;
	     curr = curr->next) {
		if (curr->key == key) return curr->value;
	}

	error("You must be a stranger in these here parts.");
}

void OurHashMap::put(string key, int value) {
	/* This key might already be here, in which case we should look up
	 * the key/value pair for it and update the value.
	 */
	int bucketIndex = hashCode(key) % numBuckets;
	for (Cell* curr = buckets[bucketIndex]; curr != NULL;
	     curr = curr->next) {
		if (curr->key == key) {
			curr->value = value;
			return;
		}
	}
	
	/* If we got here, the key isn't present and thus must be inserted. */
	
	// TODO: Rehash the elements before inserting a new one.
	// if (numElems / numBuckets >= 2) rehash();
	
	/* Create a new cell to hold the key/value pair. */
	Cell* cell = new Cell;
	cell->key = key;
	cell->value = value;
	
	/* Prepend this to the linked list representing this bucket. */
	cell->next = buckets[bucketIndex];
	buckets[bucketIndex] = cell;
	
	numElems++;
}

// TODO: Implement rehashing!
/*
void OurHashMap::rehash() {
	// TODO: Fill this in!
}

*/


