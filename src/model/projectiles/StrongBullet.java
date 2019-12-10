package model.projectiles;

import util.ResourceManager;

public class StrongBullet extends Projectile {
	private static final int WIDTH = 28;
	private static final int HEIGHT = 28;
	private static final int SPEED = 10;
	private static final int POWER = 8;
	private static final int RADIUS = 100;
 
	public StrongBullet(int x, int y, int width, int height, int speed, int power, int radius) {
		super(x, y, width, height, speed, power, radius);
		image = ResourceManager.getImage("StrongBullet");
	}
	
	public StrongBullet(int x, int y) {
		this(x, y, WIDTH, HEIGHT, SPEED, POWER, RADIUS);
	}

}
