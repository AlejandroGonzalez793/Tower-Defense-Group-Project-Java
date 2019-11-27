package model;

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
	private int health;
	
	public Enemy(int x, int y, int width, int height, int dx, int dy, int health) {
		super(x, y, width, height, dx, dy);
		this.health = health;
	}
	
	public Enemy(int x, int y, int width, int height) {
		this(x, y, width, height, 5, 5, DEFAULT_HEALTH);
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
}
