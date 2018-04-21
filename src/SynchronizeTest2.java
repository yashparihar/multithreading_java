import static java.lang.Thread.sleep;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 to synchronize 2 methods individually which are used nondependent of whether one is uesd or other

my way:
   USED LOCK1 INT VARIABLE TO LOCK(keep away) STAGEONE FROM OTHER THREAD IS USING IT
 and LOCK2 INT VARIABLE TO LOCK STAGETWO(keep away) FROM OTHER THREAD TO USE IT

sir way:
  USED LOCK AS OBJECT OBJECT FOR LOCK1 AND LOCK2 OF OBJECT TYPE UED FOR STAGE1 AND STAGE2 RESP
AND THEN USED SYNCHRONIZED BLOCK IN EACH STAGE

*/

class demo{
   
    
    public Random ran=new Random();
  
    /* my--
    public int lock1=1;
   public int lock2=1;
   */
    
   Object lock1=new Object();
   Object lock2=new Object();
   
    public List<Integer> l1=new ArrayList<>();
    public List<Integer> l2=new ArrayList<>();
    
    /* my
    public boolean check1(){
        if (lock1==0) return false;
       
            lock1=0;
        return true;
    }
    public boolean check2(){
        if (lock2==0) return false;
        
            lock2=0;
        return true;
    }
    */
    
    public void stageone() throws InterruptedException{
      
      //  while(!check1());
        
        synchronized(lock1){
        sleep(1);
        l1.add(ran.nextInt(100));
        }
      //  lock1=1;
        
    }
    
    public void stagetwo() throws InterruptedException{
      //  while(!check2());
       synchronized(lock2){
        sleep(1);
        l2.add(ran.nextInt(100));
       }
     //    lock2=1;
    }
    
   public void main() throws InterruptedException{
          
       Thread t1=new Thread(new Runnable(){
           public void run() { 
               for(int i=0;i<1000;i++){
               try {
                   stageone();
                    stagetwo();
               } catch (InterruptedException ex) {
                   Logger.getLogger(demo.class.getName()).log(Level.SEVERE, null, ex);
               }
               }
           }
       });
       
          Thread t2=new Thread(new Runnable(){
           public void run() {
               for(int i=0;i<1000;i++){
                try {
                   stageone();
                    stagetwo();
               } catch (InterruptedException ex) {
                   Logger.getLogger(demo.class.getName()).log(Level.SEVERE, null, ex);
               }
              }
           }
       });
          
          
      
          long start=System.currentTimeMillis();
      t1.start();
      t2.start();
      
       t1.join();
      t2.join();
      long end=System.currentTimeMillis();
      
      
      System.out.println("Time taken: "+(end-start));
      System.out.println("List 1 size: "+l1.size()+" and list2:"+l2.size());
   }  
}

public class SynchronizeTest2 {
    
    public static void main(String args[]) throws InterruptedException{
      new demo().main();
      
    }
}
