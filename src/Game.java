
public class Game {

	 int turn;
	 Gameboard[] gameboards;
	 
	 int height;
	 int width;
	 
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
	 }
	 
	 public void attack(int playerID, int row, int column)
	 {
		 gameboards[playerID].doAttack(row, column);
	 }
	 
	 public void addShip(int player, int row, int col, int length, int direction, char letter)
	 {
		 gameboards[player].addShip(row, col, length, direction, letter);
	 }
	 
	 public void playGame()
	 {
		 int activeShipCount = gameboards.length;
		 
		 while(activeShipCount > 1)
		 {
			 if(turn == 0)
			 {
				 
			 }
			 else
			 {
				 randomAttack();
			 }
			 
			 turn++;			 
			 turn = turn % gameboards.length;
			 
			 activeShipCount = calculateActiveShip();
			 
			 if(activeShipCount == 1)
			 {
				 System.out.println("Congratulate player " + turn + "!!!!!");
			 }
		 }
	 }
	 
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

	public static int random(int max) 
	{
		return (int)(Math.random() * max);
	}

}
