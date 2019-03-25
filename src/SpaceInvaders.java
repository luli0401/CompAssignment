import java.util.ArrayList;

public class SpaceInvaders
{
	static final int INITIAL_X = 25;
	static final int INITIAL_Y = 40;
	static final int SHIP_SPEED = 10;
	static final int BULLET_SPEED = 5;
	static final int ALIEN_DOWN_SPEED = 15;
	int alienSideSpeed = 1;

	int height;
	int width;
	int status;

	Ship playerShip;
	ArrayList<Alien> alienList;
	ArrayList<Bullet> bulletList;

	public SpaceInvaders(int height, int width)
	{
		this.height = height;
		this.width = width;

		playerShip = null;
		this.alienList = new ArrayList<Alien>();
		this.bulletList = new ArrayList<Bullet>();
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
			alienList.add(alien);
		}
	}

	private void CreateShip()
	{
		playerShip = new Ship(height / 2, width - 70);
	}

	public void update()
	{
		updateBullet();
		moveAliens();
		checkBulletHitAlien();
	}

	private void checkBulletHitAlien()
	{
		int bulletNum = -1;
		int alienNum = -1;

		for (int i = 0; i < bulletList.size(); i++)
		{
			for (int j = 0; j < alienList.size(); j++)
			{
				if (bulletList.get(i).getX() < (alienList.get(j).getX() + alienList.get(j).getColorGrid()[0].length * 2)
						&& bulletList.get(i)
								.getX() > (alienList.get(j).getX() - alienList.get(j).getColorGrid()[0].length * 2)
						&& bulletList.get(i).getY() < (alienList.get(j).getY()) && bulletList.get(i)
								.getY() > (alienList.get(j).getY() - alienList.get(j).getColorGrid().length))
				{
					bulletNum = i;
					alienNum = j;
				}
			}
		}

		if (bulletNum != -1)
		{
			bulletList.remove(bulletNum);
		}

		if (alienNum != -1)
		{
			alienList.remove(alienNum);
		}
	}

	private void moveAliens()
	{
		boolean outOfSide = checkAnyAliensOutOfSide();

		if (outOfSide)
		{
			alienSideSpeed = -alienSideSpeed;
		}

		for (int i = 0; i < alienList.size(); i++)
		{
			alienList.get(i).moveSideWay(alienSideSpeed);

			if (outOfSide)
			{
				alienList.get(i).moveDown(ALIEN_DOWN_SPEED);
			}
		}
	}

	private boolean checkAnyAliensOutOfSide()
	{
		boolean outOfSide = false;

		for (int i = 0; i < alienList.size(); i++)
		{
			if ((alienList.get(i).getX() - alienList.get(0).getColorGrid()[0].length) < 0
					|| (alienList.get(i).getX() + alienList.get(0).getColorGrid()[0].length * 2) > this.width)
			{
				outOfSide = true;
			}
		}

		return outOfSide;
	}

	private void updateBullet()
	{
		for (int i = 0; i < bulletList.size(); i++)
		{
			bulletList.get(i).moveBullet(BULLET_SPEED);
		}

		for (int i = 0; i < bulletList.size(); i++)
		{
			if (bulletList.get(i).getY() < 0)
			{
				bulletList.remove(bulletList.get(i));
			}
		}
	}

	public ArrayList<Sprite> getItems()
	{
		ArrayList<Sprite> allItemList = new ArrayList<Sprite>();

		allItemList.addAll(bulletList);
		allItemList.addAll(alienList);
		allItemList.add(playerShip);

		return allItemList;
	}

	public int status()
	{
		if (alienList.size() == 0)
		{
			status = Display.WIN;
		}

		if (alienReachBottom())
		{
			status = Display.LOSE;
		}

		return status;
	}

	private boolean alienReachBottom()
	{
		boolean reachBottom = false;

		for (int i = 0; i < alienList.size(); i++)
		{
			if (alienList.get(i).getY() + (4 * ALIEN_DOWN_SPEED) > height)
			{
				reachBottom = true;
			}
		}

		return reachBottom;
	}

	public void move(int direction)
	{
		double x = playerShip.getX();

		if (direction == Display.MOVE_LEFT)
		{
			x -= SHIP_SPEED;
		}
		else
		{
			x += SHIP_SPEED;
		}

		if (x < 0)
		{
			x = 0;
		}

		if (x > width - playerShip.getColorGrid()[0].length)
		{
			x = width - playerShip.getColorGrid()[0].length;
		}

		playerShip.setX(x);
	}

	public void shoot()
	{
		double bulletX = 0;
		double bulletY = 0;
		Bullet bullet = null;

		if (bulletList.size() < 2)
		{
			bulletX = playerShip.getX();
			bulletY = playerShip.getY() - (playerShip.getColorGrid().length);

			bullet = new Bullet(bulletX, bulletY);
			bulletList.add(bullet);
		}
	}
}
