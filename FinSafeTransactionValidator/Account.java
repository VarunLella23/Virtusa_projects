import java.util.ArrayList;

public class Account {

    private String accountHolder;
    private double balance;

    private ArrayList<Double> transactionHistory;

    public Account(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
        transactionHistory = new ArrayList<>();
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {

        if(amount <= 0){
            AuditLogger.log("Failed Transaction - Negative Amount: ₹" + amount);
            throw new IllegalArgumentException("Amount cannot be negative.");
        }

        balance += amount;

        AuditLogger.log("Deposit: ₹" + amount + " | Balance: ₹" + balance);

        addTransaction(amount);

        System.out.println("Deposit Successful.");
        System.out.println("Current Balance : " + balance);
    }

    public void processTransaction(double amount) throws InSufficientFundsException {

        if(amount < 0){
            throw new IllegalArgumentException("Amount cannot be negative.");
        }

        if(amount > balance){
            AuditLogger.log("Failed Withdrawal: ₹" + amount + " | Available: ₹" + balance);
            throw new InSufficientFundsException("Insufficient Balance.");
        }

        balance -= amount;

        addTransaction(-amount);

        System.out.println("Withdrawal Successful.");
        AuditLogger.log("Withdraw: ₹" + amount + " | Balance: ₹" + balance);
        System.out.println("Remaining Balance : " + balance);
    }

    private void addTransaction(double amount){

        if(transactionHistory.size()==5){
            transactionHistory.remove(0);
        }

        transactionHistory.add(amount);
    }

    public void printMiniStatement(){

        if(transactionHistory.isEmpty()){
            System.out.println("No Transactions Available.");
            return;
        }

        System.out.println("\nMini Statement");

        for(double amount : transactionHistory){

            if(amount>0){
                System.out.println("Deposited : " + amount);
            }
            else{
                System.out.println("Withdrawn : " + Math.abs(amount));
            }
        }

        System.out.println("Current Balance : " + balance);
    }

}