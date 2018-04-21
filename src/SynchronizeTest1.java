
public class SynchronizeTest1 {
    
    public int count=0;
    
    
    public synchronized void increment(){
        count++;
    }
    
    public static void main(String args[]) throws InterruptedException{
        
        SynchronizeTest1 sn=new SynchronizeTest1();
        
         Thread t1=new Thread(new Runnable(){
             public void run() {
                for(int i=0;i<1000;i++)
              sn.increment();           
             }
             } );
             
              Thread t2=new Thread(new Runnable(){
             public void run() {
                for(int i=0;i<1000;i++)
              sn.increment();   
             }
             });
             
              t1.start();
              t2.start();
             
              
              /*t1.join(); 
              t2.join();*/
              while(t1.isAlive() || t2.isAlive());
              
         System.out.println("Count is:"+sn.count);    
          
         
        
         
    }
    
}
