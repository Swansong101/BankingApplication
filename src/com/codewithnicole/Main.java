package com.codewithnicole;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Create a single bank account for demonstration
                BankAccount account = new BankAccount("John Doe",
                        "123456789");

                Scanner scanner = new Scanner(System.in);

                System.out.println("Welcome to the Banking Application!");
                System.out.println("Account Holder: " + account.getAccountHolderName());
                System.out.println("Account Number: " + account.getAccountNumber());
                System.out.println();

                int option;
                do {
                    System.out.print("Choose an option:");
                    System.out.println("1. Check Balance");
                    System.out.println("2. Deposit Money");
                    System.out.println("3. Withdraw Money");
                    System.out.println("4. Exit");
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
                            System.out.println("Thank you for using the Banking Application. Goodbye!");
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                    System.out.println();
                }            while (option != 4);

                scanner.close();
            }
        }

        class BankAccount {
            private final String accountHolderName;
            private final String accountNumber;
            private double balance;

            public BankAccount(String accountHolderName, String accountNumber) {
                this.accountHolderName = accountHolderName;
                this.accountNumber = accountNumber;
                this.balance = 0.0;
            }

            public String getAccountHolderName() {
                return accountHolderName;
            }

            public String getAccountNumber() {
                return accountNumber;
            }

            public double getBalance() {
                return balance;
            }

            public void deposit(double amount) {
                if (amount > 0) {
                    balance += amount;
                    System.out.println("Successfully deposited $" + amount);
                } else {
                    System.out.println("Deposit amount must be greater than zero.");
                }
            }

            public void withdraw(double amount) {
                if (amount > 0) {
                    if (amount <= balance) {
                        balance -= amount;
                        System.out.println("Successfully withdrew $" + amount);
                    } else {
                        System.out.println("Insufficient balance. Your current balance is $" + balance);
                    }
                } else {
                    System.out.println("Withdrawal amount must be greater than zero.");
                }
            }
        }


