package Comp1020_A2;
// Gameboard class. contain a two-dimensional array of Tile objects, creating a game board for a single player
public class Gameboard
{
	// instance variables
	Tile[][] gameboard;
	boolean isOpponent;

	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;

	// Constructor
	public Gameboard(int row, int column, boolean isOpponent)
	{
		gameboard = new Tile[row][column];

		for (int i = 0; i < row; i++)
		{
			for (int j = 0; j < column; j++)
			{
				gameboard[i][j] = new Tile();
			}
		}

		this.isOpponent = isOpponent;
	}

	// returns a string representation of the entire gameboard
	public String toString()
	{
		String gameBoardString = "";

		for (int i = 0; i < gameboard.length; i++)
		{
			for (int j = 0; j < gameboard[i].length; j++)
			{
				gameBoardString += gameboard[i][j].getDisplay(isOpponent);
			}
			gameBoardString += "\n";
		}

		return gameBoardString;
	}

	// fetches row rowNum from the board, returning it as a String
	public String getRow(int rowNum)
	{
		String rowString = "";

		for (int j = 0; j < gameboard[rowNum].length; j++)
		{
			rowString += gameboard[rowNum][j].getDisplay(isOpponent);
		}

		return rowString;
	}

	// sets the isAttacked flag on the tile at index row, column
	public void doAttack(int row, int column)
	{
		if(row >= 0 && row < gameboard.length &&
				column >= 0 && column < gameboard[row].length)
		{
			gameboard[row][column].attack();
		}
	}

	// returns a Tile array containing the contents of the specified row
	public Tile[] extractRow(int row)
	{
		Tile[] tileArray = new Tile[gameboard[row].length];

		for (int j = 0; j < gameboard[row].length; j++)
		{
			tileArray[j] = gameboard[row][j];
		}

		return tileArray;
	}

	// returns a Tile array containing the contents of the specified column
	public Tile[] extractColumn(int column)
	{
		Tile[] tileArray = new Tile[gameboard.length];

		for (int j = 0; j < gameboard.length; j++)
		{
			tileArray[j] = gameboard[j][column];
		}

		return tileArray;
	}

	// reverse the provided array
	public static void reverse(Tile[] data)
	{
		Tile temp = null;

		for (int i = 0; i < data.length / 2; i++)
		{
			temp = data[i];
			data[i] = data[data.length - i - 1];
			data[data.length - i - 1] = temp;
		}
	}

	// adding ships to the board
	public boolean addShip(int row, int col, int length, int direction, char display)
	{
		boolean canAddShip = true;
		int start = 0;
		Tile[] tiles = null;

		// check the direction
		if (direction == UP)
		{
			start = gameboard.length - 1 - row;
			tiles = extractColumn(col);
			reverse(tiles);
		}
		else if (direction == DOWN)
		{
			start = row;
			tiles = extractColumn(col);
		}
		else if (direction == RIGHT)
		{
			start = col;
			tiles = extractRow(row);
		}
		else if (direction == LEFT)
		{
			start = gameboard.length - 1 - col;
			tiles = extractRow(row);
			reverse(tiles);
		}

		// check if it can place a ship
		canAddShip = canPlaceShipInArray(tiles, start, length);

		// place the ship
		if(canAddShip)
		{
			placeShipInArray(tiles, start, length, display);
		}
		
		return canAddShip;
	}
	
	// adds a ship into this array
	private static void placeShipInArray(Tile [] tiles, int start, int length, char letter) 
	{
		for (int i = start; i < (start + length); i++)
		{
			tiles[i].setDisplayLetter(letter); 
		}
	}
	
	// checks to see if a ship could be placed in this array
	private static boolean canPlaceShipInArray(Tile[] tiles, int start, int length)
	{
		boolean canAddShip = true;		
		
		if ((start + length) > tiles.length)
		{
			canAddShip = false;
		}		
		else
		{
			for (int i = start; i < length; i++)
			{
				if (!tiles[i].canPlaceShip())
				{
					canAddShip = false;
				}
			}
		}
		return canAddShip;
	}
	
	// returns true if the player has lost
	public boolean hasLost()
	{
		boolean lost = true;
		
		for (int i = 0; i < gameboard.length; i++)
		{
			for (int j = 0; j < gameboard[i].length; j++)
			{
				if(gameboard[i][j].activeShip()) 
				{
					lost = false;
				}
			}
		}
		
		return lost;
	}
}
