package reversearray;
import java.util.*;
import java.io.*;
import java.lang.String;
import java.lang.reflect.Array;

/**
 *
 * @author SteveAndrewWong
 */
public class ReverseArray {

     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//        Array arr <String> = new Array <String>;
//        
//        
//        Scanner sc = new Scanner(new File(poem.txt));
//        while (sc.hasNextLine()){
//            int j = number of lines of poem.txt;
//            String line = sc.getline();
//            arr[j] = line;
//            j--;
//
//        for (int i = 0, i > arr.getsize(), i++){
//            System.out.print(arr[i]);
//        }
//    }
        ArrayList <String> list = new ArrayList();
        String filename = readLine("type a file name");
        Scanner sc = new Scanner(new File(filename);
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            list.add(line);
        }
        
        // simple for loop
        // for (String line : list){ list.get(i)};
        
        for (int i=list.size() - 1 ; i>=0; i-- ){
            list.get(i);
        }
            
        }
        

    
}
