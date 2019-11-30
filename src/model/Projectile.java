package model;

public class Projectile extends Entity {
	public static final int DEFAULT_POWER = 1;
	public static final int DEFAULT_WIDTH = 10;
	public static final int DEFAULT_HEIGHT = 10;
	
	private int power;
	
	public Projectile(int x, int y, int width, int height, int dx, int dy, int power) {
		super(x, y, width, height, dx, dy);
		this.power = power;
	}
	
	public Projectile(int x, int y, int width, int height) {
		this(x, y, width, height, 10, 10, DEFAULT_POWER);
	}
	
	public Projectile(int x, int y) {
		// TODO: start dx and dy as 0 and then set them later
		this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, 10, 10, DEFAULT_POWER);
	}

	public int getPower() {
		return power;
	}
}
