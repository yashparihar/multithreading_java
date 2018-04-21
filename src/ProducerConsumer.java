
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerConsumer {

   private static BlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(10);
    
   public static void producer() throws InterruptedException{
       Random ran=new Random();
       
      while(true){
          Thread.sleep(99);
       queue.put(ran.nextInt(30));  // IF FULL THEN WAIT HERE TILL SOME SPOT IS AVAILALBLE
                                    //It put value Randomly Between 1 to 30
      }
   } 
   
   public static void consumer() throws InterruptedException {
       Random ran=new Random();
       while(true){
           Thread.sleep(100);
           
        if (ran.nextInt(5)==0){ //Here (1/5) chances of running THESE BLOCK
           Integer it= queue.take(); // If nothing then it PATIENTLY WAIT HERE FOR ITEM IN QUEUE
            
            System.out.println("value taken :"+it+" ; its siize:"+queue.size());
           }
      }
       
   }
   
   public static void main(String args[]) throws InterruptedException{
       
       Thread t1=new Thread(new Runnable(){
          public void run(){
              try {
                 
                  
                  producer();
              } catch (InterruptedException ex) {
                  Logger.getLogger(ProducerConsumer.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
       });
     //  t1.wait();
       Thread t2=new Thread(new Runnable(){
          public void run(){
              try {
                  consumer();
              } catch (InterruptedException ex) {
                  Logger.getLogger(ProducerConsumer.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
       });
       
       t1.start();
       t2.start();
       
       t1.join(); t2.join();
       
       System.out.println("Done");
   }
   
}
