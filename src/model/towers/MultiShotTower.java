package model.towers;

import model.projectiles.MultiBullet;
import model.projectiles.Projectile;
import util.ResourceManager;

/**
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
	
	public MultiShotTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		image = ResourceManager.getImage("MultiShotTower");
	}
	
	public MultiShotTower() {
		this(0, 0, WIDTH, HEIGHT, RADIUS, RATE, COST);
	}
	
	@Override
	public Projectile getProjectile() {
		return new MultiBullet(x + WIDTH / 2, y + HEIGHT / 2);
	}
}
