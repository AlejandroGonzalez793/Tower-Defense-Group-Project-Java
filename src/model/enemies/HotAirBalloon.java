package model.enemies;

import util.ResourceManager;

public class HotAirBalloon extends Enemy {
	private static final int HEALTH = 150;
	private static final int SPEED = 3;
	private static final int POWER = 10;
	private static final int GOLD = 15;
	
	public HotAirBalloon(int x, int y, int width, int height, int health, int speed, int power, int gold) {
		super(x, y, width, height, health, speed, power, gold);
		image = ResourceManager.getImage("HotAirBalloon");
	}
	
	public HotAirBalloon(int x, int y) {
		this(x, y, 50, 50, HEALTH, SPEED, POWER, GOLD);
	}

}
