import Exceptions.AccountException;
import Exceptions.InvalidPinException;

public class TestAccountExceptions {
    public static void main(String[] args) {
        System.out.println("Account Test with Exceptions");


        try{
            System.out.println("Test 1 : valid Account creation");
            AccountExceptions a1=new AccountExceptions(1001,"Nihil",19,1000,"Current");
            System.out.println("Success:"+a1.details());




        }catch(AccountException e){
            System.out.print("Exception:"+e.getMessage());
        }
        System.out.println();
        try{
            System.out.println("Test 2: Invalid Age(under 18)");
            AccountExceptions a2=new AccountExceptions(1002,"Mourya",17,1000,"Savings");
            System.out.println("Success:"+a2.details());
        }catch (AccountException e){
            System.out.print("Exception:"+e.getMessage());
        }
        System.out.println();
        try{
            System.out.println("Test 3 :Invalid Account Type");
            AccountExceptions a3=new AccountExceptions(1003,"Phani",25,1000,"Invalid");
            System.out.println("Success:"+a3.details());
        }catch (AccountException e){
            System.out.print("Exception:"+e.getMessage());
        }
        System.out.println();
        try{
            System.out.println("Test 4:Minimum Balance on creation");
            AccountExceptions a4=new AccountExceptions(1004,"Sunny",25,800,"Current");
            System.out.println("Success:"+a4.details());
        }catch(AccountException e){
            System.out.print("Exception:"+e.getMessage());
        }
        System.out.println();
        try{
            System.out.println("Test 5: Valid Deposit and deposit");
            AccountExceptions a5=new AccountExceptions(1005,"Sirisha",30,1000,"Current");
            System.out.println("Success:"+a5.details());
            a5.setPin(1234);
            System.out.println("Setting pin successfully");
            a5.deposit(500);
            System.out.println("Deposit successful");
            System.out.println("Balance After Deposit:"+a5.getBalance());
            a5.withdraw(200,1234);
            System.out.println("Balance After Withdraw:"+a5.getBalance());
            System.out.println(a5.details());
        } catch (AccountException e){
            System.out.print("Exception:"+e.getMessage());
        }
        System.out.println();


    }
}
