/*
Array: reference data structure -> pointers and address
thats why you "overwrite" its value

3 ways to initialize/declare arrays
    1. int[] intArray1;
    2. int[] intArray2 = new int[4]; // size of array is 4 
    3. int[] intArray3 = {1,2,3,4};
*/
package arraypractice;

import java.util.Arrays;
import java.math.*;



/**
 *
 * @author SteveAndrewWong
 */
public class ArrayPractice {
    
    public static void printArray(int[] array){
       System.out.print(Arrays.toString(array) + "\n");
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] intArray1;
        int[] intArray3 = {1,2,3,4};
        int[] intArray2 = new int[4];
        int outOfBounds = intArray3[3];
        System.out.print(Arrays.toString(intArray2) + "\n");
        System.out.print(Arrays.toString(intArray3) + "\n");
        printArray(intArray3);
        System.out.print(outOfBounds + "\n");
        
        int randomNumber = Math.abs(rand.nextInt);
        
    }
    
}
