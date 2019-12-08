package model.enemies;

import util.ResourceManager;

public class RedHelicopter extends Enemy {
	private static final int HEALTH = 100;
	private static final int SPEED = 7;
	private static final int POWER = 15;
	private static final int GOLD = 30;

	public RedHelicopter(int x, int y, int width, int height, int health, int speed, int power, int gold) {
		super(x, y, width, height, health, speed, power, gold);
		image = ResourceManager.getImage("RedHelicopter");
	}
	
	public RedHelicopter(int x, int y, int width, int height, int speed, int power) {
		this(x, y, width, height, HEALTH, speed, POWER, GOLD);
	}
	
	public RedHelicopter(int x, int y) {
		this(x, y, 50, 50, HEALTH, SPEED, POWER, GOLD);
	}
}
