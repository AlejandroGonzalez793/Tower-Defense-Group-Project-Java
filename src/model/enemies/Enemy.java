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
	
	public Enemy(int x, int y, int width, int height, int health, int speed, int power, int gold) {
		super(x, y, width, height, speed);
		this.health = health;
		this.power = power;
		this.gold = gold;
		this.currNode = new Node(new Rectangle(x, y, width, height));
	}
	
	public Enemy(int x, int y, int width, int height, int speed, int power, int gold) {
		this(x, y, width, height, speed, DEFAULT_HEALTH, DEFAULT_POWER, DEFAULT_GOLD);
	}
	
	public Enemy(int x, int y, int width, int height) {
		this(x, y, width, height, DEFAULT_SPEED, DEFAULT_HEALTH, DEFAULT_POWER, DEFAULT_GOLD);
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}
	
	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public void setNode(Node node) {
		this.currNode = node;
		setDirection();
	}
	
	public void incrementNode() {
		this.currNode = currNode.getNext();
		setDirection();
	}
	
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
	
	public Node getNode() {
		return currNode;
	}
	
}
