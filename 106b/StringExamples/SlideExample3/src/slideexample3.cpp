#include <iostream>
#include "console.h"
#include "simpio.h"
using namespace std;

/** Example of string behavior in C++.
  * This code actually compiles even though we might expect
  * line 19 to have an error.
  * See lecture slides for further explanation of this code.
  *
  */
int main(){
    string s = "hello,";
    s += "dolly!";
    s += s + "why," + "hello,dolly!";
    s.append("hello");
    s.append("dolly!!!!!!");
    cout << s + '5' << endl;
    cout << "hello" + 96 << endl;
    return 0;
}


