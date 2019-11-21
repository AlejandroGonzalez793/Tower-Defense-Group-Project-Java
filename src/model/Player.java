package model;

public class Player {
	public static final int STARTING_MONEY = 1000;
	public static final int STARTING_HEALTH = 100;
	
	private int health;
	private int money;
	
	public Player() {
		this.health = STARTING_HEALTH;
		this.money = STARTING_MONEY;
	}
	
	public Player(int money) {
		this.health = STARTING_HEALTH;
		this.money = money;
	}
	
	public Player(int health, int money) {
		this.health = health;
		this.money = money;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
}
