package model.enemies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Drifblim extends Enemy {
	private static final int HEALTH = 150;
	private static final int SPEED = 2;
	private static final int POWER = 20;
	
	public Drifblim(int x, int y, int width, int height, int health, int speed, int power) {
		super(x, y, width, height, health, speed, power);
		try {
			image = new Image(new FileInputStream("resources/images/enemies/Drifblim.gif"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Drifblim(int x, int y, int width, int height, int speed, int power) {
		this(x, y, width, height, HEALTH, speed, POWER);
	}
	
	public Drifblim(int x, int y) {
		this(x, y, 50, 50, HEALTH, SPEED, POWER);
	}

}
