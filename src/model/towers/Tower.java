package model.towers;

import model.Entity;
import model.projectiles.Projectile;
import util.ResourceManager;

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
	public static final int DEFAULT_RATE = 5;
	public static final int DEFAULT_WIDTH = 50;
	public static final int DEFAULT_HEIGHT = 50;
	public static final int DEFAULT_COST = 50;
	public static final int DEFAULT_RADIUS = 100;
	
	private int radius;
	private int rate;
	private int cost;
	
	/**
	 * Custom constructor for a Tower object.
	 * 
	 * @param x the x coordinate were the tower will be placed
	 * @param y the y coordinate were the tower will be placed
	 * @param width the width of the tower
	 * @param height the height of the tower
	 * @param radius the enemy detection radius of the tower
	 * @param rate the fire rate of the tower
	 * @param cost how much the tower cost to buy
	 */
	public Tower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height);
		this.radius = radius;
		this.rate = rate;
		this.cost = cost;
		image = ResourceManager.getImage("Default");
	}
	
	/**
	 * Custom constructor for a Tower object with specified height and width.
	 * 
	 * @param x the x coordinate were the tower will be placed
	 * @param y the y coordinate were the tower will be placed
	 * @param width the width of the tower
	 * @param height the height of the tower
	 */
	public Tower(int x, int y, int width, int height) {
		this(x, y, width, height, DEFAULT_RADIUS, DEFAULT_RATE, DEFAULT_COST);
	}
	
	/**
	 * Base constructor for a Tower object.
	 */
	public Tower() {
		this(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_RADIUS, DEFAULT_RATE, DEFAULT_COST);
	}
	
	/**
	 * Determines if a projectile should be generated at a given tick
	 * value by comparing it to the rate of the tower. 
	 * 
	 * @param tick the tick to check
	 * @return true if a projectile should be generated, false otherwise
	 */
	public boolean generateProjectile(int tick) {
		return tick % rate == 0;
	}
	
	/**
	 * Get the detection radius of the tower.
	 * 
	 * @return the detection radius value of the tower
	 */
	public int getRadius() {
		return radius;
	}
	
	/**
	 * Get the fire rate of the tower.
	 * 
	 * @return the fire rate value
	 */
	public int getRate() {
		return rate;
	}
	
	/**
	 * Get the tower price.
	 * 
	 * @return the cost of the tower
	 */
	public int getCost() {
		return cost;
	}
	
	/**
	 * Create a new projectile object.
	 * 
	 * @return a new projectile object
	 */
	public Projectile getProjectile() {
		return new Projectile(x + DEFAULT_WIDTH / 2, y + DEFAULT_HEIGHT / 2);
	}
}
