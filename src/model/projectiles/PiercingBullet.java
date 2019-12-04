package model.projectiles;

import util.ResourceManager;

public class PiercingBullet extends Projectile {
	private static final int WIDTH = 16;
	private static final int HEIGHT = 15;
	private static final int SPEED = 10;
	private static final int POWER = 5;
	private static final int RADIUS = 50;
    
	public PiercingBullet(int x, int y, int width, int height, int speed, int power, int radius) {
		super(x, y, width, height, speed, power, radius);
		image = ResourceManager.getImage("PiercingBullet");
	}
	
	public PiercingBullet(int x, int y, int width, int height) {
		this(x, y, width, height, SPEED, POWER, RADIUS);
	}
	
	public PiercingBullet(int x, int y) {
		this(x, y, WIDTH, HEIGHT, SPEED, POWER, RADIUS);
	}
}
