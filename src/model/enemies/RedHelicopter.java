package model.enemies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class RedHelicopter extends Enemy {
	private static final int SPEED = 4;

	public RedHelicopter(int x, int y, int width, int height, int speed, int health) {
		super(x, y, width, height, speed, health);
		try {
			image = new Image(new FileInputStream("resources/images/enemies/red_helicopter.gif"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public RedHelicopter(int x, int y, int width, int height, int speed) {
		this(x, y, width, height, speed, DEFAULT_HEALTH);
	}
	
	public RedHelicopter(int x, int y) {
		this(x, y, 50, 50, SPEED, DEFAULT_HEALTH);
	}
}
