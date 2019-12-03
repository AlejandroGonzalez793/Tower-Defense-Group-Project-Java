package model.projectiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import model.Entity;

public class Projectile extends Entity {
	public static final int DEFAULT_POWER = 1;
	public static final int DEFAULT_WIDTH = 10;
	public static final int DEFAULT_HEIGHT = 10;
	public static final int DEFAULT_SPEED = 10;
	public static final int DEFAULT_RADIUS = 50;
	
	private int power;
	
	public Projectile(int x, int y, int width, int height, int speed, int power) {
		super(x, y, width, height, speed);
		this.power = power;
		try {
			image = new Image(new FileInputStream("resources/images/projectiles/bullet_bill.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Projectile(int x, int y, int width, int height) {
		this(x, y, width, height, DEFAULT_SPEED, DEFAULT_POWER);
	}
	
	public Projectile(int x, int y) {
		this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_SPEED, DEFAULT_POWER);
	}

	public int getPower() {
		return power;
	}

	public int getRadius() {
		return DEFAULT_RADIUS;
	}
	
	@Override
	public void update() {
		x += speed + 50;
		y += speed+ 50;
	}
}
