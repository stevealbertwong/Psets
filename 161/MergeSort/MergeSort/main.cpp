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
        cout << "leftHead: " << lh <<  "rightHead:" << rh << "length:" << len << endl;
        
        mergeSort(arr, lh, len); // recursion this one first -> stack
        mergeSort(arr, rh, len);

        cout << "this is process :  " <<  "leftHead: " << lh <<  "rightHead:" << rh << "length:" << len << endl;
        
        
        
        
        
        // there are 8 sets of lh, len and rh at this point
        int temp [len*2]; // you are dealing with your own set of len here
        
        int lhCopy = lh;
        int rhCopy = rh;
        for (int i =0; i < len*2; i++) {
            
            // if both arrays are in bounds
            if (lh < lhCopy+len && rh < rhCopy+len){
                // if left smaller
                if (min(arr[lh], arr[rh])== arr[lh]){
                    temp[i] = arr[lh];
                    lh++; // go to the next left
                // if right smaller
                } else if (min(arr[lh], arr[rh])== arr[rh]){
                    temp[i] = arr[rh];
                    rh++;
                }
            // however if anyone goes out of bounds -> just append all of the other array
            } else if (lh >= lhCopy +len || rh >= rhCopy + len){
                if (lh >= lhCopy +len) {
                    temp[i] = arr[rh];
                    rh++;
                } else if (rh >= rhCopy + len){
                    temp[i] = arr[lh];
                    lh++;
                }
            }
            cout << "this is temp" << temp[i] << endl;
        }
        
        // replace the real array with temp array that stores sorted numbers
        for (int i = 0; i<len*2; ++i) {
            arr[lhCopy] = temp[i];
            // cout << temp[i] <<endl;
            lhCopy++;
        }
        
    }
    
}


int main(int argc, const char * argv[]) {
    
    int arr[] = {8,4,2,7,1,6,3,5};
    int leftHead = 0;
    int length = 8;
    
    mergeSort(arr, leftHead, length);

    for (int i = 0; i<8; ++i) {
        cout<< arr[i]<< endl;
    }
    return 0;
}
