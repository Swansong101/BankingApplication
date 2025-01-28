package com.codewithnicole;

import java.util.ArrayList;
import java.util.Scanner;

public class BankingApplication {

    // List to store bank accounts
    private static ArrayList<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Load accounts from file
        accounts = AccountStorage.loadAccounts();

        int option;
        do {
            System.out.println("Welcome to the Banking Application!");
            System.out.println("1. Create a New Account");
            System.out.println("2. Select an Existing Account");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case 1: // Create a new account
                    createAccount(scanner);
                    break;

                case 2: // Select an existing account
                    selectAccount(scanner);
                    break;

                case 3: // Exit
                    // Save accounts to file before exiting
                    AccountStorage.saveAccounts(accounts);
                    System.out.println("Thank you for using the Banking Application. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 3);

        scanner.close();
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BankAccount newAccount = new BankAccount(name, accountNumber);
        accounts.add(newAccount);
        System.out.println("Account created successfully!\n");
    }

    private static void selectAccount(Scanner scanner) {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available. Please create an account first.\n");
            return;
        }

        // Display available accounts
        System.out.println("Available Accounts:");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ". " + accounts.get(i).getAccountHolderName() + " (" + accounts.get(i).getAccountNumber() + ")");
        }

        // Allow the user to select an account
        System.out.print("Select an account (enter the number): ");
        int accountChoice = scanner.nextInt();

        if (accountChoice > 0 && accountChoice <= accounts.size()) {
            BankAccount selectedAccount = accounts.get(accountChoice - 1);
            manageAccount(scanner, selectedAccount);
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void manageAccount(Scanner scanner, BankAccount account) {
        int option;
        do {
            System.out.println("\nManaging Account: " + account.getAccountHolderName() + " (" + account.getAccountNumber() + ")");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Go Back");
            System.out.print("Your choice: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Your current balance is: $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawalAmount = scanner.nextDouble();
                    account.withdraw(withdrawalAmount);
                    break;
                case 4:
                    System.out.println("Returning to the main menu.\n");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 4);
    }
}
