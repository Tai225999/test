import javax.swing.JOptionPane;

public class Account {
    private String ownerName;
    private int accountNumber;
    private int currentBalance;

    public Account(String ownerName, int accountNumber, int initialBalance) {
        this.ownerName = ownerName;
        this.accountNumber = accountNumber;
        this.currentBalance = initialBalance;
    }

    public String display() {
        return "Owner Name: " + ownerName + "\nAccount Number: " + accountNumber + "\nCurrent Balance: " + currentBalance + " VND";
    }

    public int decreaseBalance(int amount) {
        if (amount <= 0) {
            JOptionPane.showMessageDialog(null, "Can't decrease by a negative amount", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        if (currentBalance - amount < 50000) {
            JOptionPane.showMessageDialog(null, "Balance cannot drop below 50,000 VND", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        currentBalance -= amount;
        return 1;
    }

    public void increaseBalance(int amount) {
        if (amount <= 0) {
            JOptionPane.showMessageDialog(null, "Can't increase by a negative amount", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        currentBalance += amount;
        JOptionPane.showMessageDialog(null, "Successfully increase the balance", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public int deposit(int amount) {
        if (amount <= 0) {
            JOptionPane.showMessageDialog(null, "Please enter a positive amount", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        currentBalance += amount;
        JOptionPane.showMessageDialog(null, "Successfully deposited " + amount + " VND", "Success", JOptionPane.INFORMATION_MESSAGE);
        return 1;
    }

    public int getCurrentBalance(){
        return currentBalance;
    }

    public int getAccountNumber(){
        return accountNumber;
    }
}
