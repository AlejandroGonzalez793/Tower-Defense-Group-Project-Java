package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TDView extends Application {
	
	private BorderPane root;
	private Scene scene;
	private GridPane grid;
	private Canvas canvas;
	private GraphicsContext gc;
	private int rows;
	private int columns;
	private static final String FILE = "/Users/jarodbristol/OneDrive/Desktop/University of Arizona/Fall 2019/CSC 335/Projects/csc335-final-project-TD/TDMap2";
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new BorderPane();
		
		StackPane content = new StackPane();
		canvas = new Canvas();
		gc = canvas.getGraphicsContext2D();
		
		createMap2();
		createLayout();
		
		content.getChildren().add(canvas);
		content.getChildren().add(grid);
		
		root.setCenter(content);

		scene = new Scene(root, columns*50, rows*50);
		primaryStage.setTitle("Tower Defense");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void createLayout() {
		Button tower = new Button("Buy Tower");
		GridPane.setConstraints(tower, 0,0);
		grid.add(tower,0,0);
	}
	
	public void createMap2() {
		grid = new GridPane();
		try {
			Scanner in = new Scanner(new File(FILE));
			columns = Integer.valueOf(in.nextLine());
			rows = Integer.valueOf(in.nextLine());
			int k = 0;
			while (in.hasNextLine()) {
               String line = in.nextLine();
               for (int i = 0; i < line.length(); i ++) {
            	   Rectangle tile = new Rectangle(50 *k, 50 *i, 50, 50);
            	   FileInputStream input = null;
				   String road = "/Users/jarodbristol/OneDrive/Desktop/University of Arizona/Fall 2019/CSC 335/Projects/csc335-final-project-TD/Road.png";
				   String grass = "/Users/jarodbristol/OneDrive/Desktop/University of Arizona/Fall 2019/CSC 335/Projects/csc335-final-project-TD/Grass.png";
				   if (line.charAt(i) == '*') {
            		   input = new FileInputStream(grass);
            		   Image image = new Image(input); 
            		   ImagePattern image_pattern = new ImagePattern(image);
            		   tile.setFill(image_pattern);
            	   } else if (line.charAt(i) == '-') {
            		   input = new FileInputStream(road);
            		   Image image = new Image(input); 
            		   ImagePattern image_pattern = new ImagePattern(image);
            		   tile.setFill(image_pattern);
            	   }
            	   grid.add(tile, i, k); 
               }
               k++;
            }
            in.close();
		} catch (FileNotFoundException e){
			System.out.println("File not found " + FILE); // change later 
		}
	}

}
