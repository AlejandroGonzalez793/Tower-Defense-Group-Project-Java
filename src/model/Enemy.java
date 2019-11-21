package model;

import javafx.scene.image.Image;

public class Enemy extends Entity {
	private int speed;
	
	public Enemy(Image image, int speed) {
		super(image);
		this.speed = speed;
	}
	
	public int getSpeed() {
		return speed;
	}
}
