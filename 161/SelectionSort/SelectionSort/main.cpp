//
//  main.cpp
//  SelectionSort
//
//  Created by Steven Wong on 9/6/16.
//  Copyright © 2016 Steven Andrew Wong. All rights reserved.
//  

#include <iostream>
#include <vector>
using namespace std;


// you were thinking selection sort but you implemented bubble sort
// you basically keep swapping in a messy way until the 1st not smaller than any other number
void selectionSortFailed(vector<int>& v){

    for (int j = 0 ; j <v.size(); ++j) {
        
        int smallestNum = v[j];

        for (int i =0; i<v.size(); ++i) {
            if (v[i] < smallestNum){
                smallestNum = v[i]; // updates smallestNum for comparison
                swap(v[i], v[j]); // swap the smallest one with current position
            }
        }

    }
    for (int k=0; k<v.size(); ++k) {
        cout<<v[k]<<endl;
    }
    
}


// the main take away is I learn for loop int i = j instead of int i = 0
void selectionSort(vector<int>& v){
    
    for (int j = 0 ; j <v.size(); ++j) {
        
        int smallestNum = v[j];
        for (int i =j; i<v.size(); ++i) {
            if (v[i] < smallestNum){
                smallestNum = v[i]; // updates smallestNum for comparison
                swap(v[i], v[j]); // swap the smallest one with current position
            }
        }
    }
    
    
    for (int k=0; k<v.size(); ++k) {
        cout<<v[k]<<endl;
    }
    
}


int main(int argc, const char * argv[]) {
    
    int array [5] = {10123,213123,1334,98961,821};
    
    vector<int> value;
    for (int i=0; i<5; ++i) {
        value.push_back(array[i]);
    }
    
    //selectionSortFailed(value);
    selectionSort(value);
    
    
    
    
}
