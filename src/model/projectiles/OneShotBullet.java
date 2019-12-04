package model.projectiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class OneShotBullet extends Projectile {
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private static final int SPEED = 10;
	private static final int POWER = 1000;
	private static final int RADIUS = 150;
    
	public OneShotBullet(int x, int y, int width, int height, int speed, int power, int radius) {
		super(x, y, width, height, speed, power, radius);
		try {
			image = new Image(new FileInputStream("resources/images/projectiles/one_shot_bullet.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public OneShotBullet(int x, int y, int width, int height) {
		this(x, y, width, height, SPEED, POWER, RADIUS);
	}
	
	public OneShotBullet(int x, int y) {
		this(x, y, WIDTH, HEIGHT, SPEED, POWER, RADIUS);
	}
}
