// header for declaration + class + contructor + public method private member

// DEFINE CLASS ARITHMETIC 
// header guard -> prevent copy class twice into client 
#ifndef ARITHMETIC_H // if class arithmetic not defined 
#define ARITHMETIC_H // then define class arithmetic

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

/*
PUBLIC vs PRIVATE
Data is private so each time created once, then never be changed/called in client

Method and constructor are public because you call them in client
*/
private:

    // type name; // member varaible, data inside each object
    int num;
};




#endif // ARITHMETIC_H
