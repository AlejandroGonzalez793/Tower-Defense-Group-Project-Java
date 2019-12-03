package model.enemies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class RedHelicopter extends Enemy {
	private static final int HEALTH = 30;
	private static final int SPEED = 4;
	private static final int POWER = 10;
	private static final int GOLD = 30;

	public RedHelicopter(int x, int y, int width, int height, int health, int speed, int power, int gold) {
		super(x, y, width, height, health, speed, power, gold);
		try {
			image = new Image(new FileInputStream("resources/images/enemies/red_helicopter.gif"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public RedHelicopter(int x, int y, int width, int height, int speed, int power) {
		this(x, y, width, height, HEALTH, speed, POWER, GOLD);
	}
	
	public RedHelicopter(int x, int y) {
		this(x, y, 50, 50, HEALTH, SPEED, POWER, GOLD);
	}
}
