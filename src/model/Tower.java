package model;

/**
 * A minimal template for the different Towers that can be drawn and used
 * in the game.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu) 
 */
public class Tower extends Entity {
	public static final double SELLBACK_FACTOR = 0.9;
	public static final double DEFAULT_RATE = 1.0;
	public static final int DEFAULT_POWER = 1;
	
	private int radius;
	private int cost;
	private int power;
	private double rate; // rate at which the tower fires
	
	public Tower(int x, int y, int width, int height, int radius, int cost, int power, double rate) {
		super(x, y, width, height);
		this.radius = radius;
		this.cost = cost;
		this.power = power;
		this.rate = rate;
	}
	
	public Tower(int x, int y, int width, int height, int radius, int cost) {
		this(x, y, width, height, radius, cost, DEFAULT_POWER, DEFAULT_RATE);
	}
	
	public int getRadius() {
		return radius;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getPower() {
		return power;
	}
	
	public void setPower(int power) {
		this.power = power;
	}
	
	public double getRate() {
		return rate;
	}
	
	public void setRate(double rate) {
		this.rate = rate;
	}
}
