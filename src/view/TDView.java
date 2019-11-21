package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TDView extends Application {
	
	private BorderPane root;
	private Scene scene;
	private GridPane grid;
	private Canvas canvas;
	private GraphicsContext gc;
	private StackPane content;
	private int rows;
	private int columns;
	private static final String FILE = "/Users/jarodbristol/OneDrive/Desktop/University of Arizona/Fall 2019/CSC 335/Projects/csc335-final-project-TD/TDMap2";
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		gc = createMap();

		scene = new Scene(root);
		primaryStage.setTitle("Tower Defense");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public GraphicsContext createMap() { // grid pane is not being used right now, might keep it if needed for checking if spot taken
		root = new BorderPane();
		grid = new GridPane();
		Canvas canvas = new Canvas();
		try {
			Scanner in = new Scanner(new File(FILE));
			columns = Integer.valueOf(in.nextLine());
			rows = Integer.valueOf(in.nextLine());
			canvas.setHeight(rows*50);
			canvas.setWidth(columns*50);
			gc = canvas.getGraphicsContext2D();
			content = new StackPane();
			content.getChildren().add(grid);
			content.getChildren().add(canvas);
			int k = 0;
			while (in.hasNextLine()) {
               String line = in.nextLine();
               for (int i = 0; i < line.length(); i ++) {
            	   FileInputStream input = null;
				   String road = "/Users/jarodbristol/OneDrive/Desktop/University of Arizona/Fall 2019/CSC 335/Projects/csc335-final-project-TD/Road.png";
				   String grass = "/Users/jarodbristol/OneDrive/Desktop/University of Arizona/Fall 2019/CSC 335/Projects/csc335-final-project-TD/Grass.png";
				   if (line.charAt(i) == '*') {
            		   input = new FileInputStream(grass);
            		   Image image = new Image(input); 
            		   gc.drawImage(image, i * 50, k* 50);
            	   } else if (line.charAt(i) == '-') {
            		   input = new FileInputStream(road);
            		   Image image = new Image(input); 
            		   gc.drawImage(image, i* 50, k*50);
            	   }
               }
               k++;
            }
            in.close();
		} catch (FileNotFoundException e){
			System.out.println("File not found " + FILE); // change later 
		}
		root.setCenter(content);
		return canvas.getGraphicsContext2D();
	}

}
