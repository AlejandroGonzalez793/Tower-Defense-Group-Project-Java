package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class CheapTower extends Tower{
	
	public CheapTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		try {
			image = new Image(new FileInputStream("resources/images/towers/cheap_tower.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public CheapTower(int x, int y, int width, int height) {
		this(x, y, width, height, 150, 10, 1000);
		try {
			image = new Image(new FileInputStream("resources/images/towers/cheap_tower.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public CheapTower() {
		this(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_RADIUS, DEFAULT_RATE, DEFAULT_COST - 25);
		try {
			image = new Image(new FileInputStream("resources/images/towers/cheap_tower.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Projectile getProjectile() {
		return new WeakBullet(x, y);
	}
}
