package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Stage {
	private Image image;
	private List<int[]> points;
	private int pathWidth;
	
	public Stage() {
		this.points = new ArrayList<>();
		points.add(new int[] {0, 75});
		points.add(new int[] {275, 75});
		points.add(new int[] {275, 125});
		points.add(new int[] {425, 125});
		points.add(new int[] {425, 225});
		points.add(new int[] {75, 225});
		points.add(new int[] {75, 325});
		points.add(new int[] {525, 325});
		points.add(new int[] {525, 625});
		points.add(new int[] {750, 625});
		
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
	
	public List<int[]> getPoints() {
		return points;
	}
	
	public int getPathWidth() {
		return pathWidth;
	}
}
