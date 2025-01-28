package com.codewithnicole;

import java.io.Serializable;

public class BankAccount implements Serializable {
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
