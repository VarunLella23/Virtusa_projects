import java.util.Scanner;

public class TransactionProcessor {

    private Account account;

    public TransactionProcessor(Account account){
        this.account = account;
    }

    public void start(){

        Scanner sc = new Scanner(System.in);

        int choice;

        do{

            System.out.println("\n====== FinSafe Transaction Validator ======");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. View Mini Statement");
            System.out.println("4. View Balance");
            System.out.println("5. Exit");

            System.out.print("Enter Choice : ");

            choice = sc.nextInt();

            switch(choice){

                case 1:

                    try{

                        System.out.print("Enter Deposit Amount : ");

                        double deposit = sc.nextDouble();

                        account.deposit(deposit);

                    }
                    catch(IllegalArgumentException e){

                        System.out.println(e.getMessage());

                    }

                    break;

                case 2:

                    try{

                        System.out.print("Enter Withdrawal Amount : ");

                        double withdraw = sc.nextDouble();

                        account.processTransaction(withdraw);

                    }
                    catch(IllegalArgumentException e){

                        System.out.println(e.getMessage());

                    }
                    catch(InSufficientFundsException e){

                        System.out.println(e.getMessage());

                    }

                    break;

                case 3:

                    account.printMiniStatement();

                    break;

                case 4:

                    System.out.println("Current Balance : " + account.getBalance());

                    break;

                case 5:

                    System.out.println("Thank You for using FinSafe.");

                    AuditLogger.log("Application Closed");

                    break;

                default:

                    System.out.println("Invalid Choice.");

            }

        }while(choice!=5);

        sc.close();

    }

}