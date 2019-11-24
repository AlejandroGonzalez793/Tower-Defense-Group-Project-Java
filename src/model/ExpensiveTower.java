package model;

public class ExpensiveTower extends Tower {
	public static final int COST = 500;
		
	public ExpensiveTower(int x, int y, int width, int height, int radius, int cost, int power, double rate) {
		super(x, y, width, height, radius, cost, power, rate);
	}
	
	public ExpensiveTower(int x, int y, int width, int height, int radius) {
		this(x, y, width, height, radius, COST, DEFAULT_POWER, DEFAULT_RATE);
	}
}
