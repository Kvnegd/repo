public class CheckingAccount extends BankAccount{


    public final static double FEE = 0.15;
    public CheckingAccount (String name, double amount)
    {
        super(name, amount);
        this.setAccountNumber(getAccountNumber() + "-10");
    }

    @Override
    public boolean withdraw(double amount)
    {
         return super.withdraw(amount + FEE);
    }
}