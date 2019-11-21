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
	
	private double speed;
	
	public Enemy(int x, int y, int width, int height, int speed) {
		super(x, y, width, height);
		this.speed = speed;
	}
	
	public Enemy(int x, int y, int width, int height) {
		this(x, y, width, height, DEFAULT_SPEED);
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double getSpeed() {
		return speed;
	}
}
