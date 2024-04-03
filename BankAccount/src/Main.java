import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<NormalAccount> normalAccountList = new ArrayList<>();
        ArrayList<SavingAccount> savingAccountList = new ArrayList<>();

        while (true) {
            String choice = JOptionPane.showInputDialog(null, "Which operation do you wish to perform:\n" +
                    "1. Open a new Account\n" +
                    "2. Login to an existing Account\n" +
                    "3. Exit");
            switch (choice) {
                case "1":
                    String nextChoice = JOptionPane.showInputDialog(null, "What type of Account do you want to open:\n" +
                            "1. Normal Account\n" +
                            "2. Saving Account");

                    switch (nextChoice) {
                        case "1":
                            boolean validInitialBalance = false;
                            while (!validInitialBalance) {
                                try {
                                    String ownerName = JOptionPane.showInputDialog(null, "Enter the owner name: ");
                                    String accountNumberStr = JOptionPane.showInputDialog(null, "Enter the desired account number: ");
                                    int accountNumber = Integer.parseInt(accountNumberStr);
                                    String initialBalanceStr = JOptionPane.showInputDialog(null, "Enter the initial balance: ");
                                    int initialBalance = Integer.parseInt(initialBalanceStr);

                                    if (initialBalance >= 50000) {
                                        NormalAccount newNormalAccount = new NormalAccount(ownerName, accountNumber, initialBalance);
                                        normalAccountList.add(newNormalAccount);
                                        validInitialBalance = true;
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Initial Balance must be at least 50,000 VND", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                    }
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Initial Balance needs to be a number!", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            break;

                        case "2":
                            try {
                                String ownerName = JOptionPane.showInputDialog(null, "Enter the owner name: ");
                                String accountNumberStr = JOptionPane.showInputDialog(null, "Enter the desired account number: ");
                                int accountNumber = Integer.parseInt(accountNumberStr);
                                String initialBalanceStr = JOptionPane.showInputDialog(null, "Enter the initial balance: ");
                                int initialBalance = Integer.parseInt(initialBalanceStr);

                                // For Saving Account, do similar input for annual interest rate
                                String interestRateStr = JOptionPane.showInputDialog(null, "Enter the annual interest rate: ");
                                double annualInterestRate = Double.parseDouble(interestRateStr);

                                SavingAccount newSavingAccount = new SavingAccount(ownerName, accountNumber, initialBalance, annualInterestRate);
                                savingAccountList.add(newSavingAccount);
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Initial Balance or Interest Rate needs to be a number!", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Please enter a valid value", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case "2":
                    String accountList = "Existing Accounts:\n";
                    int count = 1;
                    for (NormalAccount account : normalAccountList) {
                        accountList += count + ". Normal Account - Account Number: " + account.getAccountNumber() + "\n";
                        count++;
                    }
                    for (SavingAccount account : savingAccountList) {
                        accountList += count + ". Saving Account - Account Number: " + account.getAccountNumber() + "\n";
                        count++;
                    }

                    String chosenAccountIndexStr = JOptionPane.showInputDialog(null, accountList + "Choose the index of the account you want to login to:");
                    try {
                        int chosenAccountIndex = Integer.parseInt(chosenAccountIndexStr);
                        if (chosenAccountIndex >= 1 && chosenAccountIndex <= normalAccountList.size()) {
                            NormalAccount chosenAccount = normalAccountList.get(chosenAccountIndex-1);

                            while(true){
                                String choiceStr = JOptionPane.showInputDialog(null, "Choose your operation:\n" +
                                        "1. View your account details\n" +
                                        "2. Deposit money\n" +
                                        "3. Withdraw money\n" +
                                        "4. Return");
                                try{
                                    int newChoice = Integer.parseInt(choiceStr);
                                    switch (newChoice){
                                        case 1:
                                            JOptionPane.showMessageDialog(null, "Details:\n" + chosenAccount.display());
                                            break;
                                        case 2: {
                                            String amountStr = JOptionPane.showInputDialog(null, "How much do you want to deposit: ");
                                            int amount = Integer.parseInt(amountStr);
                                            chosenAccount.deposit(amount);
                                            break;
                                        }
                                        case 3: {
                                            String amountStr = JOptionPane.showInputDialog(null, "How much do you want to withdraw: ");
                                            int amount = Integer.parseInt(amountStr);
                                            chosenAccount.withdraw(amount);
                                            break;
                                        }
                                        case 4: {
                                            break;
                                        }
                                    }
                                    if (newChoice == 4)
                                        break;
                                }catch (NumberFormatException e){
                                    JOptionPane.showMessageDialog(null, "Invalid Input", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } else if (chosenAccountIndex > normalAccountList.size() && chosenAccountIndex <= (normalAccountList.size() + savingAccountList.size())) {
                            SavingAccount chosenAccount = savingAccountList.get(chosenAccountIndex - normalAccountList.size() - 1);


                            while (true) {
                                String choiceStr = JOptionPane.showInputDialog(null, "Choose your operation:\n" +
                                        "1. View your account details\n" +
                                        "2. Check monthly interest\n" +
                                        "3. Deposit money\n" +
                                        "4. Return");
                                try {
                                    int newChoice = Integer.parseInt(choiceStr);
                                    switch (newChoice) {
                                        case 1:
                                            JOptionPane.showMessageDialog(null, "Details:\n" + chosenAccount.display());
                                            break;
                                        case 2:
                                            JOptionPane.showMessageDialog(null, "Monthly Interest: " + chosenAccount.calculateMonthlyInterest(), "Monthly Interest", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                        case 3: {
                                            String amountStr = JOptionPane.showInputDialog(null, "How much do you want to deposit: ");
                                            int amount = Integer.parseInt(amountStr);
                                            chosenAccount.deposit(amount);
                                            break;
                                        }
                                        case 4:
                                            break;
                                        default:
                                            JOptionPane.showMessageDialog(null, "Invalid choice", "Error", JOptionPane.ERROR_MESSAGE);
                                            break;
                                    }
                                    if (newChoice == 4) {
                                        break;
                                    }
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Invalid Input", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid account index", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid input", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case "3":
                    System.exit(0);

                default:
                    JOptionPane.showMessageDialog(null, "Please enter a valid value", "Error",
                            JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
