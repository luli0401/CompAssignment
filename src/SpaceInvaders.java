import java.util.ArrayList;

public class SpaceInvaders
{
	static final int INITIAL_X = 25;
	static final int INITIAL_Y = 40;
	static final int SHIP_SPEED = 10;
	static final int BULLET_SPEED = 5;
	static final int ALIENS_SPEED = 1;

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
		int alienX = 0;
		int alienY = 0;
		Alien alien = null;

		for (int i = 0; i < 24; i++)
		{
			alienY = INITIAL_X + INITIAL_X * (i % 4);

			if ((i % 4) == 0)
			{
				alienX += INITIAL_Y;
			}

			alien = new Alien(alienX, alienY);
			spriteList.add(alien);
		}
	}

	private void CreateShip()
	{
		Ship playerShip = new Ship(height / 2, width - 70);
		spriteList.add(playerShip);
	}

	public void update()
	{
		updateBullet();
		moveAliens();
	}

	private void moveAliens()
	{
		ArrayList<Alien> alienList = getAllAliens();
		int speed = ALIENS_SPEED;
		
		for (int i = 0; i < alienList.size(); i++)
		{
			alienList.get(i).moveSideWay(speed);

			if (alienList.get(i).getX() < 0 || alienList.get(i).getX() > this.width)
			{
				speed = -speed;
//				alienList.get(i).moveDown(ALIENS_SPEED);

				System.out.println(alienList.get(i).getX() +" -- "+ speed);
			}
		}
	}

	private ArrayList<Alien> getAllAliens()
	{
		ArrayList<Alien> alienList = new ArrayList<Alien>();

		for (int i = 0; i < spriteList.size(); i++)
		{
			if (spriteList.get(i) instanceof Alien)
			{
				alienList.add((Alien) spriteList.get(i));
			}
		}

		return alienList;
	}

	private void updateBullet()
	{
		ArrayList<Bullet> bulletList = getAllBullets();

		for (int i = 0; i < bulletList.size(); i++)
		{
			bulletList.get(i).moveBullet(BULLET_SPEED);

			if (bulletList.get(i).getY() < 0)
			{
				spriteList.remove(bulletList.get(i));
			}
		}
	}

	private ArrayList<Bullet> getAllBullets()
	{
		ArrayList<Bullet> bulletList = new ArrayList<Bullet>();

		for (int i = 0; i < spriteList.size(); i++)
		{
			if (spriteList.get(i) instanceof Bullet)
			{
				bulletList.add((Bullet) spriteList.get(i));
			}
		}

		return bulletList;
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
			x -= SHIP_SPEED;
		}
		else
		{
			x += SHIP_SPEED;
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
			bulletX = playerShip.getX();
			bulletY = playerShip.getY() - (playerShip.getColorGrid().length);

			bullet = new Bullet(bulletX, bulletY);
			spriteList.add(bullet);
		}
	}
}
