/*

ifstream -> source
ofstream -> destination

1. open a file 
2. read each line


1. create a ifstream data type i.e. a struct/endless contigous stack memory
2. open("file.txt") -> write bytes into stack memory, pointer arithmetic to walk the address, there is a null at the end of each sentence
3. getline // convert those bytes into string by creating a char* pointer until hit null, repeat
4. cout // traverse the linked list and print each char

*/

#include <fstream>

ifstream input;
input.open("abc.txt");
string line;
int sum = 0;
while (getline(input, line)){ 
	
	// input >> n; directly read from input, reading individual tokens 
	cout << line << endl;
	cout << n << endl;
	
	// or just sum += stringToInteger(line);
	int n = stringToInteger(line);
	sum = sum + n; // sum up all the numbers in the file
};
input.close();
