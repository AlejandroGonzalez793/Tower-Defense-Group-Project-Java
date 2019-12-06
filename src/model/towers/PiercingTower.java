package model.towers;

import model.projectiles.PiercingBullet;
import model.projectiles.Projectile;
import util.ResourceManager;

public class PiercingTower extends Tower{
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private static final int RADIUS = 100;
	private static final int RATE = 8;
	private static final int COST = 250;

	public PiercingTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		image = ResourceManager.getImage("PiercingTower");
	}
	
	public PiercingTower(int x, int y, int width, int height) {
		this(x, y, width, height, 150, 10, 1000);
	}
	
	public PiercingTower() {
		this(0, 0, WIDTH, HEIGHT, RADIUS, RATE, COST);
	}
	
	@Override
	public Projectile getProjectile() {
		return new PiercingBullet(x, y);
	}
}
