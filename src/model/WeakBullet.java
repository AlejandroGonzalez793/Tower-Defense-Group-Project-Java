package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class WeakBullet extends Projectile{
	
	public WeakBullet(int x, int y, int width, int height, int dx, int dy, int power) {
		super(x, y, width, height, dx, dy, power);
		try {
			Image image = new Image(new FileInputStream("resources/images/projectiles/weak_bullet.png"));
			setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public WeakBullet(int x, int y, int width, int height) {
		this(x, y, width, height, 10, 10, DEFAULT_POWER);
		try {
			Image image = new Image(new FileInputStream("resources/images/projectiles/weak_bullet.png"));
			setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public WeakBullet(int x, int y) {
		this(x, y, DEFAULT_WIDTH-34, DEFAULT_HEIGHT-40, 10, 10, DEFAULT_POWER);
		try {
			Image image = new Image(new FileInputStream("resources/images/projectiles/weak_bullet.png"));
			setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
