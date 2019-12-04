package model.towers;

import model.projectiles.OneShotBullet;
import model.projectiles.Projectile;
import util.ResourceManager;

public class OneShotTower extends Tower {
	private static final int WIDTH = 75;
	private static final int HEIGHT = 75;
	private static final int RADIUS = 150;
	private static final int RATE = 10;
	private static final int COST = 500;
	
	public OneShotTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		image = ResourceManager.getImage("OneShotTower");
	}
	
	public OneShotTower(int x, int y, int width, int height) {
		this(x, y, width, height, 150, 10, 1000);
	}
	
	public OneShotTower() {
		this(0, 0, WIDTH, HEIGHT, RADIUS, RATE, COST);
	}
	
	@Override
	public Projectile getProjectile() {
		return new OneShotBullet(x, y);
	}
}
