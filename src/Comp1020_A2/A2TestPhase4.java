package Comp1020_A2;

public class A2TestPhase4 {
	public static void main (String [] args) {
		Game game = new Game(5, 5, 2);
		
		game.addShip(0, 0, 0, 2, Gameboard.DOWN, 'A');
		game.addShip(1, 0, 0, 2, Gameboard.RIGHT, 'A');
		
		game.playGame();
	}
}
