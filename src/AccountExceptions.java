import Exceptions.*;

public class AccountExceptions {
    private static final double MIN_AMOUNT_SAVINGS=500.0;
    private static final double MIN_AMOUNT_CURRENT=1000.0;
    private static final int MIN_AGE=18;
    private static final int MIN_PIN=1000;
    private static final int MAX_PIN=9999;

    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;
    private Integer pin;

    public AccountExceptions(int accountNumber, String name, int age, double initialBalance, String accountType) throws AccountException {
         this.accountNumber = accountNumber;
         this.name = name;
         if (age<MIN_AGE) {
             throw new AccountException("Age must be at least 18\n");
         }
        this.age = age;
        if(!accountType.equals("Savings") && !accountType.equals("Current")){
            throw new AccountException("Account Type must be Savings or Current\n");
        }
        this.accountType=accountType;
        if(initialBalance<getMinBalance()){
            throw new AccountException("Initial Balance must be 500 for Savings Account and 1000 for current Account\n");
        }


         this.balance=initialBalance;


         this.status="Active";
         this.pin=null;

    }
    private double getMinBalance(){
        if(this.accountType.equals("Savings")){
            return MIN_AMOUNT_SAVINGS;
        }
        return MIN_AMOUNT_CURRENT;
    }

    public void deposit(double amount) throws AccountException{
        if(amount<=0){
            throw new InvalidAmountException("Amount must be greater than 0\n");
        }
        if(!isActive()){
            throw new InactiveAccountException("Account is not Active\n");
        }
        this.balance+=amount;
    }

    public void withdraw(double amount,int pin) throws AccountException{
        if (!isActive()) {
            throw new InactiveAccountException("Account is not Active\n");
        }
        if(!hasPin()){
            throw new InvalidPinException("Pin number not found\n");
        }
        if(!verifyPin(pin)){
            throw new InvalidPinException("Pin number not matches\n");
        }
        if(amount<=0){
            throw new InvalidAmountException("Amount must be greater than 0\n");
        }
        if(balance<amount){
            throw new InsufficientBalanceException("Insufficient Balance\n");
        }
        if(balance-amount<getMinBalance()){
            throw new InsufficientBalanceException("Violates Minimum Balance\n");
        }
        this.balance-=amount;

    }

    public void setPin(int pin) throws InvalidPinException{
        if(!(pin>MIN_PIN&&pin<MAX_PIN)){
            throw new InvalidPinException("Invalid Pin :Pin should be 4 digits\n");
        }
        this.pin=pin;
    }
    private boolean hasPin(){
        return this.pin!=null;
    }
    private boolean verifyPin(int pin){
        return this.pin == pin;
    }

    private boolean isActive(){
        return this.status.equals("Active");
    }

    public void closeAccount() throws AccountException{
        if(!isActive()){
            throw new AccountException("Account is already Inactive\n");
        }
        this.status="Inactive";
    }
    public void openAccount() throws AccountException{
        if(isActive()){
            throw new AccountException("Account is already Active\n");
        }
        this.status="Active";
    }

    public void validateAccount() throws AccountException{
        if(!isActive()){
            throw new InactiveAccountException("Account is not Active\n");
        }
    }

    public int getAccountNumber(){
        return this.accountNumber;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getAge(){
        return this.age;
    }
    public void setAge(int age){
        this.age = age;
    }
    public double getBalance(){
        return this.balance;
    }
    public String getAccountType(){
        return this.accountType;
    }
    public String getStatus(){
        return this.status;
    }

    public String details(){
        return "Account#"+this.accountNumber+"|Name:"+this.name+"("+this.age+"yrs)|"+this.accountType+"|Rs."+this.balance+"|"+this.status+"| Pin:"+this.hasPin()+"\n";
    }

}
