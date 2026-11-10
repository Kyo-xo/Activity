import java.util.InputMismatchException;
import java.util.Scanner;

// --- Step 1: Custom Exception Class ---
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// --- Step 2: Base Class Architecture (Encapsulation) ---
class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    // Constructor to initialize attributes
    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Public Getter methods for encapsulation
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    // --- Step 3: Core Business Logic & Exception Throwing ---
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid Amount! Deposit must be greater than 0.");
        }
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid Amount! Withdrawal must be greater than 0.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient Funds! Required: $" + String.format("%.2f", amount) + ", Available: $" + String.format("%.2f", balance));
        }
        balance -= amount;
    }
}

// --- Step 4: Interactive Main Application & Exception Handling ---
public class BankAccountExceptionGatekeeper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount("122008", "Kervin Somerano", 500000.00);

        System.out.println("=== BANK ACCOUNT EXCEPTION GATEKEEPER ===");
        System.out.printf("Account Created: %s (Holder: %s, Balance: $%.2f)\n", 
            account.getAccountNumber(), account.getAccountHolder(), account.getBalance());

        // Handling Withdrawal Transaction
        try {
            System.out.print("Enter withdrawal amount: ");
            double withdrawAmount = scanner.nextDouble();
            account.withdraw(withdrawAmount);
            System.out.printf("Current Balance: $%.2f\n", account.getBalance());
        } catch (InsufficientFundsException e) {
            System.out.println("[EXCEPTION CAUGHT]: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("[EXCEPTION CAUGHT]: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("[EXCEPTION CAUGHT]: Invalid input format entered.");
            scanner.next(); // clear buffer
        }

        // Handling Deposit Transaction with Finally Block
        try {
            System.out.print("Enter deposit amount: ");
            double depositAmount = scanner.nextDouble();
            account.deposit(depositAmount);
        } catch (IllegalArgumentException e) {
            System.out.println("[EXCEPTION CAUGHT]: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("[EXCEPTION CAUGHT]: Invalid input format entered.");
            scanner.next();
        } finally {
            System.out.println("[SESSION COMPLETE]: Transaction log updated.");
            System.out.printf("Current Balance: $%.2f\n", account.getBalance());
        }

        scanner.close();
    }
}