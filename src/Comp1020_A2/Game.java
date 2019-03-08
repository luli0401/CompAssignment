package Comp1020_A2;
// Game class that has two or more players that take turns attacking the other player’s ships
import java.util.Scanner;

public class Game 
{
	// instance variables
	 int turn;
	 Gameboard[] gameboards;
	 
	 int height;
	 int width;

	 // Constructor
	 public Game(int height, int width, int numPlayers)
	 {
		 turn = 0;	
		 this.height = height;
		 this.width = width;
		 gameboards = new Gameboard[numPlayers];
		 
		 for(int i = 0; i < gameboards.length; i++)
		 {
			 if(i == 0)
			 {
				 gameboards[i] = new Gameboard(height, width, false);
			 }
			 else
			 {
				 gameboards[i] = new Gameboard(height, width, true);
			 }
		 }
	 }
	 
	 // randomly chooses any of the boards, and randomly chooses a single tile to attack on that board
	 public void randomAttack()
	 {
		 int boardIndex = random(gameboards.length);
		 int boardRow;
		 int boardCol;
		 
		 while(boardIndex == turn || gameboards[boardIndex].hasLost())
		 {
			 boardIndex = random(gameboards.length);
		 }
		 
		 boardRow = random(height);
		 boardCol = random(width);
		 
		 gameboards[boardIndex].doAttack(boardRow, boardCol);
		 System.out.println("Game Board " + turn + ", row " + boardRow + " column " + boardCol + " has been attacked.");
	 }
	 
	 // attack the player specified by player ID at the given row and column
	 public void attack(int playerID, int row, int column)
	 {
		 gameboards[playerID].doAttack(row, column);
	 }
	 
	 // adds a ship on the board indicated by player
	 public void addShip(int player, int row, int col, int length, int direction, char letter)
	 {
		boolean canAddShip = gameboards[player].addShip(row, col, length, direction, letter);
		
		if(!canAddShip)
		{
			System.out.println("Cannot add a ship at "+ row +", " + col);	
		}
	 }
	 
	 // plays the game
	 public void playGame()
	 {
		 int activeShipCount = gameboards.length;
		 
		 while(activeShipCount > 1)					// check if the game is end
		 {
			 // take each player's turn
			 System.out.println("\nPlayer "+ turn +"'s turn.");	
			 if(turn == 0)
			 {
				 playerAttack();
			 }
			 else
			 {				
				 randomAttack();				
			 }	
			 System.out.println("\nPlayer "+ turn +"'s turn is done.");	
			
			 activeShipCount = calculateActiveShip();
			 
			 // if game ends, print the message
			 if(activeShipCount == 1)
			 {
				 System.out.println("Congratulations. Player " + turn + "!!!!!");
			 }
			 
			 turn++;			 
			 turn = turn % gameboards.length;
		 }
	}
	 
	// player's turn to attack
	private void playerAttack() 
	{
		Scanner scanner = new Scanner(System.in);
		int boardNumber = 0;
		int attackRow = 0;
		int attackCol = 0;
		
		// ask player where to attack 
		System.out.println("All the game board number: ");
		for(int i = 1; i < gameboards.length; i++)
		{
			System.out.print(i + " ");
		}		
		System.out.println("\nPlease type one game board number to attack: ");		
		
		boardNumber = scanner.nextInt();
		
		// attack if the player's input is valid
		if(boardNumber > 0 && boardNumber < gameboards.length && !gameboards[boardNumber].hasLost())
		{
			System.out.println("\nPlease type row number to attack: ");				
			attackRow = scanner.nextInt();
			
			System.out.println("\nPlease type column number to attack: ");				
			attackCol = scanner.nextInt();
			
			gameboards[boardNumber].doAttack(attackRow, attackCol);			
		}		
	}

	// check how many boards have active ships
	private int calculateActiveShip() 
	{
		 int activeShipCount = 0;
		 
		 for(int i = 0; i < gameboards.length; i++)
		 {
			 if(!gameboards[i].hasLost())
			 {
				 activeShipCount++;
			 }			
		 }
		 
		return activeShipCount;
	}

	// get a random integer
	public static int random(int max) 
	{
		return (int)(Math.random() * max);
	}

}
