package model.projectiles;

import util.ResourceManager;

public class MultiBullet extends Projectile {
	private static final int WIDTH = 16;
	private static final int HEIGHT = 16;
	private static final int SPEED = 10;
	private static final int POWER = 6;
	private static final int RADIUS = 200;
    
	public MultiBullet(int x, int y, int width, int height, int speed, int power, int radius) {
		super(x, y, width, height, speed, power, radius);
		image = ResourceManager.getImage("MultiBullet");
	}
	
	public MultiBullet(int x, int y, int width, int height) {
		this(x, y, width, height, SPEED, POWER, RADIUS);
	}
	
	public MultiBullet(int x, int y) {
		this(x, y, WIDTH, HEIGHT, SPEED, POWER, RADIUS);
	}
}
