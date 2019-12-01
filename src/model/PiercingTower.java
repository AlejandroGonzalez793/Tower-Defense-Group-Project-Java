package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class PiercingTower extends Tower{

	public PiercingTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		try {
			Image image = new Image(new FileInputStream("resources/images/Thicc_Yoshi.gif"));
			setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public PiercingTower(int x, int y, int width, int height) {
		this(x, y, width, height, 150, 10, 1000);
		try {
			Image image = new Image(new FileInputStream("resources/images/Thicc_Yoshi.gif"));
			setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public PiercingTower() {
		this(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_RADIUS + 50, DEFAULT_RATE + 7, DEFAULT_COST + 200);
		try {
			Image image = new Image(new FileInputStream("resources/images/Thicc_Yoshi.gif"));
			setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Projectile getProjectile() {
		return new PiercingBullet(x, y);
	}
}
