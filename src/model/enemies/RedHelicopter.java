package model.enemies;

import util.ResourceManager;

/**
 * The Balloon class holds the enemy RedHelicopter. A hard hitting enemy that likes
 * to come in high numbers.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu)
 */
public class RedHelicopter extends Enemy {
	private static final int HEALTH = 100;
	private static final int SPEED = 7;
	private static final int POWER = 15;
	private static final int GOLD = 30;

	/**
	 * Custom constructor for the RedHelicopter enemy.
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
	public RedHelicopter(int x, int y, int width, int height, int health, int speed, int power, int gold) {
		super(x, y, width, height, health, speed, power, gold);
		image = ResourceManager.getImage("RedHelicopter");
	}
	
	/**
	 * Regular constructor for the RedHelicopter enemy with its pre-set values.
	 * 
	 * @param x the x coordinate were the enemy will spawn
	 * @param y y the y coordinate were the enemy will spawn
	 */
	public RedHelicopter(int x, int y) {
		this(x, y, 50, 50, HEALTH, SPEED, POWER, GOLD);
	}
}
