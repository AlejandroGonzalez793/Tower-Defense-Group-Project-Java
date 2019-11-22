package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Entity;

public class TDView extends Application {
	
	private BorderPane root;
	private Scene scene;
	private GraphicsContext gc;
	private char[][] path;
	private int rows;
	private int columns;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Application.Parameters params = this.getParameters(); 
		List<String> rawParams = params.getRaw();
		gc = createMap(rawParams);
		scene = new Scene(root);
		primaryStage.setTitle("Tower Defense");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	/**
	 * This method createMap sets up the map that is based off from a text
	 * file. The green region indicates that a tower can be placed where the 
	 * brown or visible path shows where enemies will be traveling.
	 * @return GraphicsContext A GraphicsContext is returned that is the 
	 * GraphicsContext2D of the canvas. 
	 */
	
	public GraphicsContext createMap(List<String> params) { 
		root = new BorderPane();
		Canvas canvas = new Canvas();
		try {
			Scanner in = new Scanner(new File(params.get(0)));
			columns = Integer.valueOf(in.nextLine());
			rows = Integer.valueOf(in.nextLine());
			path = new char[rows][columns];
			canvas.setHeight(rows * Entity.DEFAULT_HEIGHT);
			canvas.setWidth(columns * Entity.DEFAULT_WIDTH);
			gc = canvas.getGraphicsContext2D();
			int k = 0;
			while (in.hasNextLine()) {
               String line = in.nextLine();
               for (int i = 0; i < line.length(); i ++) {
            	   FileInputStream input = null;
				   String road = "resources/images/Ground.png";
				   String grass = "resources/images/Grass.png";
				   char tile = line.charAt(i);
				   if (tile == '*') {
					   path[k][i] = tile;
            		   input = new FileInputStream(grass);
            		   Image image = new Image(input); 
            		   gc.drawImage(image, i * Entity.DEFAULT_WIDTH, 
            				   k * Entity.DEFAULT_HEIGHT);
            	   } else if (line.charAt(i) == '-') {
            		   path[k][i] = tile;
            		   input = new FileInputStream(road);
            		   Image image = new Image(input); 
            		   gc.drawImage(image, i * Entity.DEFAULT_WIDTH,
            				   k * Entity.DEFAULT_HEIGHT);
            	   }
               }
               k++;
            }
            in.close();
		} catch (FileNotFoundException | NullPointerException e){
			System.out.println("File not found or file does not fit format"); // change later 
		}
		root.setCenter(canvas);
		return canvas.getGraphicsContext2D();
	}

}
