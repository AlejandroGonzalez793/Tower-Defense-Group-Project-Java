package model.towers;

import model.projectiles.AreaBullet;
import model.projectiles.Projectile;
import util.ResourceManager;

/**
 * The AreaTower class holds the AreaTower object. The AreaTower has projectiles
 * that cover a wide area.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu)
 */
public class AreaTower extends Tower {
	
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private static final int RADIUS = 150;
	private static final int RATE = 4;
	private static final int COST = 200;

	/**
	 * Custom constructor for the AreaTower.
	 * 
	 * @param x the x coordinate were the tower will be placed
	 * @param y the y coordinate were the tower will be placed
	 * @param width the width of the tower
	 * @param height the height of the tower
	 * @param radius the enemy detection radius of the tower
	 * @param rate the fire rate of the tower
	 * @param cost how much the tower cost to buy
	 */
	public AreaTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		image = ResourceManager.getImage("AreaTower");
	}
	
	/**
	 * Base constructor for the AreaTower.
	 */
	public AreaTower() {
		this(0, 0, WIDTH, HEIGHT, RADIUS, RATE, COST);
		
	}
	
	/**
	 * Create a new AreaBullet object.
	 * 
	 * @return a new AreaBullet object
	 */
	@Override
	public Projectile getProjectile() {
		return new AreaBullet(x + WIDTH / 2, y + HEIGHT / 2);
	}
}
