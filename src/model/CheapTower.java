package model;

public class CheapTower extends Tower
{
	public CheapTower(int x, int y, int width, int height, int radius, int cost, int power, double rate) {
		super(x, y, width, height, radius, cost, power, rate);
	}
	
	public CheapTower(int x, int y, int width, int height, int radius, int cost) {
		this(x, y, width, height, radius, cost, DEFAULT_POWER, DEFAULT_RATE);
	}// end CheapTower constructor

}// end CheapTower
