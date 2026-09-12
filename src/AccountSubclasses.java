import Exceptions.*;

public abstract class AccountSubclasses {
    protected static final int MIN_AGE=18;
    protected static final int MIN_PIN=1000;
    protected static final int MAX_PIN=9999;
    protected int accountNumber;
    protected String name;
    protected int age;
    protected double balance;
    protected String accountType;
    protected String status;
    protected Integer pin;

    protected abstract double getMinBalance();
    protected abstract String getAccountType();

    protected AccountSubclasses(int accountNumber, String name, int age, double initialBalance) throws IllegalArgumentException {
        if (age<MIN_AGE) {
            throw new IllegalArgumentException("Age must be at least 18. Provided:"+age+"\n");
        }
        if(initialBalance<getMinBalance()){
            throw new IllegalArgumentException("Initial Balance must be 500 for Savings Account and 1000 for current Account.Provided:"+initialBalance+"\n");
        }
        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;

        this.balance=initialBalance;
        this.status="Active";
        this.pin=null;
    }

    protected void deposit(double amount) throws AccountException{
        if(amount<=0){
            throw new InvalidAmountException("Amount must be greater than 0\n");
        }
        if(!isActive()){
            throw new InactiveAccountException("Account is not Active\n");
        }
        this.balance+=amount;
    }

    protected void withdraw(double amount,int pin) throws AccountException{
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
    protected void setPin(int pin) throws InvalidPinException{
        if(!(pin>MIN_PIN&&pin<MAX_PIN)){
            throw new InvalidPinException("Invalid Pin :Pin should be 4 digits\n");
        }
        this.pin=pin;
    }
    protected boolean hasPin(){
        return this.pin!=null;
    }
    protected boolean verifyPin(int pin){
        return this.pin == pin;
    }

    protected boolean isActive(){
        return this.status.equals("Active");
    }

    protected void closeAccount() throws AccountException{
        if(!isActive()){
            throw new AccountException("Account is already Inactive\n");
        }
        this.status="Inactive";
    }
    protected void openAccount() throws AccountException{
        if(isActive()){
            throw new AccountException("Account is already Active\n");
        }
        this.status="Active";
    }

    protected void validateAccount() throws AccountException{
        if(!isActive()){
            throw new InactiveAccountException("Account is not Active\n");
        }
    }

    protected int getAccountNumber(){
        return this.accountNumber;
    }
    protected String getName(){
        return this.name;
    }
    protected void setName(String name){
        this.name = name;
    }
    protected int getAge(){
        return this.age;
    }
    protected void setAge(int age){
        this.age = age;
    }
    protected double getBalance(){
        return this.balance;
    }
    protected String getStatus(){
        return this.status;
    }

    protected String details(){
        return "Account#"+this.accountNumber+"|Name:"+this.name+"("+this.age+"yrs)|"+this.accountType+"|Rs."+this.balance+"|"+this.status+"| Pin:"+this.hasPin()+"\n";
    }
}

class SavingsAccount extends AccountSubclasses{
    private static final double MIN_AMOUNT_SAVINGS=500.0;
    private static final String ACCOUNT_TYPE="Savings";
    private static final double INTEREST_RATE=4.0;
    public SavingsAccount(int accountNumber, String name, int age, double initialBalance) throws IllegalArgumentException {
        super(accountNumber,name,age,initialBalance);
        this.accountType=ACCOUNT_TYPE;
    }



    public double calculateInterestRate(int years) {
        if(years<0){
            throw new IllegalArgumentException("Years must be greater than 0\n");
        }
        return getBalance()*(INTEREST_RATE/100)*years;

    }

    @Override public double  getMinBalance(){
        return MIN_AMOUNT_SAVINGS;
    }
    @Override public String getAccountType(){
        return ACCOUNT_TYPE;
    }

    public static double getInterestRate() {
                return INTEREST_RATE;
    }
}

class CurrentAccount extends AccountSubclasses{
    private static final double MIN_AMOUNT_CURRENT=1000.0;
    private static final String ACCOUNT_TYPE="Current";
    private static final double OVER_DRAFT_LIMIT=5000.0;
    private double overdraftUsed;
    public CurrentAccount(int accountNumber, String name, int age, double initialBalance) throws IllegalArgumentException {
        super(accountNumber,name,age,initialBalance);
        this.accountType=ACCOUNT_TYPE;
        this.overdraftUsed=0.0;
    }
    @Override public double getMinBalance(){
        return MIN_AMOUNT_CURRENT;
    }
    @Override public String getAccountType(){
        return ACCOUNT_TYPE;
    }
    @Override public void withdraw(double amount,int pin) throws AccountException{
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
        double availableBalance = getBalance()+getAvailableOverdraft();

        if (amount > availableBalance) {
            throw new InsufficientBalanceException(
                    "Insufficient funds. Available: " + availableBalance +
                            " (including " + OVER_DRAFT_LIMIT + " overdraft), Requested: " + amount
            );
        }

        double newBalance = getBalance() - amount;
        if (newBalance < getMinBalance()) {
            double overdraftAmount = getMinBalance() - newBalance;
            this.overdraftUsed += overdraftAmount;
        }
        this.balance=newBalance;

    }
    public double getOverDraftLimit(){
        return OVER_DRAFT_LIMIT;
    }
    public double getOverdraftUsed(){
        return overdraftUsed;
    }
    public double getAvailableOverdraft(){
        return  OVER_DRAFT_LIMIT-getOverdraftUsed() ;
    }
    public boolean isUsingOverdraft(){
        return overdraftUsed>0;
    }
    public void repayOverdraft(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Repayment amount must be positive");
        }
        if (amount > overdraftUsed) {
            throw new IllegalArgumentException(
                    "Amount exceeds overdraft used (" + overdraftUsed + ")"
            );
        }
        this.overdraftUsed-=amount;
        this.balance=getBalance()+amount;
    }
}


