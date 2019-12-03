package model.enemies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Drifblim extends Enemy {
	private static final int HEALTH = 150;
	private static final int SPEED = 2;
	private static final int POWER = 20;
	private static final int GOLD = 100;
	
	public Drifblim(int x, int y, int width, int height, int health, int speed, int power, int gold) {
		super(x, y, width, height, health, speed, power, gold);
		try {
			image = new Image(new FileInputStream("resources/images/enemies/Drifblim.gif"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Drifblim(int x, int y, int width, int height, int speed, int power, int gold) {
		this(x, y, width, height, HEALTH, speed, POWER, GOLD);
	}
	
	public Drifblim(int x, int y) {
		this(x, y, 50, 50, HEALTH, SPEED, POWER, GOLD);
	}

}
