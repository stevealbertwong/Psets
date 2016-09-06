//
//  main.cpp
//  InsertionSort
//
//  Created by Steven Wong on 9/6/16.
//  Copyright © 2016 Steven Andrew Wong. All rights reserved.
//

#include <iostream>
#include <vector>
#include <queue>
using namespace std;

void insertionSort(vector<int>& v){
    vector<int> sortedArray;
    for (int i =0; i<v.size(); ++i) {
        sortedArray.push_back(v[i]);
        
        // implementation of sorted queue
        for (size_t j = sortedArray.size()-1; j>0; --j) {
            // last term smaller than previous term -> keep going to previous term until hit a smaller term
            if (sortedArray[j] < sortedArray[j-1]) {
                swap(sortedArray[j], sortedArray[j-1]);
            }
        }
    }
    
    for (int i = 0; i<sortedArray.size(); ++i) {
        cout<<sortedArray[i]<<endl;
    }

}

int main(int argc, const char * argv[]) {
    int array [6] = {2,5,6,1,3,4};
    vector<int> values;
    for (int i =0; i<6; ++i) {
        values.push_back(array[i]);
    }
    insertionSort(values);
    
    return 0;
}
