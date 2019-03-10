
public class SavingsAccount extends BankAccount {

	private static final double INTERES_RATE = 0.015;
	
	public SavingsAccount(BankClient owner, double balance, int accountId) 
	{
		super(owner, balance, accountId);
	}

	public void collectInterest() 
	{
		balance = balance * (1 + INTERES_RATE);
	}
	
	public String toString() 
	{
		return "Saving Account id: " + accountId + "\n"
				+ "Owner: " + owner + "\n"
				+ "Balance: $" + String.format("%.2f", balance);
	}
}
