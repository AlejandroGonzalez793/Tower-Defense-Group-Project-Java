package model;

import javafx.scene.image.Image;

public class Projectile extends Entity {
	private int speed;
	
	public static final int DEFAULT_SPEED = 1;
	
	public Projectile(Image image, int x, int y, int radius, int speed) {
		super(image, x, y, radius * 2, radius * 2);
		this.speed = speed;
	}
	
	public Projectile(Image image, int x, int y, int radius) {
		this(image, x, y, radius, DEFAULT_SPEED);
	}

	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
