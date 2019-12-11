package model.projectiles;

import util.ResourceManager;

/**
 * The WeakBullet class holds the projectile WeakBullet. The weakest bullet in the game.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu)
 */
public class WeakBullet extends Projectile {
	private static final int WIDTH = 16;
	private static final int HEIGHT = 10;
	private static final int SPEED = 10;
	private static final int POWER = 1;
	private static final int RADIUS = 100;
 
	/**
	 * Custom constructor for the WeakBullet.
	 * 
	 * @param x the x coordinate were the projectile will spawn
	 * @param y the y coordinate were the projectile will spawn
	 * @param width the width of the projectile size
	 * @param height the height of the projectile size
	 * @param speed the speed of the projectile
	 * @param power how much damage the projectile will do to the enemies
	 * @param radius the radius of the projectile
	 */
	public WeakBullet(int x, int y, int width, int height, int speed, int power, int radius) {
		super(x, y, width, height, speed, power, radius);
		image = ResourceManager.getImage("WeakBullet");
	}
	
	/**
	 * Regular constructor for the WeakBullet with its pre-set values.
	 * 
	 * @param x the x coordinate were the projectile will spawn
	 * @param y the y coordinate were the projectile will spawn
	 */
	public WeakBullet(int x, int y) {
		this(x, y, WIDTH, HEIGHT, SPEED, POWER, RADIUS);
	}

}
