/* Constants, Random */

/*
Scope - lifetime of a variable
i.e. where does that variable exists and where could i refer to the variable

initialize variable means giving its name, type and value

An initialized variable only exists within {} 
only start counting when intialized
int sum;
	for (int i = 0, i<10, i++){
		sum = sum + 1;
	}
println("the sum is" + sum); // there will be compiler error since not initialize

int sum = sum +1 vs sum = sum + 1
int sum -> create a new variable make memory and give it a name
sum -> refer to the old variable
Java if you dont initialize variable with a value, it does not
know what it is and it will give it an error
C if you dont initialze with a value it gives a random value -> HAPPY DEBUG TIME

global variable


*/

// a sentinel loop, repeats until a sentinel value is seen e.g. until user type 0

import acm.program.*;

public class sentinel extends ConsoleProgram{
	public void run{
		int sum =0;
		int n = 1; // random value except sentinel value just to initialize
		while (n!=0){ // sentinel value
			n = readInt("type a number");
		};
	};
};