
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

//Ussage of COONECTION class....

public class semaphore {
    public static void main(String ssp[]) throws InterruptedException{
      
        ExecutorService exe=Executors.newCachedThreadPool();
        
        for(int i=1;i<=200;i++){
            exe.submit(new Runnable(){

               public void run() {
                   try {
                       connection.getConnection().connect();
                   } catch (InterruptedException ex) {
                       Logger.getLogger(semaphore.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            
            });
            
        }
        
        exe.shutdown();
        exe.awaitTermination(1, TimeUnit.DAYS);
        
        
    }

    semaphore(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
