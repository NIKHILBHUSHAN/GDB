package Activity12;
import Activity11.Domain.*;
import Activity11.Domain.AccountFactory;
import Activity11.Domain.IAccount;
import Exceptions.*;
public class TestInterfaceFactory {
    public static void main(String[] args) {
        System.out.println("=== Activity 12: Factory-Driven System Suite ===");

        // NOTE: If you completed Activity 11 successfully, paste your working IAccount.java and AccountFactory.java into src/com/gdb/domain (replacing the provided versions).

        // TODO: Step 1 - Instantiate Savings, Current, and FixedDeposit accounts exclusively through AccountFactory.createAccount()
        IAccount acc1= AccountFactory.createAccount("savings","1001","Nikhil",25,5000,"ACTIVE", "1234");
        System.out.println("Factory created: " + acc1.getAccountType() + " account for " + acc1.getName());
        IAccount acc2=AccountFactory.createAccount("current","1002","Mourya",24,7000,"ACTIVE", "5678");
        System.out.println("Factory created: " + acc2.getAccountType() + " account for " + acc2.getName());
        IAccount acc3=AccountFactory.createAccount("Fd","1003","Dhanush",24,10000.0,"ACTIVE", "2580");
        System.out.println("Factory created: " + acc3.getAccountType() + " account for " + acc3.getName());
        IAccount acc4=AccountFactory.createAccount("Salary","1004","Jaswanth",21,25000,"ACTIVE", "3749");
        System.out.println("Factory created: " + acc4.getAccountType() + " account for " + acc4.getName());
        // TODO: Step 2 - Perform deposits and withdrawals through the IAccount interface references
        try{
            System.out.println(acc1.getAccountNumber()+" |Balance before Deposit: "+acc1.getBalance());
            acc1.deposit(5000);
            System.out.println(acc1.getAccountNumber()+" |Balance after Deposit: "+acc1.getBalance());
            acc1.withdraw(5000,"1234");
            System.out.println(acc1.getAccountNumber()+" |Balance after Withdraw: "+acc1.getBalance());

        }catch (AccountException e){
            System.out.println(e.getMessage());
        }


        try{
            System.out.println(acc4.getAccountNumber()+" |Balance before Deposit: "+acc4.getBalance());
            acc4.deposit(5000);
            System.out.println(acc4.getAccountNumber()+" |Balance after Deposit: "+acc4.getBalance());
            acc4.withdraw(5000,"1234");
            System.out.println(acc4.getAccountNumber()+" |Balance after Withdraw: "+acc4.getBalance());

        }catch (AccountException e){
            System.out.println(e.getMessage());
        }
        // TODO: Step 3 - Verify Savings minimum balance rule enforcement through the interface
        try{

            acc1.withdraw(10000,"1234");

        }catch (AccountException e){
            System.out.println(e.getMessage());
        }
        // TODO: Step 4 - Verify Current overdraft limit enforcement through the interface
        try{
            System.out.println("Verify Current overdraft limit enforcement through the interface");
            acc2.withdraw(130000,"5678");


        }catch (AccountException e){
            System.out.println(e.getMessage());
        }
        // TODO: Step 5 - Verify FixedDeposit premature withdrawal rejection through the interface
        try{
            System.out.println(acc3.getAccountNumber()+" |Balance before Deposit: "+acc3.getBalance());
            acc3.deposit(5000);
            System.out.println(acc3.getAccountNumber()+" |Balance after Deposit: "+acc3.getBalance());
            acc3.withdraw(5000,"2580");
            System.out.println(acc3.getAccountNumber()+" |Balance after Withdraw: "+acc3.getBalance());

        }catch (AccountException e){
            System.out.println(e.getMessage());
        }
        // TODO: Step 6 - Verify requesting an invalid account type from AccountFactory throws IllegalArgumentException
        try {
            IAccount acc5 = AccountFactory.createAccount("null", "1005", "Sunny", 29, 50000, "ACTIVE", "5678");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        System.out.println("=== Complete Activity 12 test suite and run ===");
    }
}