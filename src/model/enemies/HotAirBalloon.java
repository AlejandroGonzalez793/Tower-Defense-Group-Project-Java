package model.enemies;

import util.ResourceManager;

/**
 * The Balloon class holds the enemy HotAirBalloon. Upgraded versions of the regular
 * Balloon enemy.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu)
 */
public class HotAirBalloon extends Enemy {
	private static final int HEALTH = 150;
	private static final int SPEED = 3;
	private static final int POWER = 10;
	private static final int GOLD = 15;
	
	/**
	 * Custom constructor for the HotAirBalloon enemy.
	 * 
	 * @param x the x coordinate were the enemy will spawn
	 * @param y the y coordinate were the enemy will spawn
	 * @param width the width of the enemy size
	 * @param height the height of the enemy size
	 * @param health the health of the enemy
	 * @param speed the speed of the enemy
	 * @param power how much damage the enemy will do to the player
	 * @param gold the gold gained from killing the enemy
	 */
	public HotAirBalloon(int x, int y, int width, int height, int health, int speed, int power, int gold) {
		super(x, y, width, height, health, speed, power, gold);
		image = ResourceManager.getImage("HotAirBalloon");
	}
	
	/**
	 * Regular constructor for the HotAirBalloon enemy with its pre-set values.
	 * 
	 * @param x the x coordinate were the enemy will spawn
	 * @param y y the y coordinate were the enemy will spawn
	 */
	public HotAirBalloon(int x, int y) {
		this(x, y, 50, 50, HEALTH, SPEED, POWER, GOLD);
	}

}
