package model.towers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import model.projectiles.OneShotBullet;
import model.projectiles.Projectile;

public class OneShotTower extends Tower{
	
	public OneShotTower(int x, int y, int width, int height, int radius, int rate, int cost) {
		super(x, y, width, height, radius, rate, cost);
		try {
			image = new Image(new FileInputStream("resources/images/towers/one_shot_tower.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public OneShotTower(int x, int y, int width, int height) {
		this(x, y, width, height, 150, 10, 1000);
		try {
			image = new Image(new FileInputStream("resources/images/towers/one_shot_tower.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public OneShotTower() {
		this(0, 0, DEFAULT_WIDTH + 25, DEFAULT_HEIGHT + 25, DEFAULT_RADIUS + 100, DEFAULT_RATE + 9, DEFAULT_COST + 450);
		try {
			image = new Image(new FileInputStream("resources/images/towers/one_shot_tower.png"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Projectile getProjectile() {
		return new OneShotBullet(x, y);
	}
}
