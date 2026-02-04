package bank;

public class SavingsAccount extends Account {

    public SavingsAccount(double balance) {
        super(balance);
    }

    public void applyInterest() {
        balance = balance * 1.1;
    }
}
