package model;

import javafx.scene.image.Image;

/**
 * The underlying object on our game board that may contain references to 
 * all other entities that can be drawn in the GUI. Contains information
 * that specifies how to draw itself when it is retrieved.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu) 
 * 
 * @see Entity
 */
public class Tile {
	public static final int DEFAULT_WIDTH = 50;
	public static final int DEFAULT_HEIGHT = 50;
	
	private Entity entity;
	private Image image;
	private boolean placeable;
	private int width;
	private int height;
	
	public Tile(Image image, boolean placeable, int width, int height) {
		this.image = image;
		this.placeable = placeable;
		this.width = width;
		this.height = height;
	}
	
	public Tile(Image image, boolean placeable) {
		this(image, placeable, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public boolean getPlaceable() {
		return placeable;
	}
	
	public void setPlaceable(boolean placeable) {
		this.placeable = placeable;
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
}
