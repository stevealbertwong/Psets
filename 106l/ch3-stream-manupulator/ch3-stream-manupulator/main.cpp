//
//  main.cpp
//  ch3-stream-manupulator
//
//  Created by Steven Wong on 9/3/16.
//  Copyright © 2016 Steven Andrew Wong. All rights reserved.
//

#include <iostream>
#include <fstream>
#include <iomanip> // cout << setw(10)
using namespace std;

// Global varaible could be called by any function
const int NUM_LINES = 4;
const int NUM_COLUMNS = 3;
const int COLUMN_WIDTH = 20;

// print dashes and plus
void PrintTableHeaderDoubleForLoop(){
    
    for (int j =0; j<NUM_COLUMNS; j++) {
        for (int i =0; i <COLUMN_WIDTH; i++) {
            cout << "-" ;
        }
        cout << "+" ;
    }
    cout << endl;

    
};


void PrintTableHeaderSetfill(){
    for (int i = 0; i <NUM_COLUMNS; ++i) {
        cout << setfill('-') << setw(COLUMN_WIDTH +1) << "+";
    }
    cout << setfill(' ') << endl;
}

// parse lines and words from ifstream
void PrintTableBody(string filename){
    
    ifstream input(filename.c_str());
    if (!input.is_open()){
        cerr << "failed to open file" << endl;
    }
    if (input.is_open()) {
        // cout << "succeeded in opening file" << endl;
    }
    
    
    // parse words + cout -> for loop 3 times as 3 lines
    int abc = 0;
    for (int i=0; i<NUM_LINES; ++i) {
        int intValue;
        double doubleValue;
        input >> intValue >> doubleValue;
        cout
        <<setw(COLUMN_WIDTH) << abc+1 << "|"
        <<setw(COLUMN_WIDTH) << intValue << "|"
        <<setw(COLUMN_WIDTH) << doubleValue << "|" << endl;
        abc++;
    }
    
    
    
};


int main(int argc, const char * argv[]) {
    
    string filename1 = "table-data.txt";
    
    // PrintTableHeaderDoubleForLoop();
    PrintTableHeaderSetfill();
    PrintTableBody(filename1);

    return 0;
}

