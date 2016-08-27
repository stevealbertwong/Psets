/****************************************************
 * File: OurHashMap.h
 *
 * A HashMap class that implements a map from strings
 * to integers.
 */
#ifndef OurHashMap_Included
#define OurHashMap_Included

#include <string>
using namespace std;

class OurHashMap {
public:
	OurHashMap();
	~OurHashMap();
	
	void put(string key, int value);
	bool containsKey(string key);
	
	/* Returns the value associated with a given key.  If the
	 * key is not found, signals an error.
	 */
	int get(string key);
	
	/* Returns how many elements are in the map. */
	int size();
	bool isEmpty();

private:
	struct Cell {
		string key;
		int value;
		Cell* next;
	};
	
	/* Pointer to the array of buckets. */
	Cell** buckets;
	
	/* The number of buckets. */
	int numBuckets;
	
	/* The number of total key/value pairs in the HashMap. */
	int numElems;
	
	/* TODO: Implement rehashing to improve performance! */
	// void rehash();
};


#endif
