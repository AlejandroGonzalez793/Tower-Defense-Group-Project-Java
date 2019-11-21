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
	private static final String FILE = "TDMap2";
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new BorderPane();
		
		StackPane content = new StackPane();
		canvas = new Canvas();
		gc = canvas.getGraphicsContext2D();
		
		createMap();
		createLayout();
		
		content.getChildren().add(canvas);
		content.getChildren().add(grid);
		
		root.setCenter(content);

		scene = new Scene(root);
		primaryStage.setTitle("Tower Defense");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void createLayout() {
		Button tower = new Button("Buy Tower");
		GridPane.setConstraints(tower, 0,0);
		root.setRight(tower);
		
		
	}
	
	public GraphicsContext createMap() { 
		root = new BorderPane();
		grid = new GridPane();
		Canvas canvas = new Canvas();
		try {// grid pane is not being used right now, might keep it if needed for checking if spot taken
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
				   String road = "Ground.png";
				   String grass = "Grass.png";
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
