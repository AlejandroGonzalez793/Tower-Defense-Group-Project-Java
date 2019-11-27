package model;

import java.awt.geom.Line2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.shape.Line;

public class Stage {
	private Image image;
	private List<Line> lines;
	private int pathWidth;
	
	public Stage() {
		this.lines = new ArrayList<>();
		lines.add(new Line(0, 75, 275, 75));
		lines.add(new Line(275, 75, 275, 125));
		lines.add(new Line(275, 125, 425, 125));
		lines.add(new Line(425, 125, 425, 225));
		lines.add(new Line(425, 225, 75, 225));
		lines.add(new Line(75, 225, 75, 325));
		lines.add(new Line(75, 325, 525, 325));
		lines.add(new Line(525, 325, 525, 625));
		lines.add(new Line(525, 625, 750, 625));
		
		this.pathWidth = 50;
		
		try {
			image = new Image(new FileInputStream("resources/images/maps/TDMap2.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Image getImage() {
		return image;
	}
	
	public List<Line> getLines() {
		return lines;
	}
	
	public int getPathWidth() {
		return pathWidth;
	}
}
