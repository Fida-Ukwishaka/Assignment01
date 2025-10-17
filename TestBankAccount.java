public class TestBankAccount {
    public static void main(String[] args) {

        // Create a savings and current account
        BankAccount savings = new BankAccount("S001",BankAccount.AccountType.SAVINGS, 200.0);
        BankAccount current = new BankAccount("C001",BankAccount.AccountType.CURRENTS);

        // Test get methods
        System.out.println("Savings Account ID: " + savings.getAccountID());
        System.out.println("Account Type: " + savings.getAccountType());
        System.out.println("Min Balance: " + savings.getMinBalance());
        System.out.println("Current Balance: " + savings.getBalance());
        System.out.println();

        // Test deposit
        savings.deposit(100);
        System.out.println("After deposit, balance: " + savings.getBalance());

        // Test withdraw
        savings.withdraw(50);
        savings.withdraw(200); // should fail due to insufficient balance

        // Test monthly maintenance
        savings.performMonthlyMaintenance();

        // Test transfer
        System.out.println("\nAttempting transfer from savings to current...");
        savings.transfer(true, current, 100);

        // Print maintenance summaries again
        savings.performMonthlyMaintenance();
        current.performMonthlyMaintenance();
    }
}