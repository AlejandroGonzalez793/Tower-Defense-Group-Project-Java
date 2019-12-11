package model.projectiles;

import model.Entity;
import util.ResourceManager;

/**
 * A minimal template for the different Projectile that can be drawn and used
 * in the game.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu)
 */
public class Projectile extends Entity {
	public static final int DEFAULT_POWER = 3;
	public static final int DEFAULT_WIDTH = 10;
	public static final int DEFAULT_HEIGHT = 10;
	public static final int DEFAULT_SPEED = 10;
	public static final int DEFAULT_RADIUS = 100;
	
	private int power;
	private int radius;
	private int distance;
	private int speed;
	
	/**
	 * Custom constructor for a projectile.
	 * 
	 * @param x the x coordinate were the projectile will spawn
	 * @param y the y coordinate were the projectile will spawn
	 * @param width the width of the projectile size
	 * @param height the height of the projectile size
	 * @param speed the speed of the projectile
	 * @param power how much damage the projectile will do to the enemies
	 * @param radius the radius of the projectile
	 */
	public Projectile(int x, int y, int width, int height, int speed, int power, int radius) {
		super(x, y, width, height, speed);
		this.power = power;
		this.radius = radius;
		this.speed = speed;
		image = ResourceManager.getImage("Projectile");
	}
	
	/**
	 * Constructor for a projectile with specified width and height.
	 * 
	 * @param x the x coordinate were the projectile will spawn
	 * @param y the y coordinate were the projectile will spawn
	 * @param width the width of the projectile size
	 * @param height the height of the projectile size
	 */
	public Projectile(int x, int y, int width, int height) {
		this(x, y, width, height, DEFAULT_SPEED, DEFAULT_POWER, DEFAULT_RADIUS);
	}
	
	/**
	 * Base constructor for an projectile.
	 * 
	 * @param x the x coordinate were the projectile will spawn
	 * @param y the y coordinate were the projectile will spawn
	 */
	public Projectile(int x, int y) {
		this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_SPEED, DEFAULT_POWER, DEFAULT_RADIUS);
	}

	/**
	 * Get the projectile's power level.
	 * 
	 * @return the projectile power number
	 */
	public int getPower() {
		return power;
	}

	/**
	 * Get the projectile's radius.
	 * 
	 * @return the projectile radius value
	 */
	public int getRadius() {
		return radius;
	}
	
	/**
	 * Update the projectile's position.
	 */
	public void update() {
		x += dx;
		y += dy;
	}
	
	/**
	 * Get the distance of the projectile.
	 * 
	 * @return the distance value of the projectile.
	 */
	public int getDistance() {
		return distance;
	}
	
	/**
	 * Set the distance of how far the projectile will travel based off 
	 * off the enemy position.
	 */
	public void setDistance() {
		distance += Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}
}
