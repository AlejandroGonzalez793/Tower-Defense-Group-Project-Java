package model.projectiles;

import util.ResourceManager;

public class RapidBullet extends Projectile {
	private static final int WIDTH = 4;
	private static final int HEIGHT = 4;
	private static final int SPEED = 10;
	private static final int POWER = 2;
	private static final int RADIUS = 50;
    
	public RapidBullet(int x, int y, int width, int height, int speed, int power) {
		super(x, y, width, height, speed, power);
		image = ResourceManager.getImage("RapidBullet");
	}
	
	public RapidBullet(int x, int y, int width, int height) {
		this(x, y, width, height, SPEED, POWER);
	}
	
	public RapidBullet(int x, int y) {
		this(x, y, WIDTH, HEIGHT, SPEED, POWER);
	}
	
	public int getRadius() {
		return RADIUS;
	}

}
