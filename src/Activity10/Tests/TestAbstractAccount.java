package Activity10.Tests;
import Activity10.Domain.*;
import Exceptions.*;
public class TestAbstractAccount {
    public static  void fundTransfer(AbstractAccount sender,AbstractAccount receiver,String pin,double amount){
        try{
            sender.withdraw(amount,pin);
            receiver.deposit(amount);
            System.out.println("transfer of Rs "+amount+" from "+sender.getAccountType()+" to "+receiver.getAccountType()+"is successfull");
            System.out.println(sender.getAccountType()+" balance:"+sender.getBalance()+"| "+receiver.getAccountType()+" balance:"+receiver.getBalance());

        }catch (AccountException e){
            System.out.println("Failed transfer: "+e.getMessage());
        }
    }
    public static void main(String[] args) {
        System.out.println("=== Activity 10: Banking Operations Suite ===");

        // NOTE: If you completed Activity 9 successfully, paste your working domain classes into src/com/gdb/domain (replacing the provided versions).

        // TODO: Step 1 - Create an array/portfolio of AbstractAccount objects (SavingsAccount, CurrentAccount, SalaryAccount)
        AbstractAccount[] account=new AbstractAccount[4];
        account[0]=new SavingsAccount("1001","Nikhil",25,10000,"ACTIVE","1234");
        account[1]=new CurrentAccount("1002","Mourya",23,5000,"ACTIVE","2525",2000);
        account[2]=new SalaryAccount("1003","Dhanush",23,5000,"ACTIVE","4565","Rajesh");
        account[3]=new FixedDepositAccount("1004","Jaswanth",21,4000,"ACTIVE","8520",7,12.0);

        // TODO: Step 2 - Implement and test secure fund transfer from Savings to Current account with PIN authentication
        fundTransfer(account[0],account[1],"1234",3000);


        // TODO: Step 3 - Test failed transfer with wrong PIN and verify no balance was credited/debited
        System.out.println("Before Transaction");
        System.out.println("Savings Balance:"+account[0].getBalance()+" | Current Balance:"+account[1].getBalance());
        fundTransfer(account[0],account[2],"123",3000);
        System.out.println("After Transaction");
        System.out.println("Savings Balance:"+account[0].getBalance()+" | Current Balance:"+account[1].getBalance());
        // TODO: Step 4 - Process monthly cycle applying interest to every SavingsAccount and checking each SalaryAccount's inactive months
        for (AbstractAccount acc:account){
            if (acc instanceof SavingsAccount){
                SavingsAccount s=(SavingsAccount)acc;
                System.out.println("Balance before applying Interest for Account#"+acc.getAccountNumber()+" Rs "+acc.getBalance());
                s.applyInterest();
                System.out.println("Balance after applying Interest for Account#"+acc.getAccountNumber()+" Rs "+acc.getBalance());
            }
            if (acc instanceof SalaryAccount){
                SalaryAccount s=(SalaryAccount)acc;
                s.incrementInactiveMonths();
                System.out.println("Inactive months:"+s.getInactiveMonths());
            }
        }
        System.out.println("=== Complete the test suite and verify all banking operations ===");
    }
}
