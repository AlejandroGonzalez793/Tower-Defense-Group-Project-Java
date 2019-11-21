package model;

import javafx.scene.image.Image;

public abstract class Entity {
	private Image image;
	
	public Entity(Image image) {
		this.image = image;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
}
