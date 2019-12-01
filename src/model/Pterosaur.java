package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Pterosaur extends Enemy{
	
	public Pterosaur(int x, int y, int width, int height, int dx, int dy, int health) {
		super(x, y, width, height, dx, dy, health);
		try {
			Image image = new Image(new FileInputStream("resources/images/enemies/pterosaur_small.gif"));
			setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Pterosaur(int x, int y, int width, int height, int dx, int dy) {
		this(x, y, width, height, dx, dy, DEFAULT_HEALTH);
		try {
			Image image = new Image(new FileInputStream("resources/images/enemies/pterosaur_small.gif"));
			setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Pterosaur(int x, int y) {
		this(x, y, 50, 50, 10, 10, DEFAULT_HEALTH);
		try {
			Image image = new Image(new FileInputStream("resources/images/enemies/pterosaur_small.gif"));
			setImage(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
