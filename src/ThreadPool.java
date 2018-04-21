
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


class process implements Runnable{
    
    public int id;
    
    process(int id){
        this.id=id;
    }
    
    public void run(){
        System.out.println("Started pocess:"+id);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(process.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Completed pocess:"+id);
    }
    
}


public class ThreadPool { // Thread pool..
    
    public static void main(String args[]) throws InterruptedException{
        
        ExecutorService exe = Executors.newFixedThreadPool(2);  // 2 THREADS WOULD WORK CONCURRENTLY
        
        for(int i=0;i<5;i++){    // WOULD SUBMIT 5 TASK .. WHERE 2 TASK WILL BE DONE SIMULTANEOUSLY BY 2 THREAD
            exe.submit(new process(i));
        }
   
        exe.shutdown(); //AFTER THESE NO NEW PROCESS IS SUBMITTED TO BE DONE BY THREAD(EXECUTOR)

          System.out.println("Submitted process");
        
        exe.awaitTermination(15, TimeUnit.SECONDS);  // IT WAITS FOR EXECUTOR THREAD TO FINISH IN TIME PERIOD
        
        try {        exe.submit(new process(6));  }  //THROWS ERROR BECAUSE EXECUTOR IS CLOSED TO TAKE MORE TASK
catch(Exception er) {System.out.println("ER:"+er);  }
        
        System.out.println("Done all");
    }
    
}
