package model.projectiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class RapidBullet extends Projectile {
	private static final int WIDTH = 4;
	private static final int HEIGHT = 4;
	private static final int SPEED = 10;
	private static final int POWER = 2;
	private static final int RADIUS = 100;
    
	public RapidBullet(int x, int y, int width, int height, int speed, int power, int radius) {
		super(x, y, width, height, speed, power, radius);
		try {
			image = new Image(new FileInputStream("resources/images/projectiles/rapid_bullet.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public RapidBullet(int x, int y, int width, int height) {
		this(x, y, width, height, SPEED, POWER, RADIUS);
	}
	
	public RapidBullet(int x, int y) {
		this(x, y, WIDTH, HEIGHT, SPEED, POWER, RADIUS);
	}
}
