
import java.util.Random;
import java.util.concurrent.locks.*;


class runner2{
    
    private Lock lock1=new ReentrantLock();
    private Lock lock2=new ReentrantLock();

    
    void acquireLock(Lock l1,Lock l2){
        boolean t1=false; boolean t2=false;
            
        while(true){
            try{
            t1=l1.tryLock();
            t2=l2.tryLock();
            }
            finally{
           if(t1 && !t2){
               l1.unlock();
              }
           else if (t1 && t2){
              return;
            }
               }          
        }

    }
    
    void firstTh(account acc1,account acc2){
        Random ran=new Random();
       
        acquireLock(lock1,lock2);
       // boolean l1=false;  boolean l2=false;
    
         try{
             account.transfer(acc1, acc2, ran.nextInt(1000));
         } 
         finally{
             lock1.unlock();
             lock2.unlock();
         }
        
    }
    
    void secondTh(account acc2,account acc1){
          Random ran=new Random();
     
          acquireLock(lock2,lock1);
                
         try{
             account.transfer(acc2, acc1, ran.nextInt(1000));
         } 
         finally{
             lock2.unlock();
             lock1.unlock();
         }
    }
    
    void runnerfinal(account acc1,account acc2){
        System.out.println("Yash acc:"+acc1.getBalance()+" Dev acc:"+acc2.getBalance());
        System.out.println("Total balance: "+(acc1.getBalance()+acc2.getBalance()));
    }
    
}

public class deadlock {

    public static void main(String args[]) throws InterruptedException{
    
        account yashacct=new account("y");
        account devacct=new account("n");
        
        runner2 run=new runner2();
      Thread t1=new Thread(new Runnable(){

         // @Override
          public void run() {
              run.firstTh(yashacct, devacct);
          }
          
      });
        Thread t2=new Thread(new Runnable(){

         // @Override
          public void run() {
              run.secondTh(devacct,yashacct);
          }
          
      });
      
      t1.start(); t2.start();
      
      t1.join(); t2.join();
      
      run.runnerfinal(yashacct, devacct);
        
}  
}
