package model.towers;

import model.projectiles.PiercingBullet;
import model.projectiles.Projectile;
import util.ResourceManager;

/**
 * The PiercingTower class holds the PiercingTower object. An all powerful
 * Thicc Yoshi commands the armor piercing bullets for heavy damage.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu)
 */
public class PiercingTower extends Tower{
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private static final int RADIUS = 100;
	private static final int RATE = 3;
	private static final int COST = 350;

	/**
	 * Custom constructor for the PiercingTower.
	 * 
	 * @param x the x coordinate were the tower will be placed
	 * @param y the y coordinate were the tower will be placed
	 * @param width the width of the tower
	 * @param height the height of the tower
	 * @param radius the enemy detection radius of the tower
	 * @param rate the fire rate of the tower
	 * @param cost how much the tower cost to buy
	 */
	public PiercingTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		image = ResourceManager.getImage("PiercingTower");
	}
	
	/**
	 * Base constructor for the PiercingTower.
	 */
	public PiercingTower() {
		this(0, 0, WIDTH, HEIGHT, RADIUS, RATE, COST);
	}
	
	/**
	 * Create a new PiercingBullet object.
	 * 
	 * @return a new PiercingBullet object
	 */
	@Override
	public Projectile getProjectile() {
		return new PiercingBullet(x + WIDTH / 2, y + HEIGHT / 2);
	}
}
