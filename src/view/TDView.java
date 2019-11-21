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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
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
	private static final String FILE = "TDMap3";
	
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		gc = createMap();
		createLayout();
		
		scene = new Scene(root);
		primaryStage.setTitle("Tower Defense");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	
	public void createLayout() {
		VBox box = new VBox();
		box.setSpacing(10);;
		
		Button tower1 = new Button("Tower 1");
		GridPane.setConstraints(tower1, 0,0);
		box.getChildren().add(tower1);
		
		Button tower2 = new Button("Tower 2");
		GridPane.setConstraints(tower2, 0,1);
		box.getChildren().add(tower2);
		
		Button tower3 = new Button("Tower 3");
		GridPane.setConstraints(tower3, 0,2);
		box.getChildren().add(tower3);
		
		Button tower4 = new Button("Tower 4");
		GridPane.setConstraints(tower4, 0,3);
		box.getChildren().add(tower4);
		
		Button tower5 = new Button("Tower 5");
		GridPane.setConstraints(tower5, 0,4);
		box.getChildren().add(tower5);
		
		root.setRight(box);
		
		HBox hbox = new HBox();
		hbox.setSpacing(20);
		
		Label hp = new Label("HP: ");
		TextField hpText = new TextField("100");
		
		Label gold = new Label("Gold: ");
		TextField goldText = new TextField("100");
		
		hbox.getChildren().addAll(hp, hpText, gold, goldText);
		
		root.setTop(hbox);
		
		root.setBottom(new Button("New Wave >>>>"));
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
				   String road = "Road.png";
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
