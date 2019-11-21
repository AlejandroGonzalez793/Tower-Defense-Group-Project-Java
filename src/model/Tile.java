package model;

import javafx.scene.image.Image;

public class Tile {
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
		this(image, placeable, 50, 50);
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
