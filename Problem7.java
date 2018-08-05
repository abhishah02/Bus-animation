package problem7;

import com.oracle.jrockit.jfr.Producer;
import java.text.SimpleDateFormat;
import java.util.*;
import java.time.LocalTime;
/**
 *
 * @author Admin
 */

class product{
    
    String productName;
	
	
	
    double price1;
    int minute;

    public product(String productName,double price,int minute) {
        
        this.productName = productName;



        this.price = price;
        this.minute = minute;
        
    }
}


public class Problem7 implements Runnable{

    ArrayList<Integer> order = new ArrayList<>();
    product p[] = new product[4];
    Scanner sc = new Scanner(System.in);
            
    public Problem7() {
    
        p[0] = new product("Veg Berger", 45, 1);
        p[1] = new product("Italian Pasta", 100, 2);
        p[2] = new product("Pizza Margareta", 120, 5);
        p[3] = new product("Pizza Thine Crust", 140, 5);
        
        int choice;
        char other = 'y';
        do{
            for (int i=0;i<p.length;i++) {
                System.out.println((i+1)+". "+p[i].productName+", "+p[i].price+" Rs. ("+p[i].minute+" minutes)");
            }
            System.out.print("Enter your choice: ");
            choice = sc.nextInt(); 
            order.add(choice-1);
            System.out.print("Any other order (Y/N): ");
            other = sc.next().toLowerCase().charAt(0);
            
        }while(other == 'y');
        
        Thread t = new Thread(this,"mythread");
        t.start();
    
    }

   
    public void run(){
        double total = 0;
        for (int o : order) {
            total += p[o].price;
        }
        System.out.println("Please pay "+total+" Rs. and wait for : "+LocalTime.now().toString());
        System.out.println("<<current system time >>");
        int counttime = 0;
        for (int o : order) {
            System.out.println(p[o].productName+" is ready at "+getTime(p[o].minute + counttime));
            counttime += p[o].minute;
        } 
    }
    
    
    public String getTime(int addminute){
    
        String myTime = LocalTime.now().toString();  
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date d = null;
        try{
            d = df.parse(myTime);
        }catch (Exception ex) {
            System.out.println(ex);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, addminute);
        String newTime = df.format(cal.getTime());
        return newTime;
        
    }
    
    
    public static void main(String[] args) {

        Problem7 p = new Problem7();        

    }

}
