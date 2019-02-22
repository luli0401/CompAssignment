
public class Tile
{
	boolean wasAttacked;
	boolean hasShip;
	char displayLetter;

	public Tile()
	{
		wasAttacked = false;
		hasShip = false;
		displayLetter = '~';
	}

	public void setDisplayLetter(char letter)
	{
		displayLetter = letter;
		hasShip = true;
	}

	public String getDisplay(boolean isOpponent)
	{
		String displayChar = "";

		if (!isOpponent)
		{
			if (!wasAttacked && hasShip)
			{
				displayChar = displayLetter + "";
			}
			else if (!wasAttacked && !hasShip)
			{
				displayChar = "~";
			}
			else if (wasAttacked && !hasShip)
			{
				displayChar = "^";
			}
			else if (wasAttacked && hasShip)
			{
				displayChar = "*";
			}
		}
		else
		{
			if (!wasAttacked)
			{
				displayChar = "~";
			}
			else if (wasAttacked && !hasShip)
			{
				displayChar = "^";
			}
			else if (wasAttacked && hasShip)
			{
				displayChar = "*";
			}
		}

		return displayChar;
	}

	public void attack()
	{
		wasAttacked = true;
	}

	public boolean canPlaceShip()
	{
		boolean canPlaceShip = false;

		if (!wasAttacked && !hasShip)
		{
			canPlaceShip = true;
		}

		return canPlaceShip;
	}

	public boolean activeShip()
	{
		boolean activeShip = false;

		if (hasShip && !wasAttacked)
		{
			activeShip = true;
		}

		return activeShip;
	}	
}
