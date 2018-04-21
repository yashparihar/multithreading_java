import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class process1 implements Runnable {
    public CountDownLatch latch;
    
    process1(CountDownLatch latch){
        this.latch=latch;
    }
    
    public void run() {
        System.out.println("Started: "+latch);
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(process1.class.getName()).log(Level.SEVERE, null, ex);
        }
               
          latch.countDown(); //This Thread Finished
       System.out.println("fininshed: "+latch); 
    }
    
}

public class countdownLatch {
    
    public static void main(String args[]) throws InterruptedException{
        
        CountDownLatch latch=new CountDownLatch(3);
        
        ExecutorService exe=Executors.newFixedThreadPool(2);
        
        for(int i=0;i<10;i++){
            exe.submit(new process1(latch));
        }
        
        latch.await(); //Main Thread wait here till all thread finished and latch valueWait here
        
        System.out.println("Latch count becomes 0"); //This Print as soon hai LATCH COUNT BECOMES 0.. OBSERVE IN OUTPUT
        
    }
}
