#include "arithmetic.h"
#include <iostream>
#include "console.h"
using namespace std;



int main() {
    Arithmetic a(-5);
    cout << "|-5| = "
        << a.getAbsolute(-5) << endl;

    cout << "hello world" << endl;
    return 0;
}
