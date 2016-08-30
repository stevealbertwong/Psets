#include <iostream>
#include "console.h"
using namespace std;

static int absoluteValue(int n);

int main() {
    setConsoleFont("Arial-20");
    cout << "|-5| = "
         << absoluteValue(-5) << endl;
    return 0;
}


static int absoluteValue(int n) {
    if (n < 0){
        return -n;
    }
    return n;
}
