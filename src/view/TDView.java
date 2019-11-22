package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import controller.TDController;
import controller.TDTowerEconomyController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Entity;
import model.Player;
import model.Tower;


public class TDView extends Application {
	
	private TDController mainController;
	private TDTowerEconomyController ecoController; 
	private BorderPane root;
	private Canvas canvas;
	private Scene scene;
	private GraphicsContext gc;
	private char[][] path;
	private int rows;
	private int columns;
	
	private Tower towerObject;
	private Button tower1;
	private Button tower2;
	private Button tower3;
	private Button tower4;
	private Button tower5;
	private Button tower6;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Application.Parameters params = this.getParameters(); 
		List<String> rawParams = params.getRaw();
		gc = createMap(rawParams);
		Player player = new Player();
		this.mainController = new TDController(player);
		this.ecoController = new TDTowerEconomyController(player);
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
		
		tower1 = new Button("tower 1");
		tower1.setGraphic(setImage("tower1.png"));
		box.getChildren().add(tower1);
		// Instantiate event handler.
		TowerOneButton tower1BtnHandler = new TowerOneButton();
		tower1.setOnAction(tower1BtnHandler); // Associate tower button with the named EventHandler
		
		tower2 = new Button("Tower 2");	
		tower2.setGraphic(setImage("tower1.png"));
		box.getChildren().add(tower2);
		// Instantiate event handler.
		TowerTwoButton tower2BtnHandler = new TowerTwoButton();
		tower2.setOnAction(tower2BtnHandler); // Associate tower button with the named EventHandler	
		
		tower3 = new Button("Tower 3");	
		tower3.setGraphic(setImage("tower1.png"));
		box.getChildren().add(tower3);
		// Instantiate event handler.
		TowerThreeButton tower3BtnHandler = new TowerThreeButton();
		tower3.setOnAction(tower3BtnHandler); // Associate tower button with the named EventHandler	
		
		tower4 = new Button("Tower 4");
		tower4.setGraphic(setImage("tower1.png"));
		box.getChildren().add(tower4);
		// Instantiate event handler.
		TowerFourButton tower4BtnHandler = new TowerFourButton();
		tower4.setOnAction(tower4BtnHandler); // Associate tower button with the named EventHandler		
		
		tower5 = new Button("Tower 5");		
		tower5.setGraphic(setImage("tower1.png"));
		box.getChildren().add(tower5);
		// Instantiate event handler.
		TowerFiveButton tower5BtnHandler = new TowerFiveButton();
		tower5.setOnAction(tower5BtnHandler); // Associate tower button with the named EventHandler	
		
