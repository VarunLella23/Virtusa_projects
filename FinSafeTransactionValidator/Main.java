public class Main {

    public static void main(String[] args) {

        AuditLogger.log("FinSafe Application Started");

        Account account = new Account("Varun",10000);

        TransactionProcessor processor = new TransactionProcessor(account);

        processor.start();

    }

}