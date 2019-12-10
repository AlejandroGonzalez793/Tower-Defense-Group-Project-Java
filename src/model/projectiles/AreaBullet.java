package model.projectiles;

import util.ResourceManager;

public class AreaBullet extends Projectile {
	private static final int WIDTH = 28;
	private static final int HEIGHT = 28;
	private static final int SPEED = 100;
	private static final int POWER = 5;
	private static final int RADIUS = 150;
    
	public AreaBullet(int x, int y, int width, int height, int speed, int power, int radius) {
		super(x, y, width, height, speed, power, radius);
		image = ResourceManager.getImage("AreaBullet");
	}
	
	public AreaBullet(int x, int y) {
		this(x, y, WIDTH, HEIGHT, SPEED, POWER, RADIUS);
	}
}
