import java.util.Scanner;

public class TestAccountEnhanced {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("GLOBAL DIGITAL BANK - ENHANCED ACCOUNT TEST");
        AccountEnhanced a1=new AccountEnhanced(1001,"Nikhil",25,1000,"Savings");
        System.out.println("Test 1:Valid Account Creation");
        System.out.println(a1.details());
        System.out.println();

        System.out.println("Test 2:Invalid age Auto correction with age 17 to 18");
        AccountEnhanced a2=new AccountEnhanced(1002,"Pushpa",17,1000,"Savings");
        System.out.println(a2.details());
        System.out.println();

        System.out.println("Test 3:Creating Account with Invalid Account Type\n Account type defaluted to Savings");
        AccountEnhanced a3=new AccountEnhanced(1003,"Rocky",21,1000,"Invalid");
        System.out.println(a3.details());
        System.out.println();

        System.out.println("Test 4: Minimum Balance Enforcement on creation\nCreating Savings account with 300 (below minimum)\nBalance auto-corrected to minimum: ₹500.0");
        AccountEnhanced a4=new AccountEnhanced(1004,"Deva",17,300,"Current");
        System.out.println(a4.details());
        System.out.println();

        System.out.println("Test 5: Withdrawl with minimum balance");
        AccountEnhanced a5=new AccountEnhanced(1005,"Varadha",30,1500,"Current");
        System.out.println(a5.details());
        a5.setPin(5454);
        System.out.println("After setting new pin");
        System.out.println(a5.details());

        System.out.println("Withdraw 200:"+a5.withdraw(200,5454));
        System.out.println("New balance:"+a5.getBalance());
        System.out.println("Withdraw 900:"+a5.withdraw(900,5454));
        System.out.println("Current balance:"+a5.getBalance());
        System.out.println();

        System.out.println("Test 6: Account status Management");
        AccountEnhanced a6=new AccountEnhanced(1006,"Bhahubali",25,2000,"Savings");
        System.out.println(a6.details());
        System.out.println("closing Account:"+a6.closeAccount());
        System.out.println("After closing Account:\n"+a6.details());

        System.out.println("Depositing into closed Account:"+a6.deposit(1000));
        System.out.println("Reopening Account:"+a6.reopenAccount());
        System.out.println("After reopening Account:\n"+a6.details());
        System.out.println();


        System.out.println("Test 7:Pin Protection");
        AccountEnhanced a7=new AccountEnhanced(1007,"Saaho",30,2000,"Current");
        System.out.println("withdraw without pin set up:"+a7.withdraw(500,1234));
        System.out.println("Setting up new pin:"+a7.setPin(1345));
        System.out.println("Withdraw with correct pin:"+a7.withdraw(550,1345));
        System.out.println("New Balance:"+a7.getBalance());
        System.out.println("Withdraw with incorrect pin:"+a7.withdraw(200,1245));
        System.out.println();

        System.out.println("Test 8: All Accounts Summary");
        System.out.println(a1.details());
        System.out.println(a2.details());
        System.out.println(a3.details());
        System.out.println(a4.details());
        System.out.println(a5.details());
        System.out.println(a6.details());
        System.out.println(a7.details());
        System.out.println();











    }
}
