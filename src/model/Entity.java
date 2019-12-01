package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

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
	private int width; // width in pixels
	private int height; // height in pixels
	private int dx; // x velocity
	private int dy; // y velocity
	private static Image image;
	
	public Entity(int x, int y, int width, int height, int dx, int dy) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.dx = dx;
		this.dy = dy;
		
		try {
			image = new Image(new FileInputStream("resources/images/towers/default_tower.png"));
			//image = new Image(new FileInputStream("resources/images/Thicc_Yoshi.gif"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Entity(int x, int y, int width, int height) {
		this(x, y, width, height, 0, 0);
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
		return image;
	}
	
	public void setImage(Image image) {
		Entity.image = image;
	}
}
