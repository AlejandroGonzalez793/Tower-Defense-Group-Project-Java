package model;

import javafx.scene.image.Image;

/**
 * A minimal template for the different Towers that can be drawn and used
 * in the game.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu) 
 */
public class Tower extends Entity {
	public static final double SELLBACK_FACTOR = 0.9;
	public static final double DEFAULT_RATE = 1.0;
	
	private int cost;
	private double rate; // rate at which the tower fires
	
	public Tower(Image image, int x, int y, int width, int height, int cost, double rate) {
		super(image, x, y, width, height);
		this.cost = cost;
		this.rate = rate;
	}
	
	public Tower(Image image, int x, int y, int width, int height, int cost) {
		this(image, x, y, width, height, cost, DEFAULT_RATE);
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public double getRate() {
		return rate;
	}
	
	public void setRate(double rate) {
		this.rate = rate;
	}
}
