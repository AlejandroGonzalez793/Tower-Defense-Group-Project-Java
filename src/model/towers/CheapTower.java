package model.towers;

import model.projectiles.Projectile;
import model.projectiles.WeakBullet;
import util.ResourceManager;

/**
 * The CheapTower class holds the CheapTower object. Low cost tower, but 
 * has very low damage.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu)
 */
public class CheapTower extends Tower {
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private static final int RADIUS = 100;
	private static final int RATE = 10;
	private static final int COST = 25;
	
	/**
	 * Custom constructor for the CheapTower.
	 * 
	 * @param x the x coordinate were the tower will be placed
	 * @param y the y coordinate were the tower will be placed
	 * @param width the width of the tower
	 * @param height the height of the tower
	 * @param radius the enemy detection radius of the tower
	 * @param rate the fire rate of the tower
	 * @param cost how much the tower cost to buy
	 */
	public CheapTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		image = ResourceManager.getImage("CheapTower");
	}
	
	/**
	 * Base constructor for the CheapTower.
	 */
	public CheapTower() {
		this(0, 0, WIDTH, HEIGHT, RADIUS, RATE, COST);
	}
	
	/**
	 * Create a new WeakBullet object.
	 * 
	 * @return a new WeakBullet object
	 */
	@Override
	public Projectile getProjectile() {
		return new WeakBullet(x + WIDTH / 2, y + HEIGHT / 2);
	}
}
