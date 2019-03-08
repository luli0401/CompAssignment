import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main
{
	public static void main(String[] args)
	{
		final String INPUT_FILE_NAME = "inputPhase2.txt";
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
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static void parseCommand(String line, Bank bank)
	{
		// System.out.println(line);
		String[] stringTokens = line.split("\\s+");

		if (stringTokens[0].equals("#"))
		{
			processComment(line);
		}
		else if (stringTokens[0].equals("NEW-CLIENT"))
		{
			bank.addBankClient(stringTokens[1], stringTokens[2]);
			System.out.println("NEW CLIENT CREATED");
		}
		else if (stringTokens[0].equals("GET-CLIENT-INFO"))
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

	private static void processComment(String text)
	{
		System.out.println(text);
	}
}
