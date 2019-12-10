package model.towers;

import model.projectiles.Projectile;
import model.projectiles.StrongBullet;
import util.ResourceManager;

public class ExpensiveTower extends Tower{
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private static final int RADIUS = 100;
	private static final int RATE = 4;
	private static final int COST = 250;

	public ExpensiveTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		image = ResourceManager.getImage("ExpensiveTower");
	}
	
	public ExpensiveTower() {
		this(0, 0, WIDTH, HEIGHT, RADIUS, RATE, COST);
	}
	
	@Override
	public Projectile getProjectile() {
		return new StrongBullet(x, y);
	}
}
