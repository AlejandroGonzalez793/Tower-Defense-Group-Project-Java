package model.projectiles;

import util.ResourceManager;

public class AreaBullet extends Projectile {
	private static final int WIDTH = 28;
	private static final int HEIGHT = 28;
	private static final int SPEED = 10;
	private static final int POWER = 2;
	private static final int RADIUS = 50;
    
	public AreaBullet(int x, int y, int width, int height, int speed, int power) {
		super(x, y, width, height, speed, power);
		image = ResourceManager.getImage("AreaBullet");
	}
	
	public AreaBullet(int x, int y, int width, int height) {
		this(x, y, width, height, SPEED, POWER);
	}
	
	public AreaBullet(int x, int y) {
		this(x, y, WIDTH, HEIGHT, SPEED, POWER);
	}
	
	public int getRadius() {
		return RADIUS;
	}

}
