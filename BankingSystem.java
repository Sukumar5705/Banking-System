import java.util.Scanner;

class Node {
    int accountNumber;
    String name;
    double balance; // Using double for more precision
    Node next;

    Node(int accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
        this.next = null;
    }
}

class BankingSystem {
    private Node head = null;
    private int numAccounts = 0;

    // Create a new account
    public void createAccount() {
        Scanner scanner = new Scanner(System.in);
        Node newNode = new Node(++numAccounts, "", 0.0);

        System.out.print("Enter account holder name: ");
        newNode.name = scanner.nextLine();

        newNode.next = head;
        head = newNode;

        System.out.println("Account created successfully! Your account number is " + newNode.accountNumber);
    }

    // Find an account by account number
    private Node findAccount(int accountNumber) {
        Node current = head;
        while (current != null) {
            if (current.accountNumber == accountNumber) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // Deposit money
    public void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();

        Node account = findAccount(accNum);
        if (account == null) {
            System.out.println("Invalid account number.");
            return;
        }

        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount should be positive.");
            return;
        }

        account.balance += amount;
        System.out.printf("Deposit successful! New balance: %.2f%n", account.balance);
    }

    // Withdraw money
    public void withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();

        Node account = findAccount(accNum);
        if (account == null) {
            System.out.println("Invalid account number.");
            return;
        }

        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount should be positive.");
            return;
        }

        if (account.balance < amount) {
            System.out.println("Insufficient balance.");
            return;
        }

        account.balance -= amount;
        System.out.printf("Withdrawal successful! New balance: %.2f%n", account.balance);
    }

    // Check balance
    public void checkBalance() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();

        Node account = findAccount(accNum);
        if (account == null) {
            System.out.println("Invalid account number.");
            return;
        }

        System.out.println("Account holder: " + account.name);
        System.out.printf("Current balance: %.2f%n", account.balance);
    }

    // View all accounts
    public void viewAllAccounts() {
        if (head == null) {
            System.out.println("No accounts exist.");
            return;
        }

        Node current = head;
        System.out.println("\n=== All Accounts ===");
        while (current != null) {
            System.out.printf("Account Number: %d, Name: %s, Balance: %.2f%n", 
                             current.accountNumber, current.name, current.balance);
            current = current.next;
        }
        System.out.println("===================");
    }

    public static void main(String[] args) {
        BankingSystem bank = new BankingSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\nBanking System Menu");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. View All Accounts");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    bank.createAccount();
                    break;
                case 2:
                    bank.deposit();
                    break;
                case 3:
                    bank.withdraw();
                    break;
                case 4:
                    bank.checkBalance();
                    break;
                case 5:
                    bank.viewAllAccounts();
                    break;
                case 6:
                    System.out.println("Thank you for using the banking system.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}