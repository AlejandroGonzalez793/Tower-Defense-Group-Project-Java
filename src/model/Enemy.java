package model;

/**
 * A minimal template for the different Enemies that can be used and drawn 
 * in the game.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu) 
 */
public class Enemy extends Entity {	
	public static final int DEFAULT_SPEED = 1;
	public static final int DEFAULT_HEALTH = 5;
	
	private double speed;
	private int health;
	
	public Enemy(int x, int y, int width, int height, int speed, int health) {
		super(x, y, width, height);
		this.speed = speed;
		this.health = health;
	}
	
	public Enemy(int x, int y, int width, int height) {
		this(x, y, width, height, DEFAULT_SPEED, DEFAULT_HEALTH);
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double getSpeed() {
		return speed;
	}
}
