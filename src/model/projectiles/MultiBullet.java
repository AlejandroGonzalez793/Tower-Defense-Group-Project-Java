package model.projectiles;

import util.ResourceManager;

/**
 * The MultiBullet class holds the projectile MultiBullet. A balanced bullet shot from the MultiTower.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu)
 */
public class MultiBullet extends Projectile {
	private static final int WIDTH = 16;
	private static final int HEIGHT = 16;
	private static final int SPEED = 10;
	private static final int POWER = 7;
	private static final int RADIUS = 200;
    
	/**
	 * Custom constructor for the MultiBullet.
	 * 
	 * @param x the x coordinate were the projectile will spawn
	 * @param y the y coordinate were the projectile will spawn
	 * @param width the width of the projectile size
	 * @param height the height of the projectile size
	 * @param speed the speed of the projectile
	 * @param power how much damage the projectile will do to the enemies
	 * @param radius the radius of the projectile
	 */
	public MultiBullet(int x, int y, int width, int height, int speed, int power, int radius) {
		super(x, y, width, height, speed, power, radius);
		image = ResourceManager.getImage("MultiBullet");
	}
	
	/**
	 * Regular constructor for the MultiBullet with its pre-set values.
	 * 
	 * @param x the x coordinate were the projectile will spawn
	 * @param y the y coordinate were the projectile will spawn
	 */
	public MultiBullet(int x, int y) {
		this(x, y, WIDTH, HEIGHT, SPEED, POWER, RADIUS);
	}
}
