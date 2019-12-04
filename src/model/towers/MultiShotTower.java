package model.towers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import model.projectiles.MultiBullet;
import model.projectiles.Projectile;

public class MultiShotTower extends Tower{
	private static final int WIDTH = 80;
	private static final int HEIGHT = 150;
	private static final int RADIUS = 200;
	private static final int RATE = 5;
	private static final int COST = 300;
	
	public MultiShotTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		try {
			image = new Image(new FileInputStream("resources/images/towers/tall_tower.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public MultiShotTower(int x, int y, int width, int height) {
		this(x, y, width, height, 150, 10, 1000);
		try {
			image = new Image(new FileInputStream("resources/images/towers/tall_tower.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public MultiShotTower() {
		this(0, 0, WIDTH, HEIGHT, RADIUS, RATE, COST);
		try {
			image = new Image(new FileInputStream("resources/images/towers/tall_tower.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Projectile getProjectile() {
		return new MultiBullet(x, y);
	}
}
