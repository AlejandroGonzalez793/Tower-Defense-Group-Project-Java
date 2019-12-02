package model.enemies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class GreenPlane extends Enemy {
	private static final int HEALTH = 10;
	private static final int SPEED = 6;
	private static final int POWER = 7;
	
	public GreenPlane(int x, int y, int width, int height, int health, int speed, int power) {
		super(x, y, width, height, health, speed, power);
		try {
			image = new Image(new FileInputStream("resources/images/enemies/green_plane.gif"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public GreenPlane(int x, int y, int width, int height, int speed, int power) {
		this(x, y, width, height, HEALTH, speed, POWER);
	}
	
	public GreenPlane(int x, int y) {
		this(x, y, 50, 50, HEALTH, SPEED, POWER);
	}

}
