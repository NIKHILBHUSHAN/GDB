package Activity13;


import static Activity13.AccountRulesEngine.*;

public class TestAccountRules {
    public static void main(String[] args) {
        System.out.println("Dynamic AccountRules Test");
        IAccount acc1= AccountFactory.createAccount("Savings","1001","Nikhil",25,5000.0,"Active","3749",4);
        System.out.println("Factory created: " + acc1.getAccountType() + " account for " + acc1.getName());
        System.out.println("Min Balance: " +  getSavingsMinBalance(4));
        System.out.println("Interest Rate:"+getSavingsInterestRate(4));
    }
}
