package model.towers;

import model.projectiles.OneShotBullet;
import model.projectiles.Projectile;
import util.ResourceManager;

public class OneShotTower extends Tower {
	private static final int WIDTH = 75;
	private static final int HEIGHT = 75;
	private static final int RADIUS = 150;
	private static final int RATE = 25;
	private static final int COST = 500;
	
	public OneShotTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		image = ResourceManager.getImage("OneShotTower");
	}
	
	public OneShotTower() {
		this(0, 0, WIDTH, HEIGHT, RADIUS, RATE, COST);
	}
	
	@Override
	public Projectile getProjectile() {
		return new OneShotBullet(x + WIDTH / 2, y + HEIGHT / 2);
	}
}
