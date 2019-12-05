package model;

import javafx.scene.image.Image;
import util.ResourceManager;

/**
 * A general abstract Entity class used to represent different objects used
 * and displayed in the game. Contains the information necessary to figure
 * out the entities position on the board and how to draw it when it is 
 * retrieved.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu) 
 */
public abstract class Entity {
	public static final int DEFAULT_WIDTH = 50;
	public static final int DEFAULT_HEIGHT = 50;
	
	protected int x; // x coord of top left corner
	protected int y; // y coord of top left corner
	protected double dx; // x velocity
	protected double dy; // y velocity
	protected int speed; 
	protected Image image;
	
	private int width; // width in pixels
	private int height; // height in pixels
	
	public Entity(int x, int y, int width, int height, int speed) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
	}
	
	public Entity(int x, int y, int width, int height) {
		this(x, y, width, height, 0);
	}
	
	/**
	 * Updates the x and y coordinates by adding dx and dy.
	 */
	public void update() {
		x += dx;
		y += dy;
	}
	
	/**
	 * Gets the x coordinate of the entity.
	 * @return x coordinate of the entity
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Sets the x coordinate of the entity.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Gets the y coordinate of the entity.
	 * @return y coordinate of the entity
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Sets the y coordinate of the entity.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Gets the width of the entity.
	 * @return width value of the entity
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Sets the width of the entity.
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Gets the height of the entity.
	 * @return height value of the entity
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Sets the height of the entity.
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Gets the dx value of the entity.
	 * @return dx value of the entity.
	 */
	public double getDx() {
		return dx;
	}
	
	/**
	 * Sets the dx value of the entity.
	 */
	public void setDx(double dx) {
		this.dx = dx;
	}
	
	
	/**
	 * Gets the dy value of the entity.
	 * @return dy value of the entity.
	 */
	public double getDy() {
		return dy;
	}
	
	/**
	 * Sets the dy value of the entity.
	 */
	public void setDy(double dy) {
		this.dy = dy;
	}
	
	/**
	 * Gets the image of the entity from the resource manager.
	 * @return image of the entity from the resource manager.
	 */
	public Image getImage() {
		if (image == null) {
			image = ResourceManager.getImage("Default");
		}
		
		return image;
	}
	
	/**
	 * Sets the image of the entity from the resource manager.
	 */
	public void setImage(Image image) {
		this.image = image;
	}
	
	/**
	 * Gets the speed of the entity.
	 * @return speed of the entity.
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * Sets the speed of the entity.
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
