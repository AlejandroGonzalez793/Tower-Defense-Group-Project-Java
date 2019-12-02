package model.enemies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class RedHelicopter extends Enemy {
	private static final int SPEED = 4;

	public RedHelicopter(int x, int y, int width, int height, int health, int speed) {
		super(x, y, width, height, health, speed);
		try {
			image = new Image(new FileInputStream("resources/images/enemies/red_helicopter.gif"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public RedHelicopter(int x, int y, int width, int height, int speed) {
		this(x, y, width, height, DEFAULT_HEALTH, speed);
	}
	
	public RedHelicopter(int x, int y) {
		this(x, y, 50, 50, DEFAULT_HEALTH, SPEED);
	}
}
