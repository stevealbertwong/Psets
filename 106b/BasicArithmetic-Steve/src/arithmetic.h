#ifndef ARITHMETIC_H
#define ARITHMETIC_H

#include <iostream>
using namespace std;

//class declaration

// class ClassName
class Arithmetic {

// public is copied into client program
public:

    // ClassName
    Arithmetic(int n); // constructor

    // returnType name(parameters);
    int getAbsolute(int n); // member function, behavior inside object

// private is copied into its own class
private:

    // type name; // member varaible, data inside each object
    int num;
};




#endif // ARITHMETIC_H
