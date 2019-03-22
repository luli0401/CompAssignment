import java.awt.Color;

public class Bullet extends Sprite
{
	public Bullet(double x, double y)
	{
		super(x, y);
	}

	public Color[][] getColorGrid()
	{
		return super.getColorGrid();
	}

	public void moveBullet(int bulletSpeed)
	{
		y -= bulletSpeed; 		
	}
}
