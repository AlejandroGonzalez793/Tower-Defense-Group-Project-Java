package model.towers;

import model.projectiles.PiercingBullet;
import model.projectiles.Projectile;
import util.ResourceManager;

public class PiercingTower extends Tower{
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private static final int RADIUS = 100;
	private static final int RATE = 3;
	private static final int COST = 350;

	public PiercingTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		image = ResourceManager.getImage("PiercingTower");
	}
	
	public PiercingTower() {
		this(0, 0, WIDTH, HEIGHT, RADIUS, RATE, COST);
	}
	
	@Override
	public Projectile getProjectile() {
		return new PiercingBullet(x + WIDTH / 2, y + HEIGHT / 2);
	}
}
