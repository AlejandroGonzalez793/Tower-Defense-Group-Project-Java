package model.towers;

import model.projectiles.Projectile;
import model.projectiles.WeakBullet;
import util.ResourceManager;

public class CheapTower extends Tower {
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private static final int RADIUS = 100;
	private static final int RATE = 1;
	private static final int COST = 25;
	
	public CheapTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		image = ResourceManager.getImage("CheapTower");

	}
	
	public CheapTower(int x, int y, int width, int height) {
		this(x, y, width, height, 150, 10, 1000);
	}
	
	public CheapTower() {
		this(0, 0, WIDTH, HEIGHT, RADIUS, RATE, COST);
	}
	
	@Override
	public Projectile getProjectile() {
		return new WeakBullet(x, y);
	}
}
