public class Account {
    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;
    Account(int accountNumber, String name, int age, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.accountType = accountType;
        this.status = "Active";
    }
    public String display(){
        return "Name:"+this.name+"("+this.age+"yrs)"+"|"+this.accountType+"|"+"Rs."+this.balance+"|"+this.status;
    }
    public boolean deposit(double amount) {
        if(amount>0){
            this.balance += amount;
            return true;
        }else {
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if(amount>0 && this.balance >= amount){
            this.balance-=amount;
            return true;
        }else  {
            return false;
        }
    }
    public int getAccountNumber(){
        return this.accountNumber;
    }
    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
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
    public void setName(String name){
        this.name = name;
    }
    public void setAge(int age){
        this.age = age;
    }

}
