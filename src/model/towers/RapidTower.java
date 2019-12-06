package model.towers;

import model.projectiles.Projectile;
import model.projectiles.RapidBullet;
import util.ResourceManager;

public class RapidTower extends Tower{
	private static final int WIDTH = 35;
	private static final int HEIGHT = 50;
	private static final int RADIUS = 100;
	private static final int RATE = 1;
	private static final int COST = 350;

	public RapidTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		image = ResourceManager.getImage("RapidTower");
	}
	
	public RapidTower(int x, int y, int width, int height) {
		this(x, y, width, height, 150, 10, 1000);
	}
	
	public RapidTower() {
		this(0, 0, WIDTH, HEIGHT, RADIUS, RATE, COST);
	}
	
	@Override
	public Projectile getProjectile() {
		return new RapidBullet(x, y);
	}
}
