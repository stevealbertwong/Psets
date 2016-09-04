//
//  main.cpp
//  helloworld
//
//  Created by Steven Wong on 9/3/16.
//  Copyright © 2016 Steven Andrew Wong. All rights reserved.
//

#include <iostream> // cin, cout -> stream object
#include <fstream> //ifstream, ofstream -> types i.e. create an object of type ifstream/ostream
using namespace std;

int main(int argc, const char * argv[]) {

    // cout is console object connected to console/screen hardware
    cout << "Hello, World!\n" << endl;
    cout << "Enter an integer, press enter, then enter a string!\n" << endl;
    int integer;
    string stringyeayea;
    // cin is console object connected to the keyboard
    cin >> integer >> stringyeayea;
    cout << integer << stringyeayea << endl;
    
    // ifstream, ofstream
    ifstream mystream;
    ifstream mystream2;
    ofstream myofstream;
    int streamInt;
    string filenamestring = "file.txt";
    
    mystream.open("file.txt"); // shorten to be just ifstream mystream("file.txt")
    mystream2.open(filenamestring.c_str()); // stream class predates string class, need to convert new string to old cstring for old stream

    // or if (!mystream.is_open()) {cerr <<"file failed to open"<< endl;}for debugging
    if (mystream.fail()){
        cout << "File failed to open." << endl;}
    
    // print to console
    // file.txt >> ifstream mystream >> int streamInt >> cout
    mystream >> streamInt;
    cout << "printing file.txt" << streamInt << endl;
    
    // print to file
    myofstream.open("outputfile.txt");
    myofstream << streamInt;
    
    return 0;
    
    
}
