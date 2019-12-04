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
	protected int dx; // x velocity
	protected int dy; // y velocity
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
	
	public void update() {
		x += dx;
		y += dy;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getDx() {
		return dx;
	}
	
	public void setDx(int dx) {
		this.dx = dx;
	}
	
	public int getDy() {
		return dy;
	}
	
	public void setDy(int dy) {
		this.dy = dy;
	}
	
	public Image getImage() {
		if (image == null) {
			image = ResourceManager.getImage("Default");
		}
		
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
