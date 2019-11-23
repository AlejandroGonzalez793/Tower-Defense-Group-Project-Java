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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
	
	private Tower selectedTower;
	private GridPane towerPane;
	
	private static final char FREE_CHAR = '*';
	private static final char ROAD_CHAR = '-';
	private static final int TOWER_ROWS = 2;
	private static final String IMAGE_PATH = "resources/images/";
	private static final String TOWER_IMAGE_PATH = "resources/images/towers/";
	private static final int PIXEL_LEEWAY = 5;

	
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
	
	
	private ImageView getImage(String pic) {
		FileInputStream in = null;
		try {
			in = new FileInputStream(IMAGE_PATH + pic);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Image towerImage = new Image(in);
		return new ImageView(towerImage);
	}
	
	private ImageView getTowerImage(String pic) {
		FileInputStream in = null;
		try {
			in = new FileInputStream(TOWER_IMAGE_PATH + pic);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Image towerImage = new Image(in);
		return new ImageView(towerImage);
	}
	
	public void createLayout() {
		towerPane = new GridPane();
		towerPane.setVgap(5);
		towerPane.setHgap(5);
		List<Tower> towers = mainController.getAllTowers();
		
		int i = 0;
		int j = 0;
		for (Tower tower : towers) {
			Button button = new Button();
			button.setOnAction(new TowerButton(tower));
			button.setGraphic(getTowerImage(tower.getImageName()));
			towerPane.add(button, j, i);
			
			i++;
			j++;
			
			if (j == TOWER_ROWS) {
				j = 0;
			}
		}
		
		root.setRight(towerPane);
		
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
				   char tile = line.charAt(i);
				   if (tile == FREE_CHAR) {
					   path[k][i] = tile;
            		   input = new FileInputStream(IMAGE_PATH + "Grass.png");
            		   Image image = new Image(input); 
            		   gc.drawImage(image, i * Entity.DEFAULT_WIDTH, 
            				   k * Entity.DEFAULT_HEIGHT);
            	   } else if (line.charAt(i) == ROAD_CHAR) {
            		   path[k][i] = tile;
            		   input = new FileInputStream(IMAGE_PATH + "Ground.png");
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
	class MouseClickedOnCanvas implements EventHandler<MouseEvent> {
		/**
		 * The handle method handles the event for when the user clicks on the
		 * map to place a tower.
		 * 
		 * @param event The MouseEvent object.
		 */
		@Override
		public void handle(MouseEvent event) {	
			int rowTopLeft = (int) (((event.getY()+((selectedTower.getWidth()/2)-PIXEL_LEEWAY)) / Entity.DEFAULT_HEIGHT) % rows);
			int colTopLeft = (int) (((event.getX()-((selectedTower.getHeight()/2)-PIXEL_LEEWAY)) / Entity.DEFAULT_WIDTH) % columns);
			
			int rowTopRight = (int) (((event.getY()+((selectedTower.getWidth()/2)-PIXEL_LEEWAY)) / Entity.DEFAULT_HEIGHT) % rows);
			int colTopRight = (int) (((event.getX()+((selectedTower.getHeight()/2)-PIXEL_LEEWAY)) / Entity.DEFAULT_WIDTH) % columns);
			
			int rowBottomLeft = (int) (((event.getY()-((selectedTower.getWidth()/2)-PIXEL_LEEWAY)) / Entity.DEFAULT_HEIGHT) % rows);
			int colBottomLeft = (int) (((event.getX()-((selectedTower.getHeight()/2)-PIXEL_LEEWAY)) / Entity.DEFAULT_WIDTH) % columns);
			
			int rowBottomRight = (int) (((event.getY()-((selectedTower.getWidth()/2)-PIXEL_LEEWAY)) / Entity.DEFAULT_HEIGHT) % rows);
			int colBottomRight = (int) (((event.getX()+((selectedTower.getHeight()/2)-PIXEL_LEEWAY)) / Entity.DEFAULT_WIDTH) % columns);
			
			System.out.println(event.getX());
			System.out.println(event.getY());
			
			if (path[rowTopLeft][colTopLeft] == FREE_CHAR && path[rowTopRight][colTopRight] == FREE_CHAR
					&& path[rowBottomLeft][colBottomLeft] == FREE_CHAR && path[rowBottomRight][colBottomRight] == FREE_CHAR 
					&& (event.getX()+((selectedTower.getHeight()/2)-PIXEL_LEEWAY)) < canvas.getWidth()
					&& (event.getY()+((selectedTower.getWidth()/2)-PIXEL_LEEWAY)) < canvas.getHeight()
					&& (event.getX()-((selectedTower.getHeight()/2)-PIXEL_LEEWAY)) > 0
					&& (event.getY()-((selectedTower.getWidth()/2)-PIXEL_LEEWAY)) > 0) {
				System.out.println("valid");
				
				FileInputStream input = null;
				try {
					input = new FileInputStream(TOWER_IMAGE_PATH + selectedTower.getImageName());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
      		    try {
      		    	if (input != null) {
    	      		    Image image = new Image(input);
    	      		    gc.drawImage(image, event.getX() - 25, event.getY() - 25);
    					input.close();
    				}
				} catch (IOException e) {
					e.printStackTrace();
				}
			
      		    ecoController.makePurchase(selectedTower);
				mainController.setTowerCoordinates(selectedTower, (int)event.getX() - 25, (int)event.getY() - 25);
				mainController.addTower(selectedTower);
      		    selectedTower = null;
      		    towerPane.setDisable(false);
				canvas.setDisable(true);
			} else {
				System.out.println("NOPE"); // else for testing
			}
		}
	}
	
	/**
	 * The TowerOneButton class is the event handler class that will
	 * check if the player can buy tower1, then they can place it on the map.
	 * If they can't buy the tower, then they won't be able to place anything.
	 */
	class TowerButton implements EventHandler<ActionEvent> {
		private Tower tower;
		
		public TowerButton(Tower tower) {
			this.tower = tower;
		}
		
		/**
		 * The handle method handles the event for when the Tower 1 button is pressed.
		 * 
		 * @param e The ActionEvent object.
		 */
		public void handle(ActionEvent e) {		
			selectedTower = tower;
			if (ecoController.validPurchase(tower)) {
				towerPane.setDisable(true);
				canvas.setDisable(false);
			} else {
				System.out.println("Can't buy tower");
			}
		}
	}
}
