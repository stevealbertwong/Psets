/*
 * CS 106B, Cynthia Lee
 *
 * This program is a client that uses our LinkedList class.
 *
 * Code based on original by Marty Stepp
 */

#include <iostream>
#include "console.h"
#include "LinkedList.h"

using namespace std;

void testAdd(LinkedList& list);
void testRemove(LinkedList& list);
void testMisc(LinkedList& list);
void testResize(LinkedList& list);
void testAssign(LinkedList& list);
void testException(LinkedList& list);

int main() {
    LinkedList list;

    testAdd(list);
    // testMisc(list);
    // testResize(list);
    // testRemove(list);
    // testAssign(list);
    // testException(list);

    cout << "exiting main" << endl;
    return 0;
}

void testAdd(LinkedList& list) {
    cout << "add:" << endl;
    list.add(42);
    list.add(-5);
    list.add(17);
    list.add(28);
    cout << list << ", size " << list.size() << endl;
    // list.debug();
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

void testRemove(LinkedList& list) {
    cout << "remove:" << endl;
    list.remove(2);
    cout << list << ", size " << list.size() << endl;
    list.remove(list.size() - 1);
    cout << list << ", size " << list.size() << endl;
    list.remove(0);
    cout << list << ", size " << list.size() << endl;
    cout << endl;
}

void testMisc(LinkedList& list) {
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

void testResize(LinkedList& list) {
    cout << "resize:" << endl;
    for (int i = 1; i <= 25; i++) {
        list.add(i);
        cout << list << ", size " << list.size() << endl;
        // list.debug();
    }
    cout << endl;
}

void testAssign(LinkedList& list) {
    cout << "assignment:" << endl;
    list.clear();
    for (int i = 1; i <= 5; i++) {
        list.add(i * 10);
    }
    LinkedList list2 = list;
    cout << "list    : " << list << ", size " << list.size() << endl;
    // list.debug();
    cout << "list2   : " << list2 << ", size " << list2.size() << endl;
    // list2.debug();
    cout << endl;

    list2.add(60);
    list2.set(1, 99);
    list.remove(3);
    cout << "list    : " << list << ", size " << list.size() << endl;
    // list.debug();
    cout << "list2   : " << list2 << ", size " << list2.size() << endl;
    // list2.debug();
    cout << endl;
}

void testException(LinkedList& list) {
    cout << "index checking:" << endl;
    list.insert(-3, 99999);
}
