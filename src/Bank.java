
public class Bank
{
	BankClient[] bankClientList;
	int bankClientIndex;

	BankAccount[] bankAccountList;
	int bankAccountIndex;

	public Bank()
	{
		bankClientList = new BankClient[99999];		
		bankClientIndex = 0;

		bankAccountList = new BankAccount[99999];
		bankAccountIndex = 0;
	}

	public int addChequingAccount(BankClient bankClient, double balance)
	{
		int bankAccountId = (5 * 100000) + bankAccountIndex;
		ChequingAccount chequingAccount = new ChequingAccount(bankClient, balance, bankAccountId);
		
		bankAccountList[bankAccountIndex] = chequingAccount;
		bankAccountIndex++;		
		
		return bankAccountId;
	}
	
	public int addSavingsAccount(BankClient bankClient, double balance)
	{
		int bankAccountId = (5 * 100000) + bankAccountIndex;
		SavingsAccount savingAccount = new SavingsAccount(bankClient, balance, bankAccountId);
		
		bankAccountList[bankAccountIndex] = savingAccount;
		bankAccountIndex++;
	
		return bankAccountId;
	}
	
	public BankAccount getBankAccount(int accountId)
	{
		BankAccount bankAccount = null;
		
		for (int i = 0; i < bankAccountIndex; i++)
		{			
			if (bankAccountList[i].getAccountId() == accountId)
			{
				bankAccount = bankAccountList[i];
			}
		}
		
		return bankAccount;
	}
	
	public void payInterest()
	{
		for (int i = 0; i < bankAccountIndex; i++)
		{
			bankAccountList[i].collectInterest();
		}
	}
	
	public void addBankClient(String firstName, String lastName)
	{
		long bankClientId = (1020 * 1000000000000L) + bankClientIndex;
		BankClient bankClient = new BankClient(firstName, lastName, bankClientId);

		bankClientList[bankClientIndex] = bankClient;
		bankClientIndex++;
	}

	public BankClient getBankClient(String firstName, String lastName)
	{
		BankClient result = null;

		for (int i = 0; i < bankClientIndex; i++)
		{
			if (bankClientList[i].getFirstName().equals(firstName) && bankClientList[i].getLastName().equals(lastName))
			{
				result = bankClientList[i];
			}
		}

		return result;
	}
}
