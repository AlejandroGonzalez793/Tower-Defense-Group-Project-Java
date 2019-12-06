package model.enemies;

import util.ResourceManager;

public class Drifblim extends Enemy {
	private static final int HEALTH = 200;
	private static final int SPEED = 4;
	private static final int POWER = 20;
	private static final int GOLD = 90;
	
	public Drifblim(int x, int y, int width, int height, int health, int speed, int power, int gold) {
		super(x, y, width, height, health, speed, power, gold);
		image = ResourceManager.getImage("Drifblim");
	}
	
	public Drifblim(int x, int y, int width, int height, int speed, int power, int gold) {
		this(x, y, width, height, HEALTH, speed, POWER, GOLD);
	}
	
	public Drifblim(int x, int y) {
		this(x, y, 50, 50, HEALTH, SPEED, POWER, GOLD);
	}

}
