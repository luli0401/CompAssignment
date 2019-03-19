package Comp1020_A3;

public class Bank
{
	BankClient[] bankClientList;
	int index;

	public Bank()
	{
		bankClientList = new BankClient[99999];
		index = 0;
	}

	public void addBankClient(String firstName, String lastName)
	{
		long bankClientId = (1020 * 1000000000000L) + index;
		BankClient bankClient = new BankClient(firstName, lastName, bankClientId);

		bankClientList[index] = bankClient;
		index++;
	}

	public BankClient getBankClient(String firstName, String lastName)
	{
		BankClient result = null;

		for (int i = 0; i < index; i++)
		{
			if (bankClientList[i].getFirstName().equals(firstName) && bankClientList[i].getLastName().equals(lastName))
			{
				result = bankClientList[i];
			}
		}

		return result;
	}
}
