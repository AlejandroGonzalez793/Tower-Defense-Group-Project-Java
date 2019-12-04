package model.towers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import model.projectiles.AreaBullet;
import model.projectiles.Projectile;

public class AreaTower extends Tower{
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private static final int RADIUS = 400;
	private static final int RATE = 3;
	private static final int COST = 200;

	public AreaTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		try {
			image = new Image(new FileInputStream("resources/images/towers/area_tower.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public AreaTower(int x, int y, int width, int height) {
		this(x, y, width, height, 150, 10, 1000);
		try {
			image = new Image(new FileInputStream("resources/images/towers/area_tower.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public AreaTower() {
		this(0, 0, WIDTH, HEIGHT, RADIUS, RATE, COST);
		try {
			image = new Image(new FileInputStream("resources/images/towers/area_tower.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Projectile getProjectile() {
		return new AreaBullet(x, y);
	}
}
