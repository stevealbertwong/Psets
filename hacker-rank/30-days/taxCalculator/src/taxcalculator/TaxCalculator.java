import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class TaxCalculator {

    public static void main(String[] args) {
    
      Scanner sc = new Scanner(System.in); // aka token or input
      // new Scanner(new File(filename)); 
      double M = sc.nextDouble(); // original meal price
      int T = sc.nextInt(); // tip percentage
      int X = sc.nextInt(); // tax percentage
      
      double finalPrice = M + M * T/100 + M * X/100;        
      int total = (int) Math.round(finalPrice);
      System.out.println("The final price of the meal is $" + total + ".");

      
    }
}
