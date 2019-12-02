package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class StrongBullet extends Projectile{
	
	private static final int RADIUS = 50;
	
	public StrongBullet(int x, int y, int width, int height, int dx, int dy, int power) {
		super(x, y, width, height, dx, dy, power);
		try {
			image = new Image(new FileInputStream("resources/images/projectiles/strong_bullet.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public StrongBullet(int x, int y, int width, int height) {
		this(x, y, width, height, 10, 10, DEFAULT_POWER+4);
		try {
			image = new Image(new FileInputStream("resources/images/projectiles/strong_bullet.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public StrongBullet(int x, int y) {
		this(x, y, DEFAULT_WIDTH-22, DEFAULT_HEIGHT-22, 10, 10, DEFAULT_POWER+9);
		try {
			image = new Image(new FileInputStream("resources/images/projectiles/strong_bullet.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public int getRadius() {
		return RADIUS;
	}

}
