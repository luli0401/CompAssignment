
public class ChequingAccount extends BankAccount {

	private static final double TRANSACTION_FEE = 4.95;
	
	public ChequingAccount(BankClient owner, double balance, int accountId) 
	{
		super(owner, balance, accountId);
	}

	public void withdraw(double amount) 
	{
		super.withdraw(amount);
		
		if(balance >= TRANSACTION_FEE)
		{
			balance -= TRANSACTION_FEE;
		}
		else
		{
			Exception exception = new InsufficientFundsException("Insufficient funds for the withdrawal.");
			System.out.println(exception);
		}
	}
	
	public String toString() 
	{
		return "Chequing Account id: " + accountId + "\n"
				+ "Owner: " + owner + "\n"
				+ "Balance: $" + String.format("%.2f", balance);
	}
}
