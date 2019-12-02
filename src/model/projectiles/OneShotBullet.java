package model.projectiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class OneShotBullet extends Projectile {
	private static final int SPEED = 10;
	private static final int RADIUS = 50;
    
	public OneShotBullet(int x, int y, int width, int height, int speed, int power) {
		super(x, y, width, height, speed, power);
		try {
			image = new Image(new FileInputStream("resources/images/projectiles/one_shot_bullet.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public OneShotBullet(int x, int y, int width, int height) {
		this(x, y, width, height, SPEED, DEFAULT_POWER+999);
	}
	
	public OneShotBullet(int x, int y) {
		this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, SPEED, DEFAULT_POWER+999);
	}
	
	public int getRadius() {
		return RADIUS;
	}

}
