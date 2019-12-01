package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Drifblim extends Enemy{
	
	public Drifblim(int x, int y, int width, int height, int dx, int dy, int health) {
		super(x, y, width, height, dx, dy, health);
		try {
			Image image = new Image(new FileInputStream("resources/images/enemies/Drifblim.gif"));
			setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Drifblim(int x, int y, int width, int height, int dx, int dy) {
		this(x, y, width, height, dx, dy, DEFAULT_HEALTH + 145);
		try {
			Image image = new Image(new FileInputStream("resources/images/enemies/Drifblim.gif"));
			setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Drifblim(int x, int y) {
		this(x, y, 50, 50, 2, 2, DEFAULT_HEALTH + 145);
		try {
			Image image = new Image(new FileInputStream("resources/images/enemies/Drifblim.gif"));
			setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
