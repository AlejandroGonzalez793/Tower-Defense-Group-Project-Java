package model.projectiles;

import util.ResourceManager;

/**
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
    
	public MultiBullet(int x, int y, int width, int height, int speed, int power, int radius) {
		super(x, y, width, height, speed, power, radius);
		image = ResourceManager.getImage("MultiBullet");
	}
	
	public MultiBullet(int x, int y) {
		this(x, y, WIDTH, HEIGHT, SPEED, POWER, RADIUS);
	}
}
