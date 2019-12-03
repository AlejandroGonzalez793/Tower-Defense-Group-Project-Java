package model.projectiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class WeakBullet extends Projectile {
	private static final int WIDTH = 16;
	private static final int HEIGHT = 10;
	private static final int SPEED = 10;
	private static final int POWER = 1;
	private static final int RADIUS = 50;
    
	public WeakBullet(int x, int y, int width, int height, int speed, int power) {
		super(x, y, width, height, speed, power);
		try {
			image = new Image(new FileInputStream("resources/images/projectiles/weak_bullet.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public WeakBullet(int x, int y, int width, int height) {
		this(x, y, width, height, SPEED, POWER);
	}
	
	public WeakBullet(int x, int y) {
		this(x, y, WIDTH, HEIGHT, SPEED, POWER);
	}
	
	public int getRadius() {
		return RADIUS;
	}

}