package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class OneShotBullet extends Projectile{
	
	public OneShotBullet(int x, int y, int width, int height, int dx, int dy, int power) {
		super(x, y, width, height, dx, dy, power);
		try {
			image = new Image(new FileInputStream("resources/images/projectiles/one_shot_bullet.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public OneShotBullet(int x, int y, int width, int height) {
		this(x, y, width, height, 10, 10, DEFAULT_POWER+999);
		try {
			image = new Image(new FileInputStream("resources/images/projectiles/one_shot_bullet.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public OneShotBullet(int x, int y) {
		this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, 10, 10, DEFAULT_POWER+999);
		try {
			image = new Image(new FileInputStream("resources/images/projectiles/one_shot_bullet.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
