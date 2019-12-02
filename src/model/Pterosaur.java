package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Pterosaur extends Enemy {
	private static final int SPEED = 10;
	
	public Pterosaur(int x, int y, int width, int height, int speed, int health) {
		super(x, y, width, height, speed, health);
		try {
			image = new Image(new FileInputStream("resources/images/enemies/pterosaur_small.gif"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Pterosaur(int x, int y, int width, int height, int speed) {
		this(x, y, width, height, speed, DEFAULT_HEALTH);
	}
	
	public Pterosaur(int x, int y) {
		this(x, y, 50, 50, SPEED, DEFAULT_HEALTH);
	}
}
