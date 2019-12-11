package model.towers;

import model.projectiles.OneShotBullet;
import model.projectiles.Projectile;
import util.ResourceManager;

/**
 * The OneShotTower class holds the OneShotTower object. A very expensive
 * tower that fire one shot bullets at a slow rate.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu)
 */
public class OneShotTower extends Tower {
	private static final int WIDTH = 75;
	private static final int HEIGHT = 75;
	private static final int RADIUS = 150;
	private static final int RATE = 25;
	private static final int COST = 500;
	
	/**
	 * Custom constructor for the OneShotTower.
	 * 
	 * @param x the x coordinate were the tower will be placed
	 * @param y the y coordinate were the tower will be placed
	 * @param width the width of the tower
	 * @param height the height of the tower
	 * @param radius the enemy detection radius of the tower
	 * @param rate the fire rate of the tower
	 * @param cost how much the tower cost to buy
	 */
	public OneShotTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		image = ResourceManager.getImage("OneShotTower");
	}
	
	/**
	 * Base constructor for the OneShotTower.
	 */
	public OneShotTower() {
		this(0, 0, WIDTH, HEIGHT, RADIUS, RATE, COST);
	}
	
	/**
	 * Create a new OneShotBullet object.
	 * 
	 * @return a new OneShotBullet object
	 */
	@Override
	public Projectile getProjectile() {
		return new OneShotBullet(x + WIDTH / 2, y + HEIGHT / 2);
	}
}
