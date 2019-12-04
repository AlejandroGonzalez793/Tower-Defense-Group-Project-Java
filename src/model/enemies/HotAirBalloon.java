package model.enemies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class HotAirBalloon extends Enemy {
	private static final int HEALTH = 20;
	private static final int SPEED = 2;
	private static final int POWER = 5;
	private static final int GOLD = 15;
	
	public HotAirBalloon(int x, int y, int width, int height, int health, int speed, int power, int gold) {
		super(x, y, width, height, health, speed, power, gold);
		try {
			image = new Image(new FileInputStream("resources/images/enemies/hot_air_balloon.gif"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public HotAirBalloon(int x, int y, int width, int height, int speed, int power) {
		this(x, y, width, height, HEALTH, speed, POWER, GOLD);
	}
	
	public HotAirBalloon(int x, int y) {
		this(x, y, 50, 50, HEALTH, SPEED, POWER, GOLD);
	}

}
