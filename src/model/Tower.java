package model;

import javafx.scene.image.Image;

public class Tower extends Entity {
	public static final double SELLBACK_FACTOR = 0.9;
	
	private int width;
	private int height;
	private int cost;
	
	public Tower(Image image, int width, int height, int cost) {
		super(image);
		this.width = width;
		this.height = height;
		this.cost = cost;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
}
