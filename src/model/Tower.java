package model;

import javafx.scene.image.Image;

public class Tower extends Entity {
	public static final double SELLBACK_FACTOR = 0.9;
	private int cost;
	
	public Tower(Image image, int x, int y, int width, int height, int cost) {
		super(image, x, y, width, height);
		this.cost = cost;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
}
