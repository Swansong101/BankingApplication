package com.codewithnicole;

import java.io.*;
import java.util.ArrayList;

public class AccountStorage {

    private static final String ACCOUNTS_FILE = "accounts.dat"; // File name for storing account data

    // Method to save accounts to a file
    public static void saveAccounts(ArrayList<BankAccount> accounts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ACCOUNTS_FILE))) {
            oos.writeObject(accounts);
            System.out.println("Accounts saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    // Method to load accounts from a file
    public static ArrayList<BankAccount> loadAccounts() {
        ArrayList<BankAccount> accounts = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ACCOUNTS_FILE))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) {
                accounts = (ArrayList<BankAccount>) obj;
                System.out.println("Accounts loaded successfully.");
            }
        } catch (FileNotFoundException e) {
            // If file doesn't exist, we just ignore the error.
            System.out.println("No previous accounts found, starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
        return accounts;
    }
}
