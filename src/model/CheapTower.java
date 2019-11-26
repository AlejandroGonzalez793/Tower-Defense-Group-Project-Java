package model;

public class CheapTower extends Tower {
	private static final int COST = 50;
	private static final int POWER = 1;
	private static final int RATE = 1;
	private static final int RADIUS = 50;
	private static final String IMAGE_NAME = "cheap_tower.png";
	
	public CheapTower(int x, int y, int width, int height, int radius, int cost, 
			int power, double rate, String imageName) {
		super(x, y, width, height, radius, cost, power, rate, imageName);
	}
	
	public CheapTower(int x, int y, int width, int height, int radius) {
		this(x, y, width, height, radius, COST, DEFAULT_POWER, DEFAULT_RATE, IMAGE_NAME);
	}
	
	public CheapTower() {
		this(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT, RADIUS, COST, POWER, RATE, IMAGE_NAME);
	}
}
