import javax.swing.JOptionPane;
public class NormalAccount extends Account{
    private final int withdrawFee = 5000;
    public NormalAccount(String ownerName, int accountNumber, int initialBalance) {
        super(ownerName, accountNumber, initialBalance);
    }

    public String display(){
        return super.display() + "\nCurrent withdraw fee: " + withdrawFee;
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            JOptionPane.showMessageDialog(null, "Please enter a positive amount", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int check = super.decreaseBalance(amount + withdrawFee);

        if(check == 1)
            JOptionPane.showMessageDialog(null, "Successfully withdraw " + amount + " VND from your account", "Success", JOptionPane.INFORMATION_MESSAGE);

    }
}
