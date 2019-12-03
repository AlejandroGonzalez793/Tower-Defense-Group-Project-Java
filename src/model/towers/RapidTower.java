package model.towers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import model.projectiles.Projectile;
import model.projectiles.RapidBullet;

public class RapidTower extends Tower{
	private static final int WIDTH = 35;
	private static final int HEIGHT = 50;
	private static final int RADIUS = 100;
	private static final int RATE = 20;
	private static final int COST = 300;

	public RapidTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		try {
			image = new Image(new FileInputStream("resources/images/towers/rapid_tower.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public RapidTower(int x, int y, int width, int height) {
		this(x, y, width, height, 150, 10, 1000);
		try {
			image = new Image(new FileInputStream("resources/images/towers/rapid_tower.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public RapidTower() {
		this(0, 0, WIDTH, HEIGHT, RADIUS, RATE, COST);
		try {
			image = new Image(new FileInputStream("resources/images/towers/rapid_tower.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Projectile getProjectile() {
		return new RapidBullet(x, y);
	}
}
