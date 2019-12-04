package model.projectiles;

import model.Entity;
import util.ResourceManager;

public class Projectile extends Entity {
	public static final int DEFAULT_POWER = 1;
	public static final int DEFAULT_WIDTH = 10;
	public static final int DEFAULT_HEIGHT = 10;
	public static final int DEFAULT_SPEED = 10;
	
	private int power;
	
	public Projectile(int x, int y, int width, int height, int speed, int power) {
		super(x, y, width, height, speed);
		this.power = power;
		image = ResourceManager.getImage("Projectile");

	}
	
	public Projectile(int x, int y, int width, int height) {
		this(x, y, width, height, DEFAULT_SPEED, DEFAULT_POWER);
	}
	
	public Projectile(int x, int y) {
		// TODO: start dx and dy as 0 and then set them later
		this(x, y, DEFAULT_WIDTH/2, DEFAULT_HEIGHT/2, DEFAULT_SPEED, DEFAULT_POWER);
	}

	public int getPower() {
		return power;
	}
}
