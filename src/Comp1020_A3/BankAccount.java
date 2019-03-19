package Comp1020_A3;
// BankAccount class
public abstract class BankAccount 
{
	// instance variables
	protected BankClient owner;
	protected double balance;
	protected int accountId;
	
	// Constructor
	public BankAccount(BankClient owner, double balance, int accountId) 
	{
		this.owner = owner;
		this.balance = balance;
		this.accountId = accountId;
	}

	// Accessors
	public int getAccountId() 
	{
		return accountId;
	}
	
	// Deposit method, which receives an amount as a parameter and adds it to the balance
	public void deposit(double amount) 
	{
		balance += amount;
	}
	
	// Withdraw method, which receives an amount as a parameter and subtracts it from the balance, 
	// only if there is enough money in the account for the withdrawal.
	public void withdraw(double amount) throws InsufficientFundsException 
	{
		if(balance >= amount)
		{
			balance -= amount;
		}
		else
		{
			throw new InsufficientFundsException("Insufficient funds for the withdrawal.");
		}
	}
	
	// SuperClass collectInterest
	public void collectInterest() 
	{
		balance = balance * 1;
	}
	
	// toString method
	public String toString() 
	{
		return "";
	}
}
