package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class PiercingBullet extends Projectile {
	private static final int SPEED = 10;
	private static final int RADIUS = 50;
    
	public PiercingBullet(int x, int y, int width, int height, int speed, int power) {
		super(x, y, width, height, speed, power);
		try {
			image = new Image(new FileInputStream("resources/images/projectiles/piercing_bullet.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public PiercingBullet(int x, int y, int width, int height) {
		this(x, y, width, height, SPEED, DEFAULT_POWER+4);
	}
	
	public PiercingBullet(int x, int y) {
		this(x, y, DEFAULT_WIDTH-34, DEFAULT_HEIGHT-35, SPEED, DEFAULT_POWER+4);
	}
	
	public int getRadius() {
		return RADIUS;
	}

}
