import java.util.ArrayList;

public class SpaceInvaders
{
	static final int INITIAL_Y = 30;
	static final int INITIAL_X = 20;

	int height;
	int width;
	int status;
	ArrayList<Sprite> spriteList;

	public SpaceInvaders(int height, int width)
	{
		this.height = height;
		this.width = width;
		this.spriteList = new ArrayList<Sprite>();
		this.status = Display.CONTINUE;

		CreateShip();
		CreateAliens();
	}

	private void CreateAliens()
	{
		int x = 20;
		int y = 0;
		Alien alien = null;

		for (int i = 0; i < 24; i++)
		{
			x += INITIAL_X * (i % 6);

			if ((i % 6) == 0)
			{
				y += INITIAL_Y;
			}

			alien = new Alien(x, y);
			spriteList.add(alien);
		}
	}

	private void CreateShip()
	{
		Ship playerShip = new Ship(height - 10, width / 2);
		spriteList.add(playerShip);
	}

	public void update()
	{

	}

	public ArrayList<Sprite> getItems()
	{
		return spriteList;
	}

	public int status()
	{
		return status;
	}

	public void move(int direction)
	{
		Ship playerShip = null;
		double x = 0;

		for (int i = 0; i < spriteList.size(); i++)
		{
			if (spriteList.get(i) instanceof Ship)
			{
				playerShip = (Ship) spriteList.get(i);
			}
		}

		x = playerShip.getX();

		if (direction == Display.MOVE_LEFT)
		{
			x -= 5;
		}
		else
		{
			x += 5;
		}

		playerShip.setX(x);
	}

	public void shoot()
	{
		int count = 0;
		double bulletX = 0;
		double bulletY = 0;
		Bullet bullet = null;
		Ship playerShip = null;

		for (int i = 0; i < spriteList.size(); i++)
		{
			if (spriteList.get(i) instanceof Bullet)
			{
				count++;
			}

			if (spriteList.get(i) instanceof Ship)
			{
				playerShip = (Ship) spriteList.get(i);
			}
		}

		if (count < 2)
		{
			bulletX = playerShip.getX() + 3.5;
			bulletY = playerShip.getY();
			
			bullet = new Bullet(bulletX, bulletY);
			spriteList.add(bullet);
		}
	}
}
