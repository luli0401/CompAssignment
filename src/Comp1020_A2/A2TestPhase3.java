package Comp1020_A2;

public class A2TestPhase3 {

	public static void main(String[] args) {
		
		Gameboard myBoard = new Gameboard(5, 5, false);
		
		System.out.println("Print a 5x5 board of waves ~:");
		System.out.println(myBoard);
		
		boolean result = myBoard.addShip(1, 1, 2, Gameboard.RIGHT, 'A');
		
		System.out.println("Added ship, should be true: " + result);
		
		System.out.println("Print a 5x5 with one ship, starting at [1,1]. with length 2, going right:");
		System.out.print("~~~~~ ");
		System.out.println(myBoard.getRow(0));
		System.out.print("~AA~~ ");
		System.out.println(myBoard.getRow(1));
		System.out.print("~~~~~ ");
		System.out.println(myBoard.getRow(2));
		System.out.print("~~~~~ ");
		System.out.println(myBoard.getRow(3));
		System.out.print("~~~~~ ");
		System.out.println(myBoard.getRow(4));
		
		
		result = myBoard.addShip(1, 4, 2, Gameboard.DOWN, 'B');
		
		System.out.println("Added ship, should be true: " + result);
		
		System.out.println("Print a 5x5 with one ship, starting at [1,4]. with length 2, going down:");
		System.out.print("~~~~~ ");
		System.out.println(myBoard.getRow(0));
		System.out.print("~AA~B ");
		System.out.println(myBoard.getRow(1));
		System.out.print("~~~~B ");
		System.out.println(myBoard.getRow(2));
		System.out.print("~~~~~ ");
		System.out.println(myBoard.getRow(3));
		System.out.print("~~~~~ ");
		System.out.println(myBoard.getRow(4));
		
		result = myBoard.addShip(3, 4, 2, Gameboard.LEFT, 'C');
		
		System.out.println("Added ship, should be true: " + result);
		
		System.out.println("Print a 5x5 with one ship, starting at [3,4]. with length 2, going left:");
		System.out.print("~~~~~ ");
		System.out.println(myBoard.getRow(0));
		System.out.print("~AA~B ");
		System.out.println(myBoard.getRow(1));
		System.out.print("~~~~B ");
		System.out.println(myBoard.getRow(2));
		System.out.print("~~~CC ");
		System.out.println(myBoard.getRow(3));
		System.out.print("~~~~~ ");
		System.out.println(myBoard.getRow(4));
		
		
		result = myBoard.addShip(4, 2, 2, Gameboard.UP, 'D');
		
		System.out.println("Added ship, should be true: " + result);
		
		System.out.println("Print a 5x5 with one ship, starting at [4,2]. with length 2, going up:");
		System.out.print("~~~~~ ");
		System.out.println(myBoard.getRow(0));
		System.out.print("~AA~B ");
		System.out.println(myBoard.getRow(1));
		System.out.print("~~~~B ");
		System.out.println(myBoard.getRow(2));
		System.out.print("~~DCC ");
		System.out.println(myBoard.getRow(3));
		System.out.print("~~D~~ ");
		System.out.println(myBoard.getRow(4));
		
		result = myBoard.addShip(3, 2, 3, Gameboard.UP, 'E');
		
		System.out.println("Added ship, should be false: " + result);
		
		System.out.println("Should be unchanged");
		System.out.println(myBoard);
		
		result = myBoard.addShip(0, 0, 2, Gameboard.UP, 'F');
		
		System.out.println("Added ship, should be false: " + result);
		
		System.out.println("Should be unchanged");
		System.out.println(myBoard);
		
		result = myBoard.addShip(0, 0, 2, Gameboard.LEFT, 'D');
		
		System.out.println("Added ship, should be false: " + result);
		
		System.out.println("Should be unchanged");
		System.out.println(myBoard);
		
		result = myBoard.addShip(0, 0, 10, Gameboard.RIGHT, 'D');
		
		System.out.println("Added ship, should be false: " + result);
		
		System.out.println("Should be unchanged");
		System.out.println(myBoard);
		
		// set up a game board for the opponent. We should
		// not see their peices.
		Gameboard theirBoard = new Gameboard(10, 5, true);
		theirBoard.addShip(1, 1, 2, Gameboard.RIGHT, 'A');
		theirBoard.addShip(9, 4, 2, Gameboard.UP, 'B');
		System.out.println("Should be waves");
		System.out.println(theirBoard);
		
		// test attacking
		
		theirBoard.doAttack(1, 1);
		System.out.println("Should have an explosion * at 1, 1:");
		System.out.println(theirBoard);
		System.out.println("They should not have lost yet, should be false: " + theirBoard.hasLost());
		
		
		theirBoard.doAttack(0, 0);
		System.out.println("Should have a splash ^ at 0, 0:");
		System.out.println(theirBoard);
		System.out.println("They should not have lost yet, should be false: " + theirBoard.hasLost());
		
		theirBoard.doAttack(1, 2);
		theirBoard.doAttack(9, 4);
		theirBoard.doAttack(8, 4);
		System.out.println("Should have an explosion * at 9,3 and 9,4 :");
		System.out.println(theirBoard);
		System.out.println("They should have lost, should be true: " + theirBoard.hasLost());

	}
	

}
