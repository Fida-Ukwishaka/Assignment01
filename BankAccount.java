public class BankAccount {
 // defining enumeration for Account Type
    public enum AccountType {
    SAVINGS,
    CURRENTS
    }


     // Constants
    private static final double CURRENT_ACCT_MIN_BALANCE = 100.0;
    private static final double CURRENT_ACCT_MAINTENANCE_FEE = 10.0;
    private static final double SAVINGS_ACCT_MIN_BALANCE = 50.0;
    private static final double SAVINGS_ACCT_INTEREST_RATE = 0.03; // 3%



    // Member variables (attributes)
    private String id;
    private AccountType type;
    private double balance;
    private int numWithdrawals;
    private double minBalance;
    private double interestRate;
    private double maintenanceFee;
    private boolean inTheRed;



    // Constructor
    public BankAccount(String id, AccountType type) {
        this.id = id;
        this.type = type;
        this.balance = 0.0;
        this.numWithdrawals = 0;
        this.inTheRed = false;


        // Set account-specific attributes
        if (type == AccountType.CURRENTS) {
            this.minBalance = CURRENT_ACCT_MIN_BALANCE;
            this.maintenanceFee = CURRENT_ACCT_MAINTENANCE_FEE;
            this.interestRate = 0.0; // No interest for current accounts
        } else if (type == AccountType.SAVINGS) {
            this.minBalance = SAVINGS_ACCT_MIN_BALANCE;
            this.interestRate = SAVINGS_ACCT_INTEREST_RATE;
            this.maintenanceFee = 0.0; // No maintenance fee for savings accounts
        }
        // check if the account is in the red
        this.inTheRed = this.balance < this.minBalance;
    }


    // another constructor for the bank account class that takes in an opening accountbalance, in addition to an account type and id:
    public BankAccount(String id, AccountType type, double openingBalance) {
        this(id, type); // Call the other constructor to initialize common attributes
        this.balance = openingBalance; // Set the opening balance
        // check if the account is in the red
        this.inTheRed = this.balance < this.minBalance;
    }


    // defining and implementing methods for BankAccount class
    public double getBalance() {
        return this.balance;
    }
    public AccountType getAccountType() {
        return this.type;
    }
    public String getAccountID() {
        return this.id;
    }
    public double getMinBalance() {
        return this.minBalance;
    }
   // third of them
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Sorry, could not perform withdrawal: Amount must be positive.");
            return false;
        }
        if (this.inTheRed) {
            System.out.println("Sorry, could not perform withdrawal: Account is in the red.");
            return false;
        }
        if (this.balance - amount < this.minBalance) {
            System.out.println("Sorry, could not perform withdrawal: Insufficient balance.");
            return false;
        }
        // Assuming no withdrawal limit for simplicity
        this.balance -= amount;
        this.numWithdrawals++;
        // Update inTheRed status
        this.inTheRed = this.balance < this.minBalance;
        return true;
    }


    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        this.balance += amount;
        // Update inTheRed status
        this.inTheRed = this.balance < this.minBalance;
    }
    public void performMonthlyMaintenance() {
   // one of them
        double earnedInterest = (this.balance * this.interestRate) / 12.0;
        this.balance += earnedInterest;
        this.balance -= this.maintenanceFee;
        this.inTheRed = this.balance < this.minBalance;
        this.numWithdrawals = 0;

        System.out.printf("Earned interest: %.2f%n", earnedInterest);
        System.out.printf("Maintenance fee: %.2f%n", this.maintenanceFee);
        System.out.printf("Updated balance: %.2f%n", this.balance);
        if (this.inTheRed) {
            System.out.println("WARNING: This account is in the red!");
        }
    }
    public boolean transfer(boolean transferTo, BankAccount otherAccount, double amount) {
        // second of them
        if (transferTo) {
            if (this.withdraw(amount)) {
                otherAccount.deposit(amount);
                return true;
            } else {
                return false;
            }
        } else {
            if (otherAccount.withdraw(amount)) {
                this.deposit(amount);
                return true;
            } else {
                return false;
            }
        }

        
    }

}