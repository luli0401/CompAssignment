//Main Class
import java.io.BufferedReader;
import java.io.FileReader;

public class Main
{
	//Main method
	public static void main(String[] args)
	{
		//String constant
		final String INPUT_FILE_NAME = "inputPhase4.txt";
		Bank bank = new Bank();

		//Read file
		readInputFile(INPUT_FILE_NAME, bank);
	}

	// ReadInputFile method gets the filename as a parameter. This method should open the file for reading,
	// read it line by line and call the following method (parseCommand) to process each line
	private static void readInputFile(String fileName, Bank bank) 
	{
		BufferedReader reader;
		String line = "";

		try
		{
			// read file
			reader = new BufferedReader(new FileReader(fileName));
			line = reader.readLine();

			// process each line
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

	// ParseCommand method which receives one line of the file
	// and determines which type of command this is. 
	private static void parseCommand(String line, Bank bank)
	{
		String[] stringTokens = line.split("\\s+");

		if (stringTokens[0].equals("#"))
		{
			processComment(line);
		}
		else if (stringTokens[0].equals("NEW-CLIENT"))
		{
			try 
			{
				bank.addBankClient(stringTokens[1], stringTokens[2]);
				System.out.println("NEW CLIENT CREATED");
			}
			catch (Exception e) 
			{
				printCommandArgsException(stringTokens[0] + ": First or last name is missing.");
			}
		}
		else if(stringTokens[0].equals("NEW-ACCOUNT"))
		{
			BankClient client = bank.getBankClient(stringTokens[2], stringTokens[3]);
			double balance = 0;
			int bankAccountId = 0;
				
			try 
			{
				balance = Double.parseDouble(stringTokens[4]);

				if (client == null) 
				{
					System.out.println("CLIENT NOT FOUND");
				} 
				else 
				{
					if (stringTokens[1].equals("CHQ")) 
					{
						bankAccountId = bank.addChequingAccount(client, balance);
					} 
					else if (stringTokens[1].equals("SVG")) 
					{
						bankAccountId = bank.addSavingsAccount(client, balance);
					} 
					else 
					{
						printCommandArgsException(stringTokens[0] + ": " + stringTokens[1] + " is not a valid account type.");
					}

					System.out.println("NEW ACCOUNT OF TYPE " + stringTokens[1] + " CREATED WITH ID = " + bankAccountId);
				} 
			} 
			catch (Exception e) 
			{
				printCommandArgsException(stringTokens[0] + ": account type, first name, last name or balance is missing.");
			}
		}
		else if(stringTokens[0].equals("GET-ACCOUNT-INFO"))
		{
			int accountId = 0;
			
			try 
			{
				accountId = Integer.parseInt(stringTokens[1]);
				BankAccount account = bank.getBankAccount(accountId);
	
				if (account == null) 
				{
					System.out.println("ACCOUNT NOT FOUND");
				} 
				else 
				{
					System.out.println(account);
				} 
			} 
			catch (Exception e) 
			{
				printCommandArgsException(stringTokens[0] + ": accountId is missing or incorrect.");
			}			
		}
		else if(stringTokens[0].equals("DEPOSIT"))
		{
			double amount = 0;
			try
			{					
				int accountId = Integer.parseInt(stringTokens[1]);
				BankAccount account = bank.getBankAccount(accountId);
			
				if(account == null)
				{
					System.out.println("ACCOUNT NOT FOUND");
				}
				else
				{
					amount = Double.parseDouble(stringTokens[2]);
					account.deposit(amount);
					System.out.println("DEPOSIT COMPLETED");
				}
			}
			catch(Exception ex)
			{		
				Exception exception = new InvalidCommandArgsException(stringTokens[0] +" accountId or amount is missing or incorrect.");
				System.out.println(exception);
			}				
		}
		else if(stringTokens[0].equals("WITHDRAW"))
		{
			double amount = 0;
			try
			{					
				int accountId = Integer.parseInt(stringTokens[1]);
				BankAccount account = bank.getBankAccount(accountId);
				
				if(account == null)
				{
					System.out.println("ACCOUNT NOT FOUND");
				}
				else
				{
					amount = Double.parseDouble(stringTokens[2]);
					try
					{						
						account.withdraw(amount);
					}
					catch(Exception exception)
					{
						System.out.println(exception);
					}
					System.out.println("WITHDRAWAL COMPLETED");
				}
			}
			catch(Exception ex)
			{		
				Exception exception = new InvalidCommandArgsException(stringTokens[0] +" accountId or amount is missing or incorrect.");
				System.out.println(exception);
			}				
		}
		else if(stringTokens[0].equals("INTEREST"))
		{			
			bank.payInterest();
			System.out.println("INTEREST WAS PAID");			
		}
		else if (stringTokens[0].equals("GET-CLIENT-INFO"))
		{
			try 
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
			catch(Exception ex)
			{
				Exception exception = new InvalidCommandArgsException(stringTokens[0] + ": First or last name is missing.");
				System.out.println(exception);
			}
		}
		else
		{			
			Exception exception = new WrongCommandException("The command "+ stringTokens[0] +" is not recognized.");
			System.out.println(exception);
		}
	}

	// Print CommandArgsException 
	private static void printCommandArgsException(String message)
	{		
		Exception exception = new InvalidCommandArgsException(message);
		System.out.println(exception);	
	}

	// ProcessComment method which will handle a comment command
	private static void processComment(String text)
	{
		System.out.println(text);
	}
}
