package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Balloon extends Enemy{
	
	public Balloon(int x, int y, int width, int height, int dx, int dy, int health) {
		super(x, y, width, height, dx, dy, health);
		try {
			Image image = new Image(new FileInputStream("resources/images/enemies/balloon_purple.gif"));
			setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Balloon(int x, int y, int width, int height, int dx, int dy) {
		this(x, y, width, height, dx, dy, DEFAULT_HEALTH);
		try {
			Image image = new Image(new FileInputStream("resources/images/enemies/balloon_purple.gif"));
			setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Balloon(int x, int y) {
		this(x, y, 50, 50, 1, 1, DEFAULT_HEALTH);
		try {
			Image image = new Image(new FileInputStream("resources/images/enemies/balloon_purple.gif"));
			setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
