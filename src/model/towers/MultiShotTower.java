package model.towers;

import model.projectiles.MultiBullet;
import model.projectiles.Projectile;
import util.ResourceManager;

/**
 * The MultiShotTower class holds the MultiShotTower object. A tall tower
 * that has moderate damage.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu)
 */
public class MultiShotTower extends Tower{
	private static final int WIDTH = 80;
	private static final int HEIGHT = 150;
	private static final int RADIUS = 200;
	private static final int RATE = 5;
	private static final int COST = 150;
	
	/**
	 * Custom constructor for the MultiShotTower.
	 * 
	 * @param x the x coordinate were the tower will be placed
	 * @param y the y coordinate were the tower will be placed
	 * @param width the width of the tower
	 * @param height the height of the tower
	 * @param radius the enemy detection radius of the tower
	 * @param rate the fire rate of the tower
	 * @param cost how much the tower cost to buy
	 */
	public MultiShotTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		image = ResourceManager.getImage("MultiShotTower");
	}
	
	/**
	 * Base constructor for the MultiShotTower.
	 */
	public MultiShotTower() {
		this(0, 0, WIDTH, HEIGHT, RADIUS, RATE, COST);
	}
	
	/**
	 * Create a new MultiBullet object.
	 * 
	 * @return a new MultiBullet object
	 */
	@Override
	public Projectile getProjectile() {
		return new MultiBullet(x + WIDTH / 2, y + HEIGHT / 2);
	}
}
