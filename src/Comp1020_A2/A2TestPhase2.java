
public class A2TestPhase2 {

	public static void main(String[] args) {
		Gameboard myBoard = new Gameboard(2, 3, false);
		
		System.out.println("Print a 2x3 board of waves ~:");
		System.out.println(myBoard);
		
		myBoard.doAttack(1, 1);
		myBoard.doAttack(1, 2);
		
		System.out.println("Should have a splash on second row, second and third column");
		System.out.println(myBoard);
		
		Tile [] row = myBoard.extractRow(1);
		
		System.out.println("Should be ~^^");
		for (Tile curr : row)
			System.out.print(curr.getDisplay(false));
		System.out.println();
		
		Gameboard.reverse(row);
		System.out.println("Should be ^^~");
		for (Tile curr : row)
			System.out.print(curr.getDisplay(false));
		System.out.println();
		
		Tile [] col = myBoard.extractColumn(1);
		System.out.println("Should be ~^");
		for (Tile curr : col)
			System.out.print(curr.getDisplay(false));
		System.out.println();
		
		Gameboard.reverse(col);
		System.out.println("Should be ^~");
		for (Tile curr : col)
			System.out.print(curr.getDisplay(false));
		System.out.println();
		
		System.out.println("The original should be unchanged");
		System.out.println(myBoard);
		
	}
}
