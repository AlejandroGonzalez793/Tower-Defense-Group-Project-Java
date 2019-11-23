package model;

public class CheapTower extends Tower
{
	public static final int COST = 50;
	
	public CheapTower(int x, int y, int width, int height, int radius, int cost, int power, double rate) {
		super(x, y, width, height, radius, cost, power, rate);
	}
	
	public CheapTower(int x, int y, int width, int height, int radius) {
		this(x, y, width, height, radius, COST, DEFAULT_POWER, DEFAULT_RATE);
	}// end CheapTower constructor

}// end CheapTower
