package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class StrongBullet extends Projectile {
	private static final int SPEED = 10;
	
	public StrongBullet(int x, int y, int width, int height, int speed, int power) {
		super(x, y, width, height, speed, power);
		try {
			image = new Image(new FileInputStream("resources/images/projectiles/strong_bullet.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public StrongBullet(int x, int y, int width, int height) {
		this(x, y, width, height, SPEED, DEFAULT_POWER+4);
	}
	
	public StrongBullet(int x, int y) {
		this(x, y, DEFAULT_WIDTH-22, DEFAULT_HEIGHT-22, SPEED, DEFAULT_POWER+9);
	}

}
