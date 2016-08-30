#include <iostream>
#include "console.h"
#include "simpio.h"
using namespace std;

/* Illustrates several uses of concatenation of strings
 * in C++. This code all compiles.
 */
int main(){
    string s;
    s = "hello!";               //string knows how to convert literal
    s = s + "1234" + "6789";    //string has + defined as concatenation
    s = "1234" + s + "5678" + s; //works in either order
    char ch = 'A';
    s += ch;                    //string knows how to interact with individual char
    s += 'A';                   //and char literal

    cout << s << endl;

    getLine("Press ENTER to quit!");
    return 0;
}

