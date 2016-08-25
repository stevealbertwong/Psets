/*
 * File: TestOurSet.cpp
 *
 * Contains test code for OurSet.
 */

#include "OurSet.h"
#include "simpio.h"
using namespace std;

int main() {
    // Create a set and add a bunch of strings:
    OurSet s;
    s.add("Happy");
    s.add("Doc");
    s.add("Grumpy");
    s.add("Sleepy");
    s.add("Prometheus");
    s.add("Fabio");
    s.add("Lt. Dan ICE CREAM");
    s.add("Art Garfunkel");
    s.add("Zeus");
    
    // Print out some basic information about the set:
    cout << "Printing all " << s.size() << " elements:" << endl;
    s.printAll();
    cout << endl;
    
    // Allow the user to enter names to look up:
    while (true) {
        string str = getLine("Enter a string to look up: ");
        if (str.empty()) break;
        
        if (s.contains(str)) {
            cout << "> '" << str << "' is in the set!" << endl;
        } else {
            cout << "> '" << str << "' is NOT invited." << endl;
        }
        cout << endl;
    }
    
    return 0;
}

