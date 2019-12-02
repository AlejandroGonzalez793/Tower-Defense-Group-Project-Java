package model.enemies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Balloon extends Enemy {
	private static final int HEALTH = 5;
	private static final int SPEED = 1;
	private static final int POWER = 1;
	
	public Balloon(int x, int y, int width, int height, int health, int speed, int power) {
		super(x, y, width, height, health, speed, power);
		try {
			image = new Image(new FileInputStream("resources/images/enemies/balloon_purple.gif"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Balloon(int x, int y, int width, int height, int speed, int power) {
		this(x, y, width, height, HEALTH, SPEED, POWER);
	}
	
	public Balloon(int x, int y) {
		this(x, y, 50, 50, HEALTH, SPEED, POWER);
	}

}
