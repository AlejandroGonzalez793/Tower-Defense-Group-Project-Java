package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import controller.TDController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Enemy;
import model.GameState;
import model.Player;
import model.Projectile;
import model.Tower;

public class TDView extends Application implements Observer {
	private Stage primaryStage;
	private BorderPane root;
	private Pane gamePane;
	private Canvas backgroundCanvas;
	private Canvas drawingCanvas;
	private GraphicsContext backgroundGC;
	private GraphicsContext drawingGC;
	private TDController controller;
	private TDMainMenu mainMenu;
	private String mapFileName;
	
	private Text money;
	private Text health;
	private GridPane towerPane;
	
	private Button sellButton;
	private Boolean sellingTowers = false;
	
	private static final String IMAGE_PATH = "resources/images/";
	public static final String MAP_PATH = "resources/maps/";
	private static final int TOWER_ROWS = 2;
	private static final char FREE_CHAR = '*';

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		
		mainMenu = new TDMainMenu();
		mainMenu.setTitle("Tower Defense");
		mainMenu.initModality(Modality.APPLICATION_MODAL);
		mainMenu.setResizable(false);
		mainMenu.showAndWait();
		
		if (mainMenu.getMapImage() == null) {
			System.exit(1);
		}
		
		primaryStage.setOnCloseRequest(e -> {
		    Platform.exit();
		    System.exit(0);
		});
		
		mapFileName = mainMenu.getMapImage();
		
