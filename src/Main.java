import java.io.BufferedReader;
import java.io.FileReader;

public class Main
{
	public static void main(String[] args)
	{
		final String INPUT_FILE_NAME = "inputPhase3.txt";
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
