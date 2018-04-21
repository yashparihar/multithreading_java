import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.concurrent.locks.ReentrantLock;

class runner{
    
    Lock lock=new ReentrantLock();
    Condition con=lock.newCondition();
    
    int count=0;
    
    void increment(){
       for(int i=0;i<1000;i++){ count++; }
    }
    
    public void firstTh() throws Exception{
        lock.lock();
        
        System.out.println("Waiting...");
        con.await();
        System.out.println("Woken up..");
        
        try{
            increment();
        }
        finally {
             System.out.println("Unlock the lock from 1ST");
            lock.unlock();
        }
        
    } 
    
    public void secondTh() throws Exception{
       Thread.sleep(1000);
        lock.lock();
        System.out.println("Presss the enter key....");
        new Scanner(System.in).nextLine();
        
       con.signal();
        try{
            increment();
        }
        finally {
            System.out.println("Unlock the lock from 2nd");
            lock.unlock();
        }
    } 


    public void finalRun(){
        System.out.println("Count:"+count);
    }
  
}  

public class reentrantLockDemo {
    
    public static void main(String args[]) throws InterruptedException{
        runner run=new runner();
        
        Thread t1=new Thread(new Runnable(){
            public void run() {
                try {
                 // for(int i=0;i<1000;i++){
                    run.firstTh();
                  //  }
                } catch (Exception ex) {
                    Logger.getLogger(reentrantLockDemo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   
        });
        
        
        Thread t2=new Thread(new Runnable(){
                 // @Override
            public void run() {
                try {
                  //  for(int i=0;i<1000;i++){
                    run.secondTh(); 
               // }
                } catch (Exception ex) {
                    Logger.getLogger(reentrantLockDemo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        
        t1.start();  t2.start();
        t1.join(); t2.join();
        
        run.finalRun();
    }
    
}
