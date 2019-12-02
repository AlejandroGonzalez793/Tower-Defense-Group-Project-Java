package model.enemies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Pterosaur extends Enemy {
	private static final int HEALTH = 15;
	private static final int SPEED = 10;
	private static final int POWER = 3;
	
	public Pterosaur(int x, int y, int width, int height, int health, int speed, int power) {
		super(x, y, width, height, health, speed, power);
		try {
			image = new Image(new FileInputStream("resources/images/enemies/pterosaur_small.gif"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Pterosaur(int x, int y, int width, int height, int speed, int power) {
		this(x, y, width, height, HEALTH, speed, POWER);
	}
	
	public Pterosaur(int x, int y) {
		this(x, y, 50, 50, HEALTH, SPEED, POWER);
	}
}
