import javax.swing.JOptionPane;
public class SavingAccount extends Account{
    private double yearlyInterestRate;

    public SavingAccount(String ownerName, int accountNumber, int initialBalance, double annualInterestRate){
        super(ownerName, accountNumber, initialBalance);
        this.yearlyInterestRate = annualInterestRate;
    }

    @Override
    public String display() {
        return super.display() + "\nYearly interest rate: " + yearlyInterestRate;
    }

    public double calculateMonthlyInterest(){
        return (double)(super.getCurrentBalance()) * (yearlyInterestRate) / 12;
    }
}
