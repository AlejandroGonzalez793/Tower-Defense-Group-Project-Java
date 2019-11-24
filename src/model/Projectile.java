package model;

public class Projectile extends Entity {
	public static final double DEFAULT_SPEED = 1.0;
	public static final int DEFAULT_POWER = 1;
	
	private double speed;
	private int power;
	
	public Projectile(int x, int y, int radius, double speed, int power) {
		super(x, y, radius * 2, radius * 2);
		this.speed = speed;
		this.power = power;
	}
	
	public Projectile(int x, int y, int radius) {
		this(x, y, radius, DEFAULT_SPEED, DEFAULT_POWER);
	}

	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public int getPower() { 
		return power;
	}
	
	public void setPower(int power) {
		this.power = power;
	}
}
