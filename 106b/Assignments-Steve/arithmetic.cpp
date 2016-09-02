************************** HEADER
#ifndef ARITHMETIC_H
#define ARITHMETIC_H

class arithmetic
{
public:
	arithmetic();
	~arithmetic();
	getAbsolute(int data);

private:
	int data;	
};

#endif






************************** CLASS
#include "arithmetic.h"
// constructor
Arithmetic::Arithmetic(int data){
	this.data = data;
}


int Arithmetic::getAbsolute(int data){
	if (data<0){
		data = -data;
	}
	return data;
}





************************** CLIENT
#include "arithmetic.h"
#include "console.h"
#include <string>
using namespace std;

int main()
{
	int data = -5;
	Arithmetic a (data);

	cout<< a.getAbsolute() << endl;
	return 0;
}
