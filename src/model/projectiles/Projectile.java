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
	public static final int DEFAULT_RADIUS = 150;
	public static Image image;
	
	private int power;
	private int distance;
	
	public Projectile(int x, int y, int width, int height, int speed, int power) {
		super(x, y, width, height, speed);
		this.power = power;
		if (image == null) {
			setImage();
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
	
	public void update() {
		x += DEFAULT_SPEED;
		y += DEFAULT_SPEED;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void setDistance() {
		distance += DEFAULT_SPEED;
	}
	
	public void setImage() {
		try {
			image = new Image(new FileInputStream("resources/images/projectiles/bullet_bill.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void update(int dxx, int dyy) {
		x = dx;
		y = dy; // issues
		System.out.println(dxx);
		System.out.println(dyy);
	}
}
