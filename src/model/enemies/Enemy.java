package model.enemies;

import javafx.scene.shape.Rectangle;
import model.Entity;
import model.Node;

/**
 * A minimal template for the different Enemies that can be used and drawn 
 * in the game.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu) 
 */
public class Enemy extends Entity {	
	public static final int DEFAULT_HEALTH = 5;
	public static final int DEFAULT_SPEED = 5;
	public static final int DEFAULT_POWER = 1;
	public static final int DEFAULT_GOLD = 10;
	private int health;
	private int power;
	private int gold;
	private Node currNode;
	
	/**
	 * Custom constructor for an enemy.
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
	public Enemy(int x, int y, int width, int height, int health, int speed, int power, int gold) {
		super(x, y, width, height, speed);
		this.health = health;
		this.power = power;
		this.gold = gold;
		this.currNode = new Node(new Rectangle(x, y, width, height));
	}
	
	/**
	 * Base constructor for an enemy.
	 * 
	 * @param x the x coordinate were the enemy will spawn
	 * @param y the y coordinate were the enemy will spawn
	 * @param width the width of the enemy size
	 * @param height the height of the enemy size
	 */
	public Enemy(int x, int y, int width, int height) {
		this(x, y, width, height, DEFAULT_SPEED, DEFAULT_HEALTH, DEFAULT_POWER, DEFAULT_GOLD);
	}
	
	/**
	 * Get enemy's current health.
	 * 
	 * @return The enemy's current health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Set enemy's current health.
	 * 
	 * @param health the amount of health the enemy has.
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * Get the enemy's power level.
	 * 
	 * @return the enemy power number
	 */
	public int getPower() {
		return power;
	}

	/**
	 * Set the enemy's power level.
	 * 
	 * @param power the amount of power the enemy will have
	 */
	public void setPower(int power) {
		this.power = power;
	}
	
	/**
	 * Get the amount of gold the enemy has.
	 * 
	 * @return the gold that the enemy has
	 */
	public int getGold() {
		return gold;
	}

	/**
	 * Set the amount of gold the enemy has.
	 * 
	 * @param gold the amount of gold the enemy will have
	 */
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	/**
	 * Assign a Node to the enemy
	 * 
	 * @param node a node object
	 */
	public void setNode(Node node) {
		this.currNode = node;
		setDirection();
	}
	
	/**
	 * Increment the enemy's node.
	 */
	public void incrementNode() {
		this.currNode = currNode.getNext();
		setDirection();
	}
	
	/**
	 * Set the direction the enemy will be facing in order to follow 
	 * the correct path by checking the enemy node and the path node.
	 */
	private void setDirection() {
		if (currNode != null && currNode.getNext() != null) {
			Node next = currNode.getNext();
			Rectangle currRect = currNode.getRectangle();
			Rectangle nextRect = next.getRectangle();
			if (nextRect.getX() > currRect.getX()) {
				dx = speed;
			} else if (currRect.getX() == nextRect.getX()) {
				dx = 0;
			} else {
				dx = -speed;
			}
			
			if (nextRect.getY() > currRect.getY()) {
				dy = speed;
			} else if (currRect.getY() == nextRect.getY()) {
				dy = 0;
			} else {
				dy = -speed;
			}
		}
	}
	
	/**
	 * Get the current enemy Node.
	 * 
	 * @return the enemy's node
	 */
	public Node getNode() {
		return currNode;
	}
	
}
