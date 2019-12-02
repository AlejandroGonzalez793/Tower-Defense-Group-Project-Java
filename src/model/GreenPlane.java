package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class GreenPlane extends Enemy{
	
	public GreenPlane(int x, int y, int width, int height, int dx, int dy, int health) {
		super(x, y, width, height, dx, dy, health);
		try {
			image = new Image(new FileInputStream("resources/images/enemies/green_plane.gif"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public GreenPlane(int x, int y, int width, int height, int dx, int dy) {
		this(x, y, width, height, dx, dy, DEFAULT_HEALTH);
		try {
			image = new Image(new FileInputStream("resources/images/enemies/green_plane.gif"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public GreenPlane(int x, int y) {
		this(x, y, 50, 50, 6, 6, DEFAULT_HEALTH);
		try {
			image = new Image(new FileInputStream("resources/images/enemies/green_plane.gif"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
