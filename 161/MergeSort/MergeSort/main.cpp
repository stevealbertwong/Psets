//
//  main.cpp
//  MergeSort
//
//  Created by Steven Wong on 9/7/16.
//  Copyright © 2016 Steven Andrew Wong. All rights reserved.
//

#include <iostream>
using namespace std;

void mergeSort(int* arr, int leftHead, int length){
    
    if(length == 1){
        return;
    }
    
    // get leftHead, midDistance -> rightHead i.e. another start of the array
    
    else{
        int lh = leftHead; // 0
        int len = length/2;// 4
        int rh = lh + len; // 4
        cout << "leftHead: " << lh << "length:" << len << "rightHead:" << rh << endl;
        
        mergeSort(arr, lh, len); // recursion this one first -> stack 
        mergeSort(arr, rh, len);
    }
}




int main(int argc, const char * argv[]) {
    
    int arr[] = {8,4,2,7,1,6,3,5};
    int leftHead = 0;
    int length = 8;
    
    mergeSort(arr, leftHead, length);
    return 0;
}
