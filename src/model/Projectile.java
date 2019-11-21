package model;

import javafx.scene.image.Image;

public class Projectile extends Entity {
	public static final double DEFAULT_SPEED = 1.0;
	
	private double speed;
	
	public Projectile(Image image, int x, int y, int radius, double speed) {
		super(image, x, y, radius * 2, radius * 2);
		this.speed = speed;
	}
	
	public Projectile(Image image, int x, int y, int radius) {
		this(image, x, y, radius, DEFAULT_SPEED);
	}

	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
}
