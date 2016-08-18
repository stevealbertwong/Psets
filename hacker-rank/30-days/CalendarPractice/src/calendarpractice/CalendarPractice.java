package calendarpractice;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author SteveAndrewWong
 */
public class CalendarPractice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Calendar today = Calendar.getInstance();
        Date time = today.getTime();
        System.out.println(time);
        
        // change format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String date = format.format(time);
        System.out.println(date);
        
        // get time zone
        TimeZone tz = today.getTimeZone();
        String name = tz.getDisplayName();
        System.out.print(tz + "\n");
        System.out.print(name);
        
        
               
        
        
    }
}


