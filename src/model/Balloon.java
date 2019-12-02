package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Balloon extends Enemy {
	private static final int SPEED = 1;
	
	public Balloon(int x, int y, int width, int height, int speed, int health) {
		super(x, y, width, height, speed, health);
		try {
			image = new Image(new FileInputStream("resources/images/enemies/balloon_purple.gif"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Balloon(int x, int y, int width, int height, int speed) {
		this(x, y, width, height, speed, DEFAULT_HEALTH);
	}
	
	public Balloon(int x, int y) {
		this(x, y, 50, 50, SPEED, DEFAULT_HEALTH);
	}

}
