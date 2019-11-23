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
		
	public Player() {
		this(null, STARTING_HEALTH, STARTING_MONEY);
	}
	
	public Player(Observer observer) {
		this(observer, STARTING_HEALTH, STARTING_MONEY);
	}
	
	public Player(Observer observer, int money) {
		this(observer, STARTING_HEALTH, money);
	}
	
	public Player(Observer o, int health, int money) {
		if (o != null) {
			addObserver(o);
		}
		this.health = health;
		this.money = money;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
		setChanged();
		notifyObservers(this);
	}
	
	public int getMoney() {
		return money;
	}
	
	public void setMoney(int money) {
		this.money = money;
		setChanged();
		notifyObservers(this);
	}
}
