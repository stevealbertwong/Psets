#ifndef _arraylist_h
#define _arraylist_h

class ArrayList
{
public:
	ArrayList();
	~ArrayList();
	void add(int value);
	void insert(int value, int index);
	void set(int value, int index);
	int get(int index);
	bool isEmpty();
	int size();
	void clear();




private:
	// private variable that has fixed value
	// in constructor
	// code that runs only in class but not client
	int* arr;
	int count;
	int capacity;

	void checkresize();

};

// overloaded operator 
ostream& operator

#endif