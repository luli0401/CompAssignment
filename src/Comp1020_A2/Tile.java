package Comp1020_A2;
// Tile class, single space on the board
public class Tile
{
	// instance variables
	boolean wasAttacked;
	boolean hasShip;
	char displayLetter;

	// Constructor
	public Tile()
	{
		wasAttacked = false;
		hasShip = false;
		displayLetter = '~';
	}

	// setter method for displayLetter
	public void setDisplayLetter(char letter)
	{
		displayLetter = letter;
		hasShip = true;
	}

	// returns the character, as a String, to display when showing this tile to the user
	public String getDisplay(boolean isOpponent)
	{
		String displayChar = "";

		if (!isOpponent)
		{
			if (!wasAttacked && hasShip) 			// Show the character saved in displayLetter if it has not been attacked, and has a ship
			{
				displayChar = displayLetter + "";
			}
			else if (!wasAttacked && !hasShip)		// Show a wave (~) if this tile has not been attacked, and does not have a ship
			{
				displayChar = "~";
			}
			else if (wasAttacked && !hasShip)		// Show a splash (^) if this tile does not have a ship, and has been attacked
			{
				displayChar = "^";
			}
			else if (wasAttacked && hasShip)		// Show an explosion (*) if this tile has been attacked, and does have a ship
			{
				displayChar = "*";
			}
		}
		else
		{
			if (!wasAttacked)						// Show a wave (~) if this tile has not been attacked
			{
				displayChar = "~";
			}
			else if (wasAttacked && !hasShip)		// Show a splash (^) if this tile does not have a ship, and has been attacked
			{
				displayChar = "^";
			}
			else if (wasAttacked && hasShip)		// Show an explosion (*) if this tile has been attacked, and does have a ship
			{
				displayChar = "*";
			}
		}

		return displayChar;
	}

	// sets wasAttacked to true
	public void attack()
	{
		wasAttacked = true;
	}

	// returns true if a ship could be placed on this Tile
	public boolean canPlaceShip()
	{
		boolean canPlaceShip = false;

		if (!wasAttacked && !hasShip)
		{
			canPlaceShip = true;
		}

		return canPlaceShip;
	}

	// returns true if there is a ship on this tile, and it has not been attacked
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
