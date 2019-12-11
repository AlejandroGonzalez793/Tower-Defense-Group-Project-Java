package model;

import java.util.Observable;
import java.util.Observer;

/**
 * A Player object represents the current player playing the game. It keeps track
 * of information necessary to determine game completion and purchasing ability.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu) 
 */
public class Player extends Observable {
	public static final int STARTING_MONEY = 1000;
	public static final int STARTING_HEALTH = 100;
	
	private int health;
	private int money;
		
	/**
	 * Make a new player object with starting heath and money without observer.
	 */
	public Player() {
		this(null, STARTING_HEALTH, STARTING_MONEY);
	}
	
	/**
	 * Make a new player object with starting heath and money with observer.
	 * 
	 * @param observer an observable class
	 */
	public Player(Observer observer) {
		this(observer, STARTING_HEALTH, STARTING_MONEY);
	}
	
	/**
	 * Make a new player object with starting heath and set money with observer.
	 * 
	 * @param observer an observable class
	 * @param money The amount of money to be set
	 */
	public Player(Observer observer, int money) {
		this(observer, STARTING_HEALTH, money);
	}
	
	/**
	 * Make a new player object with set heath and money with observer.
	 * 
	 * @param o an observable class
	 * @param health The amount of health to be set
	 * @param money The amount of money to be set
	 */
	public Player(Observer o, int health, int money) {
		if (o != null) {
			addObserver(o);
		}
		this.health = health;
		this.money = money;
	}
	
	/**
	 * Get players current health
	 * 
	 * @return The players current health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Set player's current health
	 * 
	 * @param health the amount of health the player has.
	 */
	public void setHealth(int health) {
		this.health = health;
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * Get players current money.
	 * 
	 * @return The players current money
	 */
	public int getMoney() {
		return money;
	}
	
	/**
	 * Set player's current money
	 * 
	 * @param money the amount of money the player has.
	 */
	public void setMoney(int money) {
		this.money = money;
		setChanged();
		notifyObservers(this);
	}
}
