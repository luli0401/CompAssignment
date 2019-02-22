
public class Gameboard
{
	Tile[][] gameboard;
	boolean isOpponent;

	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;

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

	public String getRow(int rowNum)
	{
		String rowString = "";

		for (int j = 0; j < gameboard[rowNum].length; j++)
		{
			rowString += gameboard[rowNum][j].getDisplay(isOpponent);
		}

		return rowString;
	}

	public void doAttack(int row, int column)
	{
		gameboard[row][column].attack();
	}

	public Tile[] extractRow(int row)
	{
		Tile[] tileArray = new Tile[gameboard[row].length];

		for (int j = 0; j < gameboard[row].length; j++)
		{
			tileArray[j] = gameboard[row][j];
		}

		return tileArray;
	}

	public Tile[] extractColumn(int column)
	{
		Tile[] tileArray = new Tile[gameboard.length];

		for (int j = 0; j < gameboard.length; j++)
		{
			tileArray[j] = gameboard[j][column];
		}

		return tileArray;
	}

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

	public boolean addShip(int row, int col, int length, int direction, char display)
	{
		boolean canAddShip = true;
		int start = 0;
		Tile[] tiles = null;

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

		canAddShip = canPlaceShipInArray(tiles, start, length);

		return canAddShip;
	}

	private static boolean canPlaceShipInArray(Tile[] tiles, int start, int length)
	{
		boolean canAddShip = true;

		if ((start + length) >= tiles.length)
		{
			canAddShip = false;
		}

		for (int i = 0; i < tiles.length; i++)
		{
			if (!tiles[i].canPlaceShip())
			{
				canAddShip = false;
			}
		}

		return canAddShip;
	}
}
