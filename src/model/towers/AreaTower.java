package model.towers;

import model.projectiles.AreaBullet;
import model.projectiles.Projectile;
import util.ResourceManager;

public class AreaTower extends Tower {
	
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private static final int RADIUS = 150;
	private static final int RATE = 4;
	private static final int COST = 200;

	public AreaTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		image = ResourceManager.getImage("AreaTower");
	}
	
	public AreaTower() {
		this(0, 0, WIDTH, HEIGHT, RADIUS, RATE, COST);
		
	}
	
	@Override
	public Projectile getProjectile() {
		return new AreaBullet(x + WIDTH / 2, y + HEIGHT / 2);
	}
}
