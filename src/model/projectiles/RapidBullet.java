package model.projectiles;

import util.ResourceManager;

public class RapidBullet extends Projectile {
	private static final int WIDTH = 10;
	private static final int HEIGHT = 10;
	private static final int SPEED = 10;
	private static final int POWER = 3;
	private static final int RADIUS = 100;
    
	public RapidBullet(int x, int y, int width, int height, int speed, int power, int radius) {
		super(x, y, width, height, speed, power, radius);
		image = ResourceManager.getImage("RapidBullet");
	}
	
	public RapidBullet(int x, int y) {
		this(x, y, WIDTH, HEIGHT, SPEED, POWER, RADIUS);
	}
}
