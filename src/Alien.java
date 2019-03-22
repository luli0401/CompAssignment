import java.awt.Color;

public class Alien extends Sprite
{
	public Alien(double x, double y)
	{
		super(x, y);
	}

	public Color[][] getColorGrid()
	{
		return Display.ALIEN_SHAPE;
	}

	public void moveSideWay(int alienSpeed)
	{
		x += alienSpeed; 			
	}

	public void moveDown(int aliensSpeed)
	{
		y += aliensSpeed; 			
	}
}
