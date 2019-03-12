
public class ChequingAccount extends BankAccount {

	private static final double TRANSACTION_FEE = 4.95;
	
	public ChequingAccount(BankClient owner, double balance, int accountId) 
	{
		super(owner, balance, accountId);
	}

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
	
	public String toString() 
	{
		return "Chequing Account id: " + accountId + "\n"
				+ "Owner: " + owner + "\n"
				+ "Balance: $" + String.format("%.2f", balance);
	}
}
