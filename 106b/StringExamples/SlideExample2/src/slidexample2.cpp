#include <iostream>
#include "console.h"
using namespace std;

int main(){
    cout << "|-5| = "
        << absoluteValue(-5) << endl;
    return 0;
}


int absoluteValue(int n) {
    if (n<0){
        return -n;
    }
    return n;
}



