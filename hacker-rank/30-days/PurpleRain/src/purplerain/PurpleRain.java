/*
Every half second, a new raindrop (5*5 black oval) added to the top of window at random horizontal location
Raindrop falls down at a rate of 2px every 50ms
 */
package purplerain;
import acm.graphics.*;
import acm.program.GraphicsProgram;
import acm.util.*; // random generator
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author SteveAndrewWong
 */
public class PurpleRain extends GraphicsProgram {
    // static variable: initialized only once at the start of execution before instantiation 
    // e.g. public static void main()
    // belongs to the class, objects/instances only share a single copy
    static final int RAINDROP_SIZE =5;
    public void run() {
        ArrayList<GOval> drops = new ArrayList<>();       
        GOval raindrop = randomRaindrop(); 
        drops.add(raindrop);
        
        int ticks = 0;
        while (true){
            for (int i = 0; i < drops.size(); i++){
                drops.get(i).move(0,2);
                pause(50);
            }
//            raindrop.move(0,2);
//            pause(50);
            ticks ++;
            if (ticks % 10 == 0){
                GOval nextdrop = randomRaindrop();
                // raindrop = randomRaindrop(); overwrites previous GOval but since already added to arraylist
                drops.add(nextdrop);
            }           
    }        
    }     
    public GOval randomRaindrop(){
        //1st draw raindrop at random position
        int x = RandomGenerator.getInstance().nextInt(0, getWidth());  
        int y = 0;
        GOval raindrop= new GOval(x,y,RAINDROP_SIZE,RAINDROP_SIZE);
        raindrop.setFillColor(Color.yellow);
        raindrop.setFilled(true);
        add(raindrop);
        return raindrop;
        
    };
    

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//    }


    
}
