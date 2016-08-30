/*
 * CS 106B, Marty Stepp
 * Updates for SPR2016 by Cynthia Lee
 *
 * This program is a client that uses our ArrayList class.
 */

#include <iostream>
#include "console.h"
#include "simpio.h"
#include "ArrayList.h"

using namespace std;

void testAdd(ArrayList& list);
void testRemove(ArrayList& list);
void testMisc(ArrayList& list);
void testResize(ArrayList& list);
void testAssign(ArrayList& list);
void testException(ArrayList& list);
void testLeak();

int main() {
    ArrayList list;
    testAdd(list);
    testMisc(list);
    testRemove(list);
    testException(list);
    testResize(list);
    testLeak();

    cout << "All tests completed." << endl;
    return 0;
}

/*
 * Test cases that call the add method.
 */
void testAdd(ArrayList& list) {
    ArrayList list2;
    
    cout << "add:" << endl;
    list.add(42);
    list.add(-5);
    list.add(17);
    list.add(28);
    cout << list << ", size " << list.size() << endl;
    cout << endl;

    cout << "insert:" << endl;
    list.insert(1, 111);
    cout << list << ", size " << list.size() << endl;
    list.insert(4, 444);
    cout << list << ", size " << list.size() << endl;
    list.insert(0, 0);
    cout << list << ", size " << list.size() << endl;
    list.insert(7, 7777);
    cout << list << ", size " << list.size() << endl;
    cout << endl;
}

/*
 * Test cases that call the remove method.
 */
void testRemove(ArrayList& list) {
    list.clear();
    list.add(10);
    list.add(20);
    list.add(30);
    list.add(40);
    list.add(50);
    cout << "remove:" << endl;
    list.remove(2);
    cout << list << ", size " << list.size() << endl;
    list.remove(list.size() - 1);
    cout << list << ", size " << list.size() << endl;
    list.remove(0);
    cout << list << ", size " << list.size() << endl;
    cout << endl;
}

/*
 * Test cases that call methods like get, set, and clear.
 */
void testMisc(ArrayList& list) {
    cout << "get:" << endl;
    for (int i = 0; i < list.size(); i++) {
        cout << "index " << i << ": " << list.get(i) << endl;
    }
    cout << endl;

    cout << "set:" << endl;
    list.set(2, 222);
    cout << list << ", size " << list.size() << endl;
    list.set(0, 12345);
    cout << list << ", size " << list.size() << endl;
    cout << endl;

    cout << "clear:" << endl;
    list.clear();
    cout << list << ", size " << list.size() << endl;
    cout << endl;
}

/*
 * Test case that adds many elements so that the list needs to resize.
 */
void testResize(ArrayList& list) {
    cout << "resize:" << endl;
    for (int i = 1; i <= 25; i++) {
        list.add(i);
        cout << list << ", size " << list.size() << endl;
    }
    cout << endl;
}

/*
 * Tries adding and removing at extreme indexes and makes sure an exception is thrown.
 */
void testException(ArrayList& list) {
    cout << "index checking:" << endl;
    try {
        list.insert(-3, 99999);
        cout << "fail: Insert at negative index didn't throw  :-(" << endl;
    } catch (...) {
        cout << "pass: Insert at negative index threw an exception!" << endl;
    }
    
    try {
        list.insert(list.size() + 1, 99999);
        cout << "fail: Insert at too-big index didn't throw  :-(" << endl;
    } catch (...) {
        cout << "pass: Insert at too-big index threw an exception!" << endl;
    }
    try {
        list.remove(-1);
        cout << "fail: Remove at negative index didn't throw  :-(" << endl;
    } catch (...) {
        cout << "pass: Remove at negative index threw an exception!" << endl;
    }
    
    try {
        list.remove(list.size());
        cout << "fail: Remove at too-big index didn't throw  :-(" << endl;
    } catch (...) {
        cout << "pass: Remove at too-big index threw an exception!" << endl;
    }
}

/*
 * Declares a large number of lists to see if any memory is leaking.
 */
void testLeak() {
    for (int i = 0; i < 10000; i++) {
        ArrayList list;
    } // free memory?
    getLine("Done memory leakin'.  Press Enter ...");
}
