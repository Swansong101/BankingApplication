package com.codewithnicole;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BankingGUI extends Application {

    private static ArrayList<BankAccount> accounts = new ArrayList<>();
    private BankAccount selectedAccount = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        accounts = AccountStorage.loadAccounts(); // Load accounts from file

        // Login Scene
        Scene loginScene = createLoginScene(primaryStage);

        // Account Management Scene
        Scene accountScene = createAccountScene(primaryStage);

        // Set initial scene (login screen)
        primaryStage.setTitle("Banking Application");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private Scene createLoginScene(Stage stage) {
        // Login form
        VBox loginLayout = new VBox(10);
        loginLayout.setPadding(new javafx.geometry.Insets(20));

        TextField accountNumberField = new TextField();
        accountNumberField.setPromptText("Enter Account Number");

        PasswordField pinField = new PasswordField();
        pinField.setPromptText("Enter PIN");

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String accountNumber = accountNumberField.getText();
            String enteredPin = pinField.getText();

            BankAccount account = findAccount(accountNumber);
            if (account != null && account.authenticate(enteredPin)) {
                selectedAccount = account;
                stage.setScene(createAccountScene(stage)); // Switch to account management screen
            } else {
                showError("Invalid Account Number or PIN.");
            }
        });

        loginLayout.getChildren().addAll(new Label("Login"), accountNumberField, pinField, loginButton);
        return new Scene(loginLayout, 300, 200);
    }

    private Scene createAccountScene(Stage stage) {
        // Account management screen
        VBox accountLayout = new VBox(10);
        accountLayout.setPadding(new javafx.geometry.Insets(20));

        Label accountInfoLabel = new Label("Account: " + selectedAccount.getAccountHolderName() + " - Balance: $" + selectedAccount.getBalance());

        Button depositButton = new Button("Deposit");
        depositButton.setOnAction(e -> depositMoney(stage));

        Button withdrawButton = new Button("Withdraw");
        withdrawButton.setOnAction(e -> withdrawMoney(stage));

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> stage.setScene(createLoginScene(stage)));

        accountLayout.getChildren().addAll(accountInfoLabel, depositButton, withdrawButton, logoutButton);
        return new Scene(accountLayout, 300, 200);
    }

    private void depositMoney(Stage stage) {
        TextInputDialog depositDialog = new TextInputDialog();
        depositDialog.setTitle("Deposit Money");
        depositDialog.setHeaderText("Enter the deposit amount:");

        depositDialog.showAndWait().ifPresent(amount -> {
            try {
                double depositAmount = Double.parseDouble(amount);
                selectedAccount.deposit(depositAmount);
                stage.setScene(createAccountScene(stage)); // Refresh the account scene
            } catch (NumberFormatException e) {
                showError("Invalid deposit amount.");
            }
        });
    }

    private void withdrawMoney(Stage stage) {
        TextInputDialog withdrawDialog = new TextInputDialog();
        withdrawDialog.setTitle("Withdraw Money");
        withdrawDialog.setHeaderText("Enter the withdrawal amount:");

        withdrawDialog.showAndWait().ifPresent(amount -> {
            try {
                double withdrawAmount = Double.parseDouble(amount);
                selectedAccount.withdraw(withdrawAmount);
                stage.setScene(createAccountScene(stage)); // Refresh the account scene
            } catch (NumberFormatException e) {
                showError("Invalid withdrawal amount.");
            }
        });
    }

    private BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
