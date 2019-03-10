
public abstract class BankAccount {

	protected BankClient owner;
	protected double balance;
	protected int accountId;
	
	public BankAccount(BankClient owner, double balance, int accountId) 
	{
		this.owner = owner;
		this.balance = balance;
		this.accountId = accountId;
	}
	
	public BankClient getOwner() 
	{
		return owner;
	}
	
	public double getBalance() 
	{
		return balance;
	}
	
	public int getAccountId() 
	{
		return accountId;
	}
	
	public void deposit(double amount) 
	{
		balance += amount;
	}
	
	public void withdraw(double amount) 
	{
		if(balance >= amount)
		{
			balance -= amount;
		}
		else
		{
			Exception exception = new InsufficientFundsException("Insufficient funds for the withdrawal.");
			System.out.println(exception);
		}
	}
	
	public void collectInterest() 
	{
		balance = balance * 1;
	}
	
	public String toString() 
	{
		return "";
	}
}
