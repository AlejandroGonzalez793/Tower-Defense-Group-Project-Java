package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import controller.TDController;
import controller.TDTowerEconomyController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.Entity;
import model.Player;
import model.Tower;


public class TDView extends Application implements Observer {
	
	private TDController mainController;
	private TDTowerEconomyController ecoController; 
	private BorderPane root;
	private Canvas canvas;
	private GraphicsContext gc;
	private char[][] path;
	private int rows;
	private int columns;
	
	private Text money;
	private Text health;
	
	private Tower selectedTower;
	private GridPane towerPane;
	
	private static final char FREE_CHAR = '*';
	private static final char ROAD_CHAR = '-';
	private static final char TOWER_CHAR = 't';
	private static final int TOWER_ROWS = 2;
	private static final String IMAGE_PATH = "resources/images/";
	private static final String TOWER_IMAGE_PATH = "resources/images/towers/";

	
	@Override
	public void start(Stage primaryStage) {
		Player player = new Player(this);
		this.mainController = new TDController(player);
		this.ecoController = new TDTowerEconomyController(player);
		
		root = new BorderPane();
		canvas = new Canvas();
		
		// Create menu bar
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("File");
		MenuItem newMapItem = new MenuItem("New Map");
		
		menu.getItems().add(newMapItem);
		menuBar.getMenus().add(menu);
		
		root.setTop(menuBar);
		
		newMapItem.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Map File");
			FileChooser.ExtensionFilter mapFilter = new FileChooser.ExtensionFilter("Map Files (*.td)", "*.td");
			FileChooser.ExtensionFilter allFilter = new FileChooser.ExtensionFilter("All Files", "*.*");
			fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
			fileChooser.getExtensionFilters().addAll(mapFilter, allFilter);
			File file = fileChooser.showOpenDialog(primaryStage);
			if (file != null) {
				createLayout();
				gc = createMap(file);
			}
			primaryStage.sizeToScene();
			primaryStage.centerOnScreen();
		});
		
		primaryStage.setMinHeight(100);
		primaryStage.setMinWidth(100);
		primaryStage.setTitle("Tower Defense");
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Player) {
			Player player = (Player)arg;
			money.setText(Integer.toString(player.getMoney()));
			health.setText(Integer.toString(player.getHealth()));
		}
		
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
		// Create side bar
		BorderPane sidebarPane = new BorderPane();
		towerPane = new GridPane();
		towerPane.setPadding(new Insets(5, 5, 5, 5));
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
			
			j++;
			if (j == TOWER_ROWS) {
				j = 0;
				i++;
			}
		}
		
		VBox statsBox = new VBox();
		statsBox.setSpacing(5);
		
		HBox hpBox = new HBox();
		Label hpLabel = new Label("HP: ");
		health = new Text(Integer.toString(Player.STARTING_HEALTH));
		hpBox.getChildren().addAll(hpLabel, health);
		
		HBox moneyBox = new HBox();
		Label moneyLabel = new Label("Gold: ");
		money = new Text(Integer.toString(Player.STARTING_MONEY));
		moneyBox.getChildren().addAll(moneyLabel, money);
		
		statsBox.getChildren().addAll(hpBox, moneyBox);
		
		VBox controlBox = new VBox();
		Button newWaveButton = new Button("New Wave >>");
		controlBox.getChildren().add(newWaveButton);
		
		sidebarPane.setTop(statsBox);
		sidebarPane.setCenter(towerPane);
		sidebarPane.setBottom(controlBox);
		
		root.setRight(sidebarPane);
	}
	
	/**
	 * This method createMap sets up the map that is based off from a text
	 * file. The green region indicates that a tower can be placed where the 
	 * brown or visible path shows where enemies will be traveling.
	 * @return GraphicsContext A GraphicsContext is returned that is the 
	 * GraphicsContext2D of the canvas. 
	 */
	
	public GraphicsContext createMap(File file) { 
		// Instantiate event handler.
		MouseClickedOnCanvas MouseClickedOnCanvasHandler = new MouseClickedOnCanvas();
		canvas.setOnMouseClicked(MouseClickedOnCanvasHandler); // Associate Canvas with the named EventHandler
		canvas.setDisable(true);
		try {
			Scanner in = new Scanner(file);
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
		} catch(FileNotFoundException e) {
			System.err.println("Could not load tile images");
			e.printStackTrace();
		} catch (NumberFormatException | IndexOutOfBoundsException e) {
			System.err.println("File does not fit correct format");
			e.printStackTrace();
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
			int col = (int)(event.getX() / Entity.DEFAULT_WIDTH) % columns;
			int row = (int)(event.getY() / Entity.DEFAULT_HEIGHT) % rows;
			
			int x = col * Entity.DEFAULT_WIDTH;
			int y = row * Entity.DEFAULT_HEIGHT;
			
			if (path[row][col] == FREE_CHAR) {
				FileInputStream input = null;
				try {
					input = new FileInputStream(TOWER_IMAGE_PATH + selectedTower.getImageName());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
      		    try {
      		    	if (input != null) {
    	      		    Image image = new Image(input);
    	      		    gc.drawImage(image, x, y, Entity.DEFAULT_WIDTH, Entity.DEFAULT_HEIGHT);
    					input.close();
    				}
				} catch (IOException e) {
					e.printStackTrace();
				}
			
      		    path[row][col] = TOWER_CHAR;
      		    ecoController.makePurchase(selectedTower);
				mainController.setTowerCoordinates(selectedTower, x, y);
				mainController.addTower(selectedTower);
      		    selectedTower = null;
      		    towerPane.setDisable(false);
				canvas.setDisable(true);
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
