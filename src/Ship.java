import java.awt.Color;

public class Ship extends Sprite
{
	public Ship(double x, double y)
	{
		super(x, y);
	}

	public Color[][] getColorGrid()
	{
		return Display.SHIP_SHAPE;
	}
}
