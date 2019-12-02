package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class RedHelicopter extends Enemy{

	public RedHelicopter(int x, int y, int width, int height, int dx, int dy, int health) {
		super(x, y, width, height, dx, dy, health);
		try {
			image = new Image(new FileInputStream("resources/images/enemies/red_helicopter.gif"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public RedHelicopter(int x, int y, int width, int height, int dx, int dy) {
		this(x, y, width, height, dx, dy, DEFAULT_HEALTH);
		try {
			image = new Image(new FileInputStream("resources/images/enemies/red_helicopter.gif"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public RedHelicopter(int x, int y) {
		this(x, y, 50, 50, 4, 4, DEFAULT_HEALTH);
		try {
			image = new Image(new FileInputStream("resources/images/enemies/red_helicopter.gif"));
			//this.setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
