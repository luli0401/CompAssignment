// ChequingAccount class extends BankAccount class
public class ChequingAccount extends BankAccount 
{
	// Constant
	private static final double TRANSACTION_FEE = 4.95;
	
	// Constructor
	public ChequingAccount(BankClient owner, double balance, int accountId) 
	{
		super(owner, balance, accountId);
	}

	// overridden withdraw method, which will apply the transaction fee after 
	// withdrawing the amount given as a parameter
	public void withdraw(double amount) throws InsufficientFundsException 
	{
		if(balance >= amount)
		{
			balance -= amount;
			balance -= TRANSACTION_FEE;		
		}
		else
		{
			throw new InsufficientFundsException("Insufficient funds for the withdrawal.");
		}		
	}
	
	// toString method
	public String toString() 
	{
		return "Chequing Account id: " + accountId + "\n"
				+ "Owner: " + owner + "\n"
				+ "Balance: $" + String.format("%.2f", balance);
	}
}
