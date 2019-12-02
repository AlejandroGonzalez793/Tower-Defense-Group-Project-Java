package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class AreaBullet extends Projectile{
	
	public AreaBullet(int x, int y, int width, int height, int dx, int dy, int power) {
		super(x, y, width, height, dx, dy, power);
		try {
			image = new Image(new FileInputStream("resources/images/projectiles/area_bullet.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public AreaBullet(int x, int y, int width, int height) {
		this(x, y, width, height, 10, 10, DEFAULT_POWER+1);
		try {
			image = new Image(new FileInputStream("resources/images/projectiles/area_bullet.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public AreaBullet(int x, int y) {
		this(x, y, DEFAULT_WIDTH-22, DEFAULT_HEIGHT-22, 10, 10, DEFAULT_POWER+1);
		try {
			image = new Image(new FileInputStream("resources/images/projectiles/area_bullet.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
