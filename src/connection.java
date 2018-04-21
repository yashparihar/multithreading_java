
import java.util.concurrent.Semaphore;

// usual class used by Semaphore class

public class connection {

    private static connection instance=new connection();
    private static Semaphore sem=new Semaphore(10);
    
    private int connections=0;
    
    public static connection getConnection(){
        return instance;
    }
    
    public void connect() throws InterruptedException{
        sem.acquire();
        
        try{
            doConnect();
        }
        finally{
              sem.release();
        }
    }
    public void doConnect() throws InterruptedException{
        
        
        synchronized(this){
            connections++;
            System.out.println("Connections: "+connections);
        }
        
        Thread.sleep(2000);
        
        synchronized(this){
            connections--;
        }
        
      
    }
}
