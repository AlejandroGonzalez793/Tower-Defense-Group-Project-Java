package model;

public class Projectile extends Entity {
	public static final double DEFAULT_SPEED = 1.0;
	
	private double speed;
	
	public Projectile(int x, int y, int radius, double speed) {
		super(x, y, radius * 2, radius * 2);
		this.speed = speed;
	}
	
	public Projectile(int x, int y, int radius) {
		this(x, y, radius, DEFAULT_SPEED);
	}

	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
}
