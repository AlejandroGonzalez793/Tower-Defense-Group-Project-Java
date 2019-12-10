package model.towers;

import model.projectiles.Projectile;
import model.projectiles.RapidBullet;
import util.ResourceManager;

/**
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

	public RapidTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		image = ResourceManager.getImage("RapidTower");
	}
	
	public RapidTower() {
		this(0, 0, WIDTH, HEIGHT, RADIUS, RATE, COST);
	}
	
	@Override
	public Projectile getProjectile() {
		return new RapidBullet(x + WIDTH / 2, y + HEIGHT / 2);
	}
}
