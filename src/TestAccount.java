import java.util.*;

public class TestAccount {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("GLOBAL DIGITAL BANK - ACCOUNT TEST");

        Account a1=new Account(1001,"Nikhil",25,1000,"Savings");
        System.out.println("Account created");
        System.out.println(a1.display());
        System.out.println("Enter amount to deposit:");
        int d_amount=input.nextInt();
        if(a1.deposit(d_amount)){
            System.out.println("Depositing:"+d_amount+" Successful");
            System.out.println("New Balance:"+a1.getBalance());
        }else{
            System.out.println("Depositing:"+d_amount+" Failed(Invalid amount)");
        }
        System.out.println("Enter amount to withdraw:");
        int w_amount=input.nextInt();
        if(a1.withdraw(w_amount)){
            System.out.println("Withdrawal:"+w_amount+" Successful");
            System.out.println("New Balance:"+a1.getBalance());
        }else {
            System.out.println("Withdrawal:"+w_amount+" Failed(Insufficient Balance)");
        }
        Account a2=new Account(1002,"Mourya",26,1000,"Savings");
        System.out.println("Another Account created");
        System.out.println(a2.display());
        System.out.println("All Accounts");
        System.out.println(a1.display());
        System.out.println(a2.display());
    }
}
