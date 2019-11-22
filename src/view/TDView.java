package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import controller.TDController;
import controller.TDTowerEconomyController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Entity;
import model.Player;
import model.Tower;

public class TDView extends Application {
	
	private TDController mainController;
	private TDTowerEconomyController ecoController; 
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
		Player player = new Player();
		this.mainController = new TDController(player);
		this.ecoController = new TDTowerEconomyController(player);
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
		// Instantiate event handler.
		MouseClickedOnCanvas MouseClickedOnCanvasHandler = new MouseClickedOnCanvas();
		canvas.setOnMouseClicked(MouseClickedOnCanvasHandler); // Associate Canvas with the named EventHandler
		canvas.setDisable(true);
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

	/**
	 * The MouseClickedOnCanvas class is the event handler for the 
	 * canvas setOnMouseClicked.
	 */
	class MouseClickedOnCanvas implements EventHandler<MouseEvent>  
	{
		/**
		 * The handle method handles the event for when 
		 * 
		 * @param event The MouseEvent object.
		 */
		@Override
		public void handle(MouseEvent event) 
		{	
			//System.out.println(event.getSceneX());
	        //System.out.println(event.getSceneY());
			int row = (int) ((event.getSceneY() / Entity.DEFAULT_HEIGHT) % rows);
			int col = (int) ((event.getSceneX() / Entity.DEFAULT_WIDTH) % columns);
			
			if (path[row][col] == '*')
			{
				System.out.println("valid");
				
				ecoController.makePurchase(some_tower); // need connection to tower
				mainController.addTower(some_tower); // need connection to tower
			}
			else
			{
				System.out.println("NOPE"); // else for testing
			}// end if
			
		}// end handle
	}// end class MouseClickedOnCanvas
}
