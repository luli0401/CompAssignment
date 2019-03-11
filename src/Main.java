import java.io.BufferedReader;
import java.io.FileReader;

public class Main
{
	public static void main(String[] args)
	{
		final String INPUT_FILE_NAME = "inputPhase4.txt";
		Bank bank = new Bank();

		readInputFile(INPUT_FILE_NAME, bank);
	}

	private static void readInputFile(String fileName, Bank bank) 
	{
		BufferedReader reader;
		String line = "";

		try
		{
			reader = new BufferedReader(new FileReader(fileName));
			line = reader.readLine();

			while (line != null)
			{
				if (line.length() != 0)
				{
					parseCommand(line, bank);
				}

				line = reader.readLine();
			}
			reader.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void parseCommand(String line, Bank bank)
	{
		String[] stringTokens = line.split("\\s+");

		if (stringTokens[0].equals("#"))
		{
			processComment(line);
		}
		else if (stringTokens[0].equals("NEW-CLIENT"))
		{
			if(!hasInvalidCommandArgsException(stringTokens))
			{
				bank.addBankClient(stringTokens[1], stringTokens[2]);
				System.out.println("NEW CLIENT CREATED");
			}
		}
		else if(stringTokens[0].equals("NEW-ACCOUNT"))
		{
			BankClient client = bank.getBankClient(stringTokens[2], stringTokens[3]);
			double balance = 0;
			int bankAccountId = 0;
			
			if(stringTokens.length < 5) 
			{			
				Exception exception = new InvalidCommandArgsException(stringTokens[0] + ": account type, first name, last name or balance is missing.");
				System.out.println(exception);
			}	
			else
			{
				balance = Double.parseDouble(stringTokens[4]);
				
				if(client == null)
				{
					System.out.println("CLIENT NOT FOUND");
				}
				else
				{
					if(stringTokens[1].equals("CHQ"))
					{
						bankAccountId = bank.addChequingAccount(client, balance);
					}
					else if(stringTokens[1].equals("SVG"))
					{
						bankAccountId = bank.addSavingsAccount(client, balance);
					}	
					else
					{
						Exception exception = new InvalidCommandArgsException(stringTokens[1] + ": is not a valid account type.");
						System.out.println(exception);
					}

					System.out.println("NEW ACCOUNT OF TYPE "+ stringTokens[1] +" CREATED WITH ID = " + bankAccountId);
			}	
			}
		}
		else if(stringTokens[0].equals("GET-ACCOUNT-INFO"))
		{
			int accountId = 0;
			
			if(stringTokens.length < 2) 
			{			
				Exception exception = new InvalidCommandArgsException(stringTokens[0] + ": accountId is missing or incorrect.");
				System.out.println(exception);
			}	
			else
			{
				accountId = Integer.parseInt(stringTokens[1]);
				BankAccount account = bank.getBankAccount(accountId);
				
				if(account == null)
				{
					System.out.println("ACCOUNT NOT FOUND");
				}
				else
				{
					System.out.println(account);
				}
			}
		}
		else if(stringTokens[0].equals("DEPOSIT"))
		{
			double amount = 0;
			int accountId = Integer.parseInt(stringTokens[1]);
			BankAccount account = bank.getBankAccount(accountId);
			
			if(account == null)
			{
				System.out.println("ACCOUNT NOT FOUND");
			}
			else
			{
				try
				{					
					amount = Double.parseDouble(stringTokens[2]);
					account.deposit(amount);
					System.out.println("DEPOSIT COMPLETED");
				}
				catch(Exception ex)
				{		
					Exception exception = new InvalidCommandArgsException(stringTokens[0] +" accountId or amount is missing or incorrect.");
					System.out.println(exception);
				}				
			}
		}
		else if(stringTokens[0].equals("WITHDRAW"))
		{
			double amount = 0;
			int accountId = Integer.parseInt(stringTokens[1]);
			BankAccount account = bank.getBankAccount(accountId);
			
			if(account == null)
			{
				System.out.println("ACCOUNT NOT FOUND");
			}
			else
			{
				try
				{					
					amount = Double.parseDouble(stringTokens[2]);
					account.withdraw(amount);
					System.out.println("WITHDRAWAL COMPLETED");
				}
				catch(Exception ex)
				{		
					Exception exception = new InvalidCommandArgsException(stringTokens[0] +" accountId or amount is missing or incorrect.");
					System.out.println(exception);
				}				
			}
		}
		else if(stringTokens[0].equals("INTEREST"))
		{			
			bank.payInterest();
			System.out.println("INTEREST WAS PAID");			
		}
		else if (stringTokens[0].equals("GET-CLIENT-INFO"))
		{
			if(!hasInvalidCommandArgsException(stringTokens))
			{
				BankClient bankClient = bank.getBankClient(stringTokens[1], stringTokens[2]);
				if (bankClient != null)
				{
					System.out.println(bankClient);
				}
				else
				{
					System.out.println("CLIENT NOT FOUND");
				}
			}
		}
		else
		{			
			Exception exception = new WrongCommandException("The command "+ stringTokens[0] +" is not recognized.");
			System.out.println(exception);
		}
	}

	private static boolean hasInvalidCommandArgsException(String[] stringTokens)
	{
		boolean hasException = false;
		
		if(stringTokens.length < 3) 
		{			
			Exception exception = new InvalidCommandArgsException(stringTokens[0] + ": First or last name is missing.");
			System.out.println(exception);
			hasException = true;
		}	
		return hasException;
	}

	private static void processComment(String text)
	{
		System.out.println(text);
	}
}
