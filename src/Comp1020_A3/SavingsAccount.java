package Comp1020_A3;
// SavingsAccount class extends BankAccount class
public class SavingsAccount extends BankAccount 
{
	// Constant
	private static final double INTERES_RATE = 0.015;
	
	// Constructor
	public SavingsAccount(BankClient owner, double balance, int accountId) 
	{
		super(owner, balance, accountId);
	}

	// collectInterest method that calculates the interest on the balance 
	// and adds	it to the balance
	public void collectInterest() 
	{
		balance = balance * (1 + INTERES_RATE);
	}
	
	// toString method
	public String toString() 
	{
		return "Saving Account id: " + accountId + "\n"
				+ "Owner: " + owner + "\n"
				+ "Balance: $" + String.format("%.2f", balance);
	}
}
