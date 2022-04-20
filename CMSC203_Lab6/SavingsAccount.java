public class SavingsAccount extends BankAccount{
    private final double RATE = 0.025;
    private static int savingsNumber = 0;
    private String accountNumber;
    public SavingsAccount(String name, double balance)
    {
        super(name, balance);
        accountNumber = super.getAccountNumber() + "-"+ savingsNumber;
        //super.setAccountNumber(getAccountNumber()+ savingsNumber);
        savingsNumber ++;
    }
    // copy constructor


    public void postInterest()
    {

        super.deposit(this.getBalance() *RATE/12);


    }
    @Override
    public String getAccountNumber()
    {
        return accountNumber;
    }

    // copy constructor
    public SavingsAccount (SavingsAccount savAcc, double initialBalance)
    {
        super(savAcc, initialBalance);

        this.accountNumber = super.getAccountNumber() +"-" + savingsNumber;
        savingsNumber++;

    }
}