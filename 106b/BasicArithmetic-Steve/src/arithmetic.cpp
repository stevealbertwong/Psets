#include "arithmetic.h"
#include <iostream>
//declaration copied here by import classname.h


//class definition

// construct
Arithmetic::Arithmetic(int n)
{   
    num = n;
}

// body of member function
int Arithmetic::getAbsolute(int num) {
    if (num < 0){
        return -num;
    }
    return num;
}

