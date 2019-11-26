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
	public static final int DEFAULT_COST = 100;
	public static final int DEFAULT_RADIUS = 50;
	public static final String DEFAULT_IMAGE = "default_tower.png";
	
	private String imageName;
	private int radius;
	private int cost;
	private int power;
	private double rate; // rate at which the tower fires
	
	public Tower(int x, int y, int width, int height, int radius, int cost, int power, double rate, String imageName) {
		super(x, y, width, height);
		this.radius = radius;
		this.cost = cost;
		this.power = power;
		this.rate = rate;
		this.imageName = imageName;
	}
	
	public Tower(int x, int y, int width, int height) {
		this(x, y, width, height, DEFAULT_RADIUS, DEFAULT_COST, DEFAULT_POWER, DEFAULT_RATE, DEFAULT_IMAGE);
	}
	
	public Tower() {
		this(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_RADIUS, DEFAULT_COST, DEFAULT_POWER, DEFAULT_RATE, DEFAULT_IMAGE);
	}
	
	public String getImageName() {
		return imageName;
	}
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
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
