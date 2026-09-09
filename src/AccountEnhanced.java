public class AccountEnhanced {
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

    public AccountEnhanced(int accountNumber, String name, int age, double balance, String accountType) throws IllegalArgumentException{
        this.accountNumber = accountNumber;
        this.name = name;
        this.age = Math.max(age, MIN_AGE);
        if(!accountType.equals("Savings") &&  !accountType.equals("Current")){
            this.accountType="Savings";
        }else {
            this.accountType = accountType;
        }
        this.balance=Math.max(balance,getMinBalance());
        this.status="Active";
        this.pin=null;
    }
    private double getMinBalance(){
        if(this.accountType.equals("Savings")){
            return MIN_AMOUNT_SAVINGS;
        }
        return MIN_AMOUNT_CURRENT;
    }
    public boolean deposit(double amount) {
        if(amount>0 &&  isActive()){
            this.balance += amount;
            return true;
        }else {
            return false;
        }
    }

    public boolean withdraw(double amount,Integer pin) {
        /*if (amount>0 && this.balance >= amount && isActive() && this.hasPin()) {
            if(this.verifyPin(pin))
                this.balance -= amount;
            else return false;
            if (this.accountType.equals("Savings") && this.balance < MIN_AMOUNT_SAVINGS) {
                this.balance+=amount;
                return false;
            } else if (this.accountType.equals("Current") && this.balance<MIN_AMOUNT_CURRENT) {
                this.balance+=amount;
                return false;
            }
            return true;
        }else {
            return false;
        }*/

        if(amount<=0){
            return false;
        }
        if(!isActive()){
            return false;
        }
        if(!hasPin()){
            return false;
        }
        if (!verifyPin(pin)) {
            return false;
        }
        if(balance<amount){
            return false;
        }
        if(balance-amount<getMinBalance()){
            return false;
        }
        balance-=amount;
        return true;

    }

     public boolean closeAccount(){
        this.status="Inactive";
        return true;
     }

     public boolean reopenAccount(){
        this.status="Active";
        return true;
     }

     private boolean isActive(){
        return this.status.equals("Active");
     }

     public boolean setPin(int pin){
        if(pin>MIN_PIN && pin<MAX_PIN){
            this.pin=pin;
            return true;
        }else  {
            return false;
        }
     }

     private boolean verifyPin(int pin){
         return this.pin == pin;
     }

     private boolean hasPin(){
        return this.pin!=null;
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
        return "Name:"+this.name+"("+this.age+"yrs)|"+this.accountType+"|Rs."+this.balance+"|"+this.status+"| Pin:"+this.hasPin();
    }



}