		root = new BorderPane();
		
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Stage Select");
		MenuItem stageOneItem = new MenuItem("Stage 1");
		stageOneItem.setOnAction(new StageButton("map1.td"));
		MenuItem stageTwoItem = new MenuItem("Stage 2");
		stageTwoItem.setOnAction(new StageButton("map2.td"));
		MenuItem stageThreeItem = new MenuItem("Stage 3");
		stageThreeItem.setOnAction(new StageButton("map3.td"));
		MenuItem selectMapItem = new MenuItem("Select Map");
		selectMapItem.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Map File");
			FileChooser.ExtensionFilter mapFilter = new ExtensionFilter(
					"Map Files (*.td)", "*.td");
			FileChooser.ExtensionFilter allFilter = new ExtensionFilter(
					"All Files", "*.*");
			fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
			fileChooser.getExtensionFilters().addAll(mapFilter, allFilter);
			File file = fileChooser.showOpenDialog(primaryStage);
			if (file != null) {
				mapFileName = file.getPath();
				newGame();
			}
		});
		
		menu.getItems().addAll(stageOneItem, stageTwoItem, stageThreeItem, selectMapItem);
		menuBar.getMenus().add(menu);
		
		root.setTop(menuBar);
		
		newGame();
		primaryStage.setTitle("Tower Defense");
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof GameState) {
			GameState gameState = (GameState)arg;
			drawingGC.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
			for (Projectile proj : gameState.getProjectiles()) {
				drawingGC.drawImage(proj.getImage(), proj.getX(), proj.getY(),
						proj.getWidth(), proj.getHeight());
			}
			
			for (Enemy enemy : gameState.getEnemies()) {
				drawingGC.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), 
						enemy.getWidth(), enemy.getHeight());
			}
			
			for (Tower tower : gameState.getTowers()) {
				drawingGC.drawImage(tower.getImage(), tower.getX(), tower.getY(), 
						tower.getWidth(), tower.getHeight());
			}
			
			
		} else if (arg instanceof Player) {
			Player player = (Player)arg;
			money.setText(Integer.toString(player.getMoney()));
			health.setText(Integer.toString(player.getHealth()));
		}
	}
	
	public void newGame() {
		controller = new TDController(new Player(this), new GameState(this));
		createMap();
		createLayout();
		
		gamePane.setOnMouseClicked(e -> {
			System.out.println("X: " + e.getX());
			System.out.println("Y: " + e.getY());
			
            if (!sellingTowers) {
                if (controller.canPlaceTower((int)e.getX(), (int)e.getY())) {
				    controller.addTower((int) e.getX(), (int) e.getY());
				    towerPane.setDisable(false);
                    gamePane.setDisable(true);
                    gamePane.setCursor(Cursor.DEFAULT);
                }
            } else {
            	if (controller.sellTower((int)e.getX(), (int)e.getY())) {
            		// TODO: Do Something
            	}
            }
        });		
		gamePane.setDisable(true);
		
		primaryStage.setMinHeight(100);
		primaryStage.setMinWidth(100);
		primaryStage.sizeToScene();
		primaryStage.centerOnScreen();
	}

	public void createMap() {
		backgroundCanvas = new Canvas();
		drawingCanvas = new Canvas();
		gamePane = new Pane(backgroundCanvas, drawingCanvas);
		backgroundGC = backgroundCanvas.getGraphicsContext2D();
		drawingGC = drawingCanvas.getGraphicsContext2D();
		
		Scanner input = null;
		Image grass = null;
		Image road = null;
		try {
			input = new Scanner(new File(mapFileName));
			grass = new Image(new FileInputStream(IMAGE_PATH + "Grass.png"));
			road = new Image(new FileInputStream(IMAGE_PATH + "Road.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		} 
		
		try {
			backgroundCanvas.setWidth(input.nextInt() * grass.getWidth());
			backgroundCanvas.setHeight(input.nextInt() * grass.getHeight());
			input.nextLine();
		} catch (NoSuchElementException e) {
			System.err.println("Invalid map format");
			if (input != null) input.close();
			return;
		}
		
		drawingCanvas.setWidth(backgroundCanvas.getWidth());
		drawingCanvas.setHeight(backgroundCanvas.getHeight());
		
		int row = 0;
		while (input.hasNextLine()) {
			String line = input.nextLine();
			for (int col = 0; col < line.length(); col++) {
				char currChar = line.charAt(col);
				if (currChar == FREE_CHAR) {
					backgroundGC.drawImage(grass, col * grass.getWidth(), row * grass.getHeight());
				} else {
					backgroundGC.drawImage(road, col * grass.getWidth(), row * grass.getHeight());
					controller.addPathTile((int)(col * road.getWidth()), (int)(row * road.getHeight()), 
							(int)road.getWidth(), (int)road.getHeight());
				}
			}
			row++;
		}

		input.close();
		root.setCenter(gamePane);
	}
	
	public void createLayout() {
		// Create side bar
		BorderPane sidebarPane = new BorderPane();
		towerPane = new GridPane();
		towerPane.setPadding(new Insets(5, 5, 5, 5));
		towerPane.setVgap(5);
		towerPane.setHgap(5);
		Map<String, Image> towerImageMap = controller.getTowerImageMap();
		
		int i = 0;
		int j = 0;
		for (Map.Entry<String, Image> entry : towerImageMap.entrySet()) {
			Button button = new Button();
			button.setOnAction(new TowerButton(entry.getKey()));
			ImageView imageView = new ImageView(entry.getValue());
			imageView.setFitWidth(50);
			imageView.setFitHeight(50);
			button.setGraphic(imageView);
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
		controlBox.setSpacing(5);
		
		HBox sellBox = new HBox();
		sellButton = new Button("Sell Towers");
		sellButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!sellingTowers) {
					towerPane.setDisable(true);
					gamePane.setDisable(false);
					sellingTowers = true;
					sellButton.setText("Buy Towers");
				} else {
					towerPane.setDisable(false);
					gamePane.setDisable(true);
					sellingTowers = false;
					sellButton.setText("Sell Towers");
				}
			}
		});
		
		sellBox.getChildren().add(sellButton);
		
		HBox waveBox = new HBox();
		Button newWaveButton = new Button("New Wave >>");
		newWaveButton.setOnAction(e -> {
			controller.newWave();
		});
		waveBox.getChildren().add(newWaveButton);
		
		controlBox.getChildren().addAll(sellButton, waveBox);
		
		sidebarPane.setTop(statsBox);
		sidebarPane.setCenter(towerPane);
		sidebarPane.setBottom(controlBox);
		
		root.setRight(sidebarPane);
	}
	
	/**
	 * The TowerButton class is the event handler class that will check if the 
	 * player can buy a tower, then they can place it on the map. If they can't 
	 * buy the tower, then they won't be able to place anything.
	 */
	private class TowerButton implements EventHandler<ActionEvent> {
		private String tower;
		
		public TowerButton(String tower) {
			this.tower = tower;
		}
		
		/**
		 * The handle method handles the event for when the tower button
		 * is clicked. It sets the selected tower to the current tower if
		 * the player can buy it.
		 * 
		 * @param e The ActionEvent object.
		 */
		public void handle(ActionEvent e) {		
			if (controller.canPurchaseTower(this.tower)) {
				Image image = controller.getSelectedTowerImage();
				gamePane.setCursor(new ImageCursor(image));
				towerPane.setDisable(true);
				gamePane.setDisable(false);
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Can't buy this tower");
				alert.showAndWait();
			}
		}
	}
	
	private class StageButton implements EventHandler<ActionEvent> {
		private String mapFile;
		
		public StageButton(String mapFile) {
			this.mapFile = mapFile;
		}
		
		public void handle(ActionEvent e) {	
			mapFileName = MAP_PATH + mapFile;
			newGame();
		}
	}
}
