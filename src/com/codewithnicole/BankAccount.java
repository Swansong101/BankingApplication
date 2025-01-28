package com.codewithnicole;

import java.io.Serializable;

public class BankAccount implements Serializable {
    private final String accountHolderName;
    private final String accountNumber;
    private final String pin; // Added pin for authentication
    private double balance;

    public BankAccount(String accountHolderName, String accountNumber, String pin) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.pin = pin; // Assign PIN
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

    public String getPin() {
        return pin;
    }

    public boolean authenticate(String enteredPin) {
        return this.pin.equals(enteredPin); // Check if entered pin matches stored pin
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
