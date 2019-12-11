package model.towers;

import model.projectiles.Projectile;
import model.projectiles.RapidBullet;
import util.ResourceManager;

/**
 * The RapidTower class holds the RapidTower object. A high fire rate tower
 * with low damage bullets.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu)
 */
public class RapidTower extends Tower{
	private static final int WIDTH = 35;
	private static final int HEIGHT = 50;
	private static final int RADIUS = 100;
	private static final int RATE = 1;
	private static final int COST = 100;

	/**
	 * Custom constructor for the RapidTower.
	 * 
	 * @param x the x coordinate were the tower will be placed
	 * @param y the y coordinate were the tower will be placed
	 * @param width the width of the tower
	 * @param height the height of the tower
	 * @param radius the enemy detection radius of the tower
	 * @param rate the fire rate of the tower
	 * @param cost how much the tower cost to buy
	 */
	public RapidTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		image = ResourceManager.getImage("RapidTower");
	}
	
	/**
	 * Base constructor for the RapidTower.
	 */
	public RapidTower() {
		this(0, 0, WIDTH, HEIGHT, RADIUS, RATE, COST);
	}
	
	/**
	 * Create a new RapidBullet object.
	 * 
	 * @return a new RapidBullet object
	 */
	@Override
	public Projectile getProjectile() {
		return new RapidBullet(x + WIDTH / 2, y + HEIGHT / 2);
	}
}
