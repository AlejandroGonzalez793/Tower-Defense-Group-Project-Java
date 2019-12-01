package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class StrongBullet extends Projectile{
	
	public StrongBullet(int x, int y, int width, int height, int dx, int dy, int power) {
		super(x, y, width, height, dx, dy, power);
		try {
			Image image = new Image(new FileInputStream("resources/images/projectiles/strong_bullet.png"));
			setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public StrongBullet(int x, int y, int width, int height) {
		this(x, y, width, height, 10, 10, DEFAULT_POWER+4);
		try {
			Image image = new Image(new FileInputStream("resources/images/projectiles/strong_bullet.png"));
			setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public StrongBullet(int x, int y) {
		this(x, y, DEFAULT_WIDTH-22, DEFAULT_HEIGHT-22, 10, 10, DEFAULT_POWER+9);
		try {
			Image image = new Image(new FileInputStream("resources/images/projectiles/strong_bullet.png"));
			setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
