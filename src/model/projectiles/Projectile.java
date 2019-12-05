package model.projectiles;

import model.Entity;
import util.ResourceManager;

public class Projectile extends Entity {
	public static final int DEFAULT_POWER = 1;
	public static final int DEFAULT_WIDTH = 10;
	public static final int DEFAULT_HEIGHT = 10;
	public static final int DEFAULT_SPEED = 10;
	public static final int DEFAULT_RADIUS = 100;
	
	private int power;
	private int radius;
	private int distance;
	private int speed;
	
	public Projectile(int x, int y, int width, int height, int speed, int power, int radius) {
		super(x, y, width, height, speed);
		this.power = power;
		this.radius = radius;
		this.speed = speed;
		image = ResourceManager.getImage("Projectile");
	}
	
	public Projectile(int x, int y, int width, int height) {
		this(x, y, width, height, DEFAULT_SPEED, DEFAULT_POWER, DEFAULT_RADIUS);
	}
	
	public Projectile(int x, int y) {
		this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_SPEED, DEFAULT_POWER, DEFAULT_RADIUS);
	}

	public int getPower() {
		return power;
	}

	public int getRadius() {
		return radius;
	}
	
	public void update() {
		x += dx;
		y += dy;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void setDistance() {
		distance += speed;
	}
}
