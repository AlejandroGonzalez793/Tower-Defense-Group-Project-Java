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
	public static final int DEFAULT_RATE = 1;
	public static final int DEFAULT_RADIUS = 50;
	public static final int DEFAULT_WIDTH = 50;
	public static final int DEFAULT_HEIGHT = 50;
	public static final int DEFAULT_COST = 50;
	
	private int radius;
	private int rate;
	private int cost;
	
	public Tower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height);
		this.radius = radius;
		this.rate = rate;
		this.cost = cost;
		image = ResourceManager.getImage("Default");
	}
	
	public Tower(int x, int y, int width, int height) {
		this(x, y, width, height, DEFAULT_RADIUS, DEFAULT_RATE, DEFAULT_COST);
	}
	
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
	
	public int getRadius() {
		return radius;
	}
	
	public int getRate() {
		return rate;
	}
	
	public int getCost() {
		return cost;
	}
	
	public Projectile getProjectile() {
		return new Projectile(x, y);
	}
}
