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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
		createLayout();
		
		scene = new Scene(root);
		primaryStage.setTitle("Tower Defense");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	
	private ImageView setImage(String pic) {
		FileInputStream in = null;
		try {
			in = new FileInputStream("resources/images/" + pic);
		} catch (FileNotFoundException e) {
		}
		Image towerImage1 = new Image(in);
		return new ImageView(towerImage1);
	}
	
	public void createLayout() {
		VBox box = new VBox();
		box.setSpacing(10);
		
		Button tower1 = new Button("tower 1");
		tower1.setGraphic(setImage("tower1.png"));
		box.getChildren().add(tower1);
		
		Button tower2 = new Button("Tower 2");	
		tower2.setGraphic(setImage("tower1.png"));
		box.getChildren().add(tower2);
		
		Button tower3 = new Button("Tower 3");	
		tower3.setGraphic(setImage("tower1.png"));
		box.getChildren().add(tower3);
		
		Button tower4 = new Button("Tower 4");
		tower4.setGraphic(setImage("tower1.png"));
		box.getChildren().add(tower4);
		
		Button tower5 = new Button("Tower 5");		
		tower5.setGraphic(setImage("tower1.png"));
		box.getChildren().add(tower5);
		
		Button tower6 = new Button("Tower 6");		
		tower6.setGraphic(setImage("tower1.png"));
		box.getChildren().add(tower6);
		
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
