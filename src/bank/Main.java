package bank;

public class Main {
    public static void main(String[] args) {

        Customer customer = new Customer("Ali");

        Account account = new SavingsAccount(500);

        Transaction t1 = new Transaction(TransactionType.DEPOSIT, 200);
        account.deposit(200);

        Transaction t2 = new Transaction(TransactionType.WITHDRAW, 100);
        account.withdraw(100);

        SavingsAccount sa = (SavingsAccount) account;
        sa.applyInterest();

        System.out.println(customer.getName());
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(account.getBalance());
    }
}

