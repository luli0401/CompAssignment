
public class A2TestPhase1 {

	public static void main(String[] args) {
		
		Tile emptyTile = new Tile();
		
		System.out.println("Should be a wave ~: " + emptyTile.getDisplay(false));
		emptyTile.attack();
		System.out.println("Should be a splash ^: " + emptyTile.getDisplay(false));
		System.out.println("Should be a splash ^: " + emptyTile.getDisplay(true));
		
		Tile tileWithShip = new Tile();
		tileWithShip.setDisplayLetter('C');
		System.out.println("Should be a C: " + tileWithShip.getDisplay(false));
		tileWithShip.setDisplayLetter('~');
		System.out.println("Should be a ~: " + tileWithShip.getDisplay(true));
		tileWithShip.attack();
		System.out.println("Should be an explosion *: " + tileWithShip.getDisplay(false));
		
		Gameboard myBoard = new Gameboard(2, 2, false);
		
		System.out.println("Print a 2x2 board of waves ~:");
		System.out.println(myBoard);
		
		myBoard.doAttack(1, 1);
		
		System.out.println("Should have a splash ^ on second row, second column");
		System.out.println(myBoard);
		
		myBoard.doAttack(0, 0);
		
		System.out.println("Should be ^~ : " + myBoard.getRow(0) );
		System.out.println("Should be ~^ : " + myBoard.getRow(1) );
		
		Gameboard theirBoard = new Gameboard(3, 3, true);
		theirBoard.doAttack(0, 0);
		theirBoard.doAttack(1, 1);
		theirBoard.doAttack(2, 2);
		System.out.println("Print splashes down the x=y axis of a 3x3 board:");
		System.out.println(theirBoard);
	}

}
