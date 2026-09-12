import Exceptions.AccountException;
import Exceptions.InactiveAccountException;
import Exceptions.InvalidPinException;

public class TestAccountSubclasses {
    public static void main(String[] args) {
        System.out.println("TestAccountSubclasses");

        System.out.println("Test 1 creating Accounts");
        SavingsAccount a1=new SavingsAccount(1001,"Nikhil",25,1000);
        CurrentAccount a2=new CurrentAccount(1002,"Mourya",22,2000);
        System.out.println(a1.details());
        System.out.println(a2.details());

        System.out.println("Test 2: Account type and Minimum Balance");
        System.out.println(a1.getAccountType()+"|"+a1.getMinBalance());
        System.out.println(a2.getAccountType()+"|"+a2.getMinBalance());

        System.out.println("Test 3: Savins Account Intereset Calculation");
        System.out.println("Interest Rate:% "+a1.getInterestRate()+" per annum");
        System.out.println(("Interest after 1 year:"+a1.calculateInterestRate(1)));
        System.out.println("Interest after 2 years:"+a1.calculateInterestRate(2));
        System.out.println("Interest after 3 years:"+a1.calculateInterestRate(3));

        System.out.println("Test 4 Current Account -OverDraft Feature");
        System.out.println(a2.details());
        System.out.println("Overdraft Limit:"+a2.getOverDraftLimit());
        System.out.println("Available Overdraft:"+ (a2.getOverDraftLimit()-a2.getOverdraftUsed()));
        System.out.println("Overdraft used:"+a2.getOverdraftUsed());
        System.out.println("Is using overdraft:"+a2.isUsingOverdraft());
        try{
            a2.setPin(1234);
        }catch (InvalidPinException e){
            System.out.println(e.getMessage());
        }

        System.out.println("withdrawing 1500 (goes balance below minimum balance)");
        System.out.println("Balance before:"+a2.getBalance());
        System.out.println("withdraw 1500:");
        try{
            a2.withdraw(1500,1234);
            System.out.println("withdrawing 1500 - successful");
        }catch (AccountException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Balance after:"+a2.getBalance());
        System.out.println("overdraft used:"+a2.getOverdraftUsed());
        System.out.println("available overdraft:"+a2.getAvailableOverdraft());
        System.out.println("Is using overdraft:"+a2.isUsingOverdraft());

        System.out.println("withdrawing 6000 (would exceed overdraft)");
        System.out.println("Available funds:"+a2.getBalance()+"(Balance)"+a2.getAvailableOverdraft()+"(overdraft)");
        try{
            a2.withdraw(6000,1234);
        }catch (AccountException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Repaying overdraft of 500");
        System.out.println("Balance before repayment:"+a2.getBalance());
        System.out.println("Overdraft used before:"+a2.getOverdraftUsed());
        try{
            a2.repayOverdraft(500);
            System.out.println("repaying overdraft of 500-Success");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Balance after repayment:"+a2.getBalance());
        System.out.println("Overdraft used after:"+a2.getOverdraftUsed());
        System.out.println("is suing overdraft:"+a2.isUsingOverdraft());

        System.out.println("Test 5 : Polymorphism");
        AccountSubclasses a3=new SavingsAccount(1003,"Dhanush",26,500);
        AccountSubclasses a4=new CurrentAccount(1004,"Jaswanth",21,1000);
        System.out.println(a1.details());
        System.out.println(a2.details());
        System.out.println(a3.details());
        System.out.println(a4.details());

        System.out.println("Test 6 : Validation - Invalid Creation attempts");
        try{
            SavingsAccount a5=new SavingsAccount(1005,"Phani",26,300);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try{
            CurrentAccount a6=new CurrentAccount(1006,"sunny",21,900);
        }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
        }

        System.out.println("test 7 Savings account pin amd operations");
        SavingsAccount a5=new SavingsAccount(1005,"Phani",26,2000);
        try{
            a5.setPin(1234);
            System.out.println("setting pin:1234-success");
        }catch (InvalidPinException e){
            System.out.println(e.getMessage());
        }
        try{
            a5.deposit(500);
            System.out.println("deposit 500-Success");
        }catch (AccountException e){
                    System.out.println(e.getMessage());
        }
        System.out.println("Balance after deposit:"+a5.getBalance());
        try{
            a5.withdraw(300,1234);
            System.out.println("withdraw 300-Success");
        }catch (AccountException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Balance after withdraw:"+a5.getBalance());
        System.out.println("withdraw 2000 violates minimum balance");
        try{
            a5.withdraw(2000,1234);
            System.out.println("withdraw 2000-Success");
        }catch (AccountException e){
            System.out.println(e.getMessage());
        }

        System.out.println("test 8 current account active status operations");
        CurrentAccount a6=new CurrentAccount(1006,"Sunny",26,3000);
        System.out.println(a6.details());
        try{
            a6.closeAccount();
            System.out.println("closing account-successful");
        }catch (AccountException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Attempting to deposit on closed account");
        try{
            a6.deposit(100);
            System.out.println("deposit 100-Success");
        }catch (AccountException e){
                System.out.println(e.getMessage());
        }
        try{
            a6.openAccount();
            System.out.println("opening account-successful");
        } catch (AccountException e) {
                System.out.println(e.getMessage());
        }
        System.out.println("depositing after reopen");
        try{
            a6.deposit(100);
            System.out.println("deposit 100-Success");
        }catch (AccountException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Balance after deposit:"+a6.getBalance());

        System.out.println("Test 9 all account summary");
        System.out.println(a1.details());
        System.out.println(a2.details());
        System.out.println(a3.details());
        System.out.println(a4.details());
        System.out.println(a5.details());
        System.out.println(a6.details());

    }
}
