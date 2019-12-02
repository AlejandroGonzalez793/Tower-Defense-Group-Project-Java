package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class PiercingBullet extends Projectile{
	
	public PiercingBullet(int x, int y, int width, int height, int dx, int dy, int power) {
		super(x, y, width, height, dx, dy, power);
		try {
			image = new Image(new FileInputStream("resources/images/projectiles/piercing_bullet.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public PiercingBullet(int x, int y, int width, int height) {
		this(x, y, width, height, 10, 10, DEFAULT_POWER+4);
		try {
			image = new Image(new FileInputStream("resources/images/projectiles/piercing_bullet.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public PiercingBullet(int x, int y) {
		this(x, y, DEFAULT_WIDTH-34, DEFAULT_HEIGHT-35, 10, 10, DEFAULT_POWER+4);
		try {
			image = new Image(new FileInputStream("resources/images/projectiles/piercing_bullet.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
