
public class account{
  private int balance=10000;
  private String name;
  
  account(String n){
      name=n;
  }
  
  
  public void deposit(int amt){
      balance+=amt;
  }
  public void withdraw(int amt){
      balance-=amt;
  }
  
  public static void transfer(account acc1,account acc2,int amt){
      acc1.balance-=amt;
     acc2.balance+=amt;
      
  }
  public int getBalance(){
      return balance;
  }
    
}