		tower6 = new Button("Tower 6");		
		tower6.setGraphic(setImage("tower1.png"));
		box.getChildren().add(tower6);
		// Instantiate event handler.
		TowerSixButton tower6BtnHandler = new TowerSixButton();
		tower6.setOnAction(tower6BtnHandler); // Associate tower button with the named EventHandler		
		
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
		canvas = new Canvas();
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
		 * The handle method handles the event for when the user clicks on the
		 * map to place a tower.
		 * 
		 * @param event The MouseEvent object.
		 */
		@Override
		public void handle(MouseEvent event) 
		{	
			//System.out.println(event.getSceneX());
	        //System.out.println(event.getSceneY());
			int row = (int) ((event.getY() / Entity.DEFAULT_HEIGHT) % rows);
			int col = (int) ((event.getX() / Entity.DEFAULT_WIDTH) % columns);
			
			if (path[row][col] == '*')
			{
				System.out.println("valid");
				
				//ecoController.makePurchase(some_tower);
				try {
				FileInputStream input = new FileInputStream("resources/images/tower1.png");
      		    Image image = new Image(input); 
      		    gc.drawImage(image, event.getX() - 25, event.getY() - 25);
      		    System.out.println(event.getX());
      		    input.close();
				} catch (NullPointerException | IOException e){
					System.out.println("File not found or file does not fit format"); // change later 
				}// end try catch
      			  
				mainController.setTowerCoordinates(towerObject, (int)event.getX() - 25, (int)event.getY() - 25);
      		    mainController.addTower(towerObject);
      		    towerObject = null;
      		    System.gc();
      		    tower1.setDisable(false);
      		    tower2.setDisable(false);
      		    tower3.setDisable(false);
      		    tower4.setDisable(false);
      		    tower5.setDisable(false);
      		    tower6.setDisable(false);
				canvas.setDisable(true);
			}
			else
			{
				System.out.println("NOPE"); // else for testing
			}// end if
			
			// need some way to find the pixal's of the map 
			// so that the towers can be placed properly. 
			//PixelReader pixelReader = canvas.getPixelReader();
			
		}// end handle
	}// end class MouseClickedOnCanvas
	
	/**
	 * The TowerOneButton class is the event handler class that will
	 * check if the player can buy tower1, then they can place it on the map.
	 * If they can't buy the tower, then they won't be able to place anything.
	 */
	class TowerOneButton implements EventHandler<ActionEvent>
	{
		/**
		 * The handle method handles the event for when the Tower 1 button is pressed.
		 * 
		 * @param e The ActionEvent object.
		 */
		public void handle(ActionEvent e) 
		{
			//System.out.println("\nCLICK\n"); //used to test
			towerObject = mainController.getCheapTower();
			
			if (ecoController.makePurchase(towerObject))
			{
				// here would be a way to reference the tower object
				// so that the mouse handler would know what tower to place
				tower1.setDisable(true);
				tower2.setDisable(true);
				tower3.setDisable(true);
				tower4.setDisable(true);
				tower5.setDisable(true);
				tower6.setDisable(true);
				canvas.setDisable(false);
				System.out.println("Bought Tower 1");
			}
			else
			{
				// do something like flash red on box
				System.out.println("Can't buy tower1");
			}// end if
		}// end handle
	}// end TowerOneButton class
	
	/**
	 * The TowerTwoButton class is the event handler class that will
	 * check if the player can buy tower2, then they can place it on the map.
	 * If they can't buy the tower, then they won't be able to place anything.
	 */
	class TowerTwoButton implements EventHandler<ActionEvent>
	{
		/**
		 * The handle method handles the event for when the Tower 2 button is pressed.
		 * 
		 * @param e The ActionEvent object.
		 */
		public void handle(ActionEvent e) 
		{
			//System.out.println("\nCLICK\n"); //used to test
			/*
			if (ecoController.makePurchase(tower2))
			{
				// here would be a way to reference the tower object
				// so that the mouse handler would know what tower to place
				canvas.setDisable(false);
			}
			else
			{
				// do something like flash red on box
				System.out.println("Can't buy tower2");
			}// end if
			*/
		}// end handle
	}// end TowerTwoButton class
	
	/**
	 * The TowerThreeButton class is the event handler class that will
	 * check if the player can buy tower3, then they can place it on the map.
	 * If they can't buy the tower, then they won't be able to place anything.
	 */
	class TowerThreeButton implements EventHandler<ActionEvent>
	{
		/**
		 * The handle method handles the event for when the Tower 3 button is pressed.
		 * 
		 * @param e The ActionEvent object.
		 */
		public void handle(ActionEvent e) 
		{
			//System.out.println("\nCLICK\n"); //used to test
			/*
			if (ecoController.makePurchase(tower3))
			{
				// here would be a way to reference the tower object
				// so that the mouse handler would know what tower to place
				canvas.setDisable(false);
			}
			else
			{
				// do something like flash red on box
				System.out.println("Can't buy tower3");
			}// end if
			*/
		}// end handle
	}// end TowerThreeButton class
	
	/**
	 * The TowerFourButton class is the event handler class that will
	 * check if the player can buy tower4, then they can place it on the map.
	 * If they can't buy the tower, then they won't be able to place anything.
	 */
	class TowerFourButton implements EventHandler<ActionEvent>
	{
		/**
		 * The handle method handles the event for when the Tower 4 button is pressed.
		 * 
		 * @param e The ActionEvent object.
		 */
		public void handle(ActionEvent e) 
		{
			//System.out.println("\nCLICK\n"); //used to test
			/*
			if (ecoController.makePurchase(tower4))
			{
				// here would be a way to reference the tower object
				// so that the mouse handler would know what tower to place
				canvas.setDisable(false);
			}
			else
			{
				// do something like flash red on box
				System.out.println("Can't buy tower4");
			}// end if
			*/
		}// end handle
	}// end TowerFourButton class
	
	/**
	 * The TowerFiveButton class is the event handler class that will
	 * check if the player can buy tower5, then they can place it on the map.
	 * If they can't buy the tower, then they won't be able to place anything.
	 */
	class TowerFiveButton implements EventHandler<ActionEvent>
	{
		/**
		 * The handle method handles the event for when the Tower 5 button is pressed.
		 * 
		 * @param e The ActionEvent object.
		 */
		public void handle(ActionEvent e) 
		{
			//System.out.println("\nCLICK\n"); //used to test
			/*
			if (ecoController.makePurchase(tower5))
			{
				// here would be a way to reference the tower object
				// so that the mouse handler would know what tower to place
				canvas.setDisable(false);
			}
			else
			{
				// do something like flash red on box
				System.out.println("Can't buy tower5");
			}// end if
			*/
		}// end handle	
	}// end TowerFiveButton class
	
	/**
	 * The TowerSixButton class is the event handler class that will
	 * check if the player can buy tower6, then they can place it on the map.
	 * If they can't buy the tower, then they won't be able to place anything.
	 */
	class TowerSixButton implements EventHandler<ActionEvent>
	{
		/**
		 * The handle method handles the event for when the Tower 6 button is pressed.
		 * 
		 * @param e The ActionEvent object.
		 */
		public void handle(ActionEvent e) 
		{
			//System.out.println("\nCLICK\n"); //used to test
			/*
			if (ecoController.makePurchase(tower6))
			{
				// here would be a way to reference the tower object
				// so that the mouse handler would know what tower to place
				canvas.setDisable(false);
			}
			else
			{
				// do something like flash red on box
				System.out.println("Can't buy tower6");
			}// end if
			*/
		}// end handle
	}// end TowerSixButton class
}
