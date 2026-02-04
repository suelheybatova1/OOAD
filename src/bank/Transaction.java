package bank;

public class Transaction {
    private TransactionType type;
    private double amount;

    public Transaction(TransactionType type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String toString() {
        return type + " " + amount;
    }
}
