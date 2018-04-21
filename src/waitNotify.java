
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


//..CLASS PROCESS FOR DEPICTING SIR WAY OF PRODUCER AND CONSUMER...
class processes{  //SIRS WAY...
  
    LinkedList<Integer> ls=new LinkedList<>();
    final int limit=10;
    Object lock=new Object();
    Random ran=new Random();
     int value=0;
    public  void producer() throws InterruptedException{
     /*
        SYNCHRO METHOD IS USED WHEN LOCK IS CLASS OBJ ITSELF... LIKE IN MY
        AND SYNCHRO BLOCK IS USED WHEN LOCK IS SOME OBJECT OBJECT.. LIKE IN  SIRS.. HERER
        */
        
        synchronized(lock){   //SYN block and method both works the same when ON SAME OBJECT
       
        while (ls.size()==limit) {
         lock.wait();}
        
        ls.add(value++);
        lock.notify();
     
      }
    }
    public void consumer() throws InterruptedException{
        Scanner sc=new Scanner(System.in);
        synchronized(lock){
           
         while (ls.isEmpty()){ 
         lock.wait();}
        
         System.out.println("lIST Size is:"+ls.size());
         int value=ls.removeFirst();
         System.out.println("; value :"+value);
         
         
            lock.notify();       
      }
    }
}


//..MAIN PROGRAM.


public class waitNotify extends Thread {

     List<Integer> ls=new ArrayList<>();
    final int size=5;
    
    public synchronized  void  consumer() throws InterruptedException{
      /*
        SYNCHRO METHOD IS USED WHEN LOCK IS CLASS OBJ ITSELF... LIKE IN MY way..HERE
        AND SYNCHRO BLOCK IS USED WHEN LOCK IS SOME OBJECT OBJECT.. LIKE IN SIRS WAY
        */ 
    
       
         if (ls.isEmpty()){
            wait(); }
            
            ls.remove(ls.get(ls.size()-1));
            Thread.sleep(1000);
             System.out.println(ls.toString());
             
       
             notify();   
       
       
    }
    public synchronized void producer() throws InterruptedException{
        Random ran=new Random();
        
              if (ls.size()==5) //wait 
                 wait();
               
               ls.add(ran.nextInt(10));
            Thread.sleep(1000);
            System.out.println(ls.toString());

                  notify();

    }
    
    public static void main(String args[]) throws InterruptedException{
        
      //  processes pro=new processes();  //SIRS WAY: 
        
        waitNotify pro=new waitNotify();  //MY WAY:
        
        Thread t1=new Thread(new Runnable(){
            
            @Override
            public void run() {
                try {
                  while(true){
                   pro.producer(); 
               //    Thread.sleep(new Random().nextInt(1000));
                  }
                } catch (InterruptedException ex) {
                    Logger.getLogger(waitNotify.class.getName()).log(Level.SEVERE, null, ex);
                }
                
              
            }
        });
        
        Thread t2=new Thread(new Runnable(){
            
            @Override
            public void run() {
                try {
                   
                    while(true){
                     pro.consumer();
                 Thread.sleep(new Random().nextInt(1000));
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(waitNotify.class.getName()).log(Level.SEVERE, null, ex);
                }
                
               
            }
        });
        
        t1.start();
        t2.start();
        
        t1.join(); t2.join();
        
     //   System.out.println("remains list:"+pro.ls.toString());
        
    }
}
