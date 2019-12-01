package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class RapidTower extends Tower{

	public RapidTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		try {
			Image image = new Image(new FileInputStream("resources/images/towers/rapid_tower.png"));
			setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public RapidTower(int x, int y, int width, int height) {
		this(x, y, width, height, 150, 10, 1000);
		try {
			Image image = new Image(new FileInputStream("resources/images/towers/rapid_tower.png"));
			setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public RapidTower() {
		this(0, 0, DEFAULT_WIDTH - 15, DEFAULT_HEIGHT, DEFAULT_RADIUS + 50, DEFAULT_RATE + 19, DEFAULT_COST + 250);
		try {
			Image image = new Image(new FileInputStream("resources/images/towers/rapid_tower.png"));
			setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Projectile getProjectile() {
		return new Projectile(x, y);
	}
}
