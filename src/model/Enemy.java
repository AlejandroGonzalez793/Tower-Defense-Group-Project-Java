package model;

import javafx.scene.image.Image;

public class Enemy extends Entity {
	private int speed;
	
	public static final int DEFAULT_SPEED = 1;
	
	public Enemy(Image image, int x, int y, int width, int height, int speed) {
		super(image, x, y, width, height);
		this.speed = speed;
	}
	
	public Enemy(Image image, int x, int y, int width, int height) {
		this(image, x, y, width, height, DEFAULT_SPEED);
	}
	
	public int getSpeed() {
		return speed;
	}
}
