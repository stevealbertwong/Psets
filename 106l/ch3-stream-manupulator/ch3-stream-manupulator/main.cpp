//
//  main.cpp
//  ch3-stream-manupulator
//
//  Created by Steven Wong on 9/3/16.
//  Copyright © 2016 Steven Andrew Wong. All rights reserved.
//

#include <iostream>
#include <fstream>
using namespace std;

int main(int argc, const char * argv[]) {

    ifstream input("table-data.txt");
    if (!input.is_open()){
        cerr << "failed to open file" << endl;
    }
    if (input.is_open()) {
        cout << "succeeded in opening file" << endl;
    }
    
    PrintTableHeader();
    PrintTableBody();

    return 0;
}

void PrintTableHeader(){
    
};

void PrintTableBody(){
    
};