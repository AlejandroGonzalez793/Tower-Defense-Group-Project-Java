package model.enemies;

import util.ResourceManager;

public class Pterosaur extends Enemy {
	private static final int HEALTH = 70;
	private static final int SPEED = 15;
	private static final int POWER = 9;
	private static final int GOLD = 40;
	
	public Pterosaur(int x, int y, int width, int height, int health, int speed, int power, int gold) {
		super(x, y, width, height, health, speed, power, gold);
		image = ResourceManager.getImage("Pterosaur");
	}
	
	public Pterosaur(int x, int y, int width, int height, int speed, int power) {
		this(x, y, width, height, HEALTH, speed, POWER, GOLD);
	}
	
	public Pterosaur(int x, int y) {
		this(x, y, 50, 50, HEALTH, SPEED, POWER, GOLD);
	}
}
