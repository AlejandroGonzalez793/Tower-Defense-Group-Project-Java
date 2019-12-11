package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
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
import javafx.scene.Node;
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
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.GameState;
import model.Player;
import model.Waves;
import model.enemies.Enemy;
import model.projectiles.Projectile;
import model.towers.Tower;
import util.ResourceManager;


/**
 * This class represents the view of our program. 
 * 
 * The view uses JavaFX to display our Tower defense game. The view
 * contains multiple windows for the main main, game window, and other
 * window pop ups. The view reads in a user designed text file that 
 * constructs our map to play tower defense. The view updates every time 
 * the tick method in the model set changes to it's observers. 
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu)
 */
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
	private TDStageComplete gameOverWindow;
	private TDmicrotransaction microtransMenu;
	private String mapFileName;
	private MediaPlayer player;

	private Text money;
	private Text health;
	private Label roundLabel;
	private GridPane towerPane;

	private Button sellButton;
	private Boolean sellingTowers = false;

	private Button newWaveButton;

	private static final String IMAGE_PATH = "resources/images/";
	public static final String MAP_PATH = "resources/maps/";
	private static final int TOWER_ROWS = 3;
	private static final String START_CHAR = "+";
	private static final String END_CHAR = "=";
	private static final String ROAD_CHAR = "-";
	private static final String DEAD_CHAR = "d";

	/**
	 * This method is the start method that initializes our view once the 
	 * main class calls starts the application. 
	 * 
	 * @param primaryStage This is a Stage object that will contain our view.
	 */
	@Override
	public void start(Stage primaryStage) {
		ResourceManager.loadImages();
		ResourceManager.loadAudio();
		
		this.primaryStage = primaryStage;
		this.controller = new TDController(new Player(this), new GameState(this));

		mainMenu = new TDMainMenu(this, controller);
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

		// These our all our preset maps in the main menu
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
			FileChooser.ExtensionFilter mapFilter = new ExtensionFilter("Map Files (*.td)", "*.td");
			FileChooser.ExtensionFilter allFilter = new ExtensionFilter("All Files", "*.*");
			fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
			fileChooser.getExtensionFilters().addAll(mapFilter, allFilter);
			File file = fileChooser.showOpenDialog(primaryStage);
			if (file != null) {
				mapFileName = file.getPath();
				controller.stop();
				newGame();
			}
		});

		menu.getItems().addAll(stageOneItem, stageTwoItem, stageThreeItem, selectMapItem);
		menuBar.getMenus().add(menu);

		root.setTop(menuBar);

		primaryStage.setTitle("Tower Defense");
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		newGame();
	}

	/**
	 * This method update our view each time the observable is changed in the model.
	 * 
	 * Once a projectile hits and enemy or goes outside the tower radius, the 
	 * projectile will decrement the enemies health and then have the bullet removed
	 * from the canvas. It also checks if the game is over, if so then a pop up window
	 * appears and the user is redirected to the main menu.
	 * 
	 * @param o This is a Observable object
	 * @param arg This is a Object that contains either the gamestate object or the 
	 * player object.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof GameState) {
			GameState gameState = (GameState) arg;
			drawingGC.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());

			for (Tower tower : gameState.getTowers()) {
				drawingGC.drawImage(tower.getImage(), tower.getX(), tower.getY(), tower.getWidth(), tower.getHeight());
			}
			
			Iterator<Enemy> enemyIter = gameState.getEnemies().iterator();
			while (enemyIter.hasNext()) {
				Enemy enemy = enemyIter.next();
				if (enemy.getHealth() <= 0) {
					controller.addGold(enemy.getGold());
					enemyIter.remove();
				} else {
					drawingGC.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), enemy.getWidth(),
							enemy.getHeight());
				}
			}

			Iterator<Projectile> bulletIter = gameState.getProjectiles().iterator();
			while (bulletIter.hasNext()) {
				Projectile bullet = bulletIter.next();
				if (controller.checkBulletCollision(bullet)) {
					bulletIter.remove();
				} else if (bullet.getDistance() >= bullet.getRadius()) {
					bulletIter.remove();
				} else {
					drawingGC.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), bullet.getWidth(),
							bullet.getHeight());
				}
				bullet.setDistance();
			}
		} else if (arg instanceof Player) {
			Player player = (Player) arg;

			int playerHealth = Math.max(0, player.getHealth());
			health.setText(Integer.toString(playerHealth));
			money.setText(Integer.toString(player.getMoney()));
		}
		
		if (controller.isPlayerDead()) {
			if (!controller.getIsPlaying()) {
				return;
			}
			
			controller.stop();
			stopMusic();
			towerPane.setDisable(true);
			gameOverWindow = new TDStageComplete();
			gameOverWindow.setTitle("Game Over");
			gameOverWindow.initModality(Modality.APPLICATION_MODAL);
			gameOverWindow.setResizable(false);
			gameOverWindow.setLabel("You have lost...");
			gameOverWindow.getContinueBtn().setOnAction(e -> {
				primaryStage.hide();
				mainMenu.playMenuMusic();
				mainMenu.show();
				Node source = (Node) e.getSource();
				Stage stage = (Stage) source.getScene().getWindow();
				stage.close();
			});
			gameOverWindow.show();
			return;
		}

		// if new round is true, set the wave button to be pressable
		if (controller.isNewRound()) {
			newWaveButton.setDisable(false);
			roundLabel.setText("Rounds: " + controller.getWaveNumber() + "/" + Waves.MAX_WAVES);
		}

		// when the stage is completed, show victory screen and return to main menu
		if (controller.isGameOver()) {
			controller.stop();
			stopMusic();
			gameOverWindow = new TDStageComplete();
			gameOverWindow.setTitle("Stage Complete!");
			gameOverWindow.initModality(Modality.APPLICATION_MODAL);
			gameOverWindow.setResizable(false);
			gameOverWindow.setLabel("You Won!");
			gameOverWindow.getContinueBtn().setOnAction(e -> {
				primaryStage.hide();
				mainMenu.playMenuMusic();
				mainMenu.show();
				Node source = (Node) e.getSource();
				Stage stage = (Stage) source.getScene().getWindow();
				stage.close();
			});
			gameOverWindow.show();
		}
	}

	/**
	 * This method initializes a new game for the user once he or she has just started
	 * the game. 
	 * 
	 * The layout and map of the game is reseted to whatever map the user has chosen or 
	 * goes to the default map when the user clicks start.
	 */
	public void newGame() {
		this.controller = new TDController(new Player(this), new GameState(this));
		createMap();
		createLayout();

		gamePane.setOnMouseClicked(e -> {
            if (!sellingTowers) {
            	int height = (int)drawingCanvas.getHeight();
            	int width = (int)drawingCanvas.getWidth();
                if (controller.canPlaceTower((int)e.getX(), (int)e.getY(), height, width)) {
				    controller.addTower((int) e.getX(), (int) e.getY());
				    towerPane.setDisable(false);
                    gamePane.setDisable(true);
                    gamePane.setCursor(Cursor.DEFAULT);
                }
            } else {
            	controller.sellTower((int) e.getX(), (int) e.getY());
            }
        });
		gamePane.setDisable(true);

		primaryStage.setMinHeight(100);
		primaryStage.setMinWidth(100);
		primaryStage.sizeToScene();
		primaryStage.centerOnScreen();
		primaryStage.show();
		stopMusic();
		getTrack();
	}

	/**
	 * This method creates our entire map.
	 * 
	 * The map will contain a set path that will be a linked list of nodes
	 * that contain a rectangle object. The free spaces for the user to set 
	 * done towers are denoted as "free spaces" with green grass with the road
	 * being "road spaces" that will have some stone road image. Then the dead
	 * zones on the map appear as rocks on the map.
	 */
	public void createMap() {
		backgroundCanvas = new Canvas();
		drawingCanvas = new Canvas();
		gamePane = new Pane(backgroundCanvas, drawingCanvas);
		backgroundGC = backgroundCanvas.getGraphicsContext2D();
		drawingGC = drawingCanvas.getGraphicsContext2D();

		Scanner input = null;
		Image grass = null;
		Image road = null;
		Image rock = null;
		try {
			input = new Scanner(new File(mapFileName));
			grass = new Image(new FileInputStream(IMAGE_PATH + "Grass.png"));
			rock = new Image(new FileInputStream(IMAGE_PATH + "DeadZone.png"));
			road = new Image(new FileInputStream(IMAGE_PATH + "Road.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}

		int cols;
		int rows;
		try {
			cols = input.nextInt();
			rows = input.nextInt();
			backgroundCanvas.setWidth(cols * grass.getWidth());
			backgroundCanvas.setHeight(rows * grass.getHeight());
			input.nextLine(); // consume newline
		} catch (NoSuchElementException e) {
			System.err.println("Invalid map format");
			if (input != null)
				input.close();
			return;
		}

		drawingCanvas.setWidth(backgroundCanvas.getWidth());
		drawingCanvas.setHeight(backgroundCanvas.getHeight());

		int currRow = 0;
		int currCol = 0;
		int row = 0;
		String[][] tempBoard = new String[rows][cols];
		while (input.hasNextLine()) {
			String line = input.nextLine();
			tempBoard[row] = line.split("");
			for (int col = 0; col < tempBoard[row].length; col++) {
				
				if (tempBoard[row][col].equals(START_CHAR)) {
					currRow = row;
					currCol = col;
				} else if (tempBoard[row][col].equals(DEAD_CHAR)) {
					this.controller.addDeadzone(new Rectangle(col * 50, row * 50, 50, 50));
					backgroundGC.drawImage(rock, col * 50, row * 50, 50 ,50);
				} else {
					backgroundGC.drawImage(grass, col * grass.getWidth(), row * grass.getHeight());
				}
				
			}
			row++;
		}

		// Get path by starting at the start position and checking the surrounding
		// positions for another road tile. Once one is found, move to that new
		// position and repeat until the end of the path is found
		String curr = tempBoard[currRow][currCol];
		while (!curr.equals(END_CHAR)) {
			backgroundGC.drawImage(road, currCol * grass.getWidth(), currRow * grass.getHeight());
			controller.addPathTile((int) (currCol * road.getWidth()), (int) (currRow * road.getHeight()),
					(int) road.getWidth(), (int) road.getHeight());

			tempBoard[currRow][currCol] = "x";
			if (currRow + 1 < rows && (tempBoard[currRow + 1][currCol].equals(ROAD_CHAR)
					|| tempBoard[currRow + 1][currCol].equals(END_CHAR))) {
				currRow = currRow + 1;
			} else if (currCol + 1 < cols && (tempBoard[currRow][currCol + 1].equals(ROAD_CHAR)
					|| tempBoard[currRow][currCol + 1].equals(END_CHAR))) {
				currCol = currCol + 1;
			} else if (currRow - 1 >= 0 && (tempBoard[currRow - 1][currCol].equals(ROAD_CHAR)
					|| tempBoard[currRow - 1][currCol].equals(END_CHAR))) {
				currRow = currRow - 1;
			} else if (currCol - 1 >= 0 && (tempBoard[currRow][currCol - 1].equals(ROAD_CHAR)
					|| tempBoard[currRow][currCol - 1].equals(END_CHAR))) {
				currCol = currCol - 1;
			}
			curr = tempBoard[currRow][currCol];
		}

		// Add end tile
		backgroundGC.drawImage(road, currCol * grass.getWidth(), currRow * grass.getHeight());
		controller.addPathTile((int) (currCol * road.getWidth()), (int) (currRow * road.getHeight()),
				(int) road.getWidth(), (int) road.getHeight());

		input.close();
		root.setCenter(gamePane);
	}

	/**
	 * This method creates our layout of towers and other buttons on the map.
	 * 
	 * There are eight towers added to the right of the border pane along with
	 * the gold, round, and health added to the top of them. We also include 
	 * multiple buttons for the speed of the rounds, next wave, music sound, 
	 * stop music, and more, which are all added to the right side of the border
	 * pane below the towers.
	 */
	public void createLayout() {
		// Create side bar
		BorderPane sidebarPane = new BorderPane();
		towerPane = new GridPane();
		towerPane.setPadding(new Insets(5, 5, 5, 5));
		towerPane.setVgap(5);
		towerPane.setHgap(5);
		Map<String, Image> towerImageMap = controller.getTowerImageMap();

		// These are the towers being set on the border pane
		int i = 0;
		int j = 0;
		for (Map.Entry<String, Image> entry : towerImageMap.entrySet()) {

			VBox towerBox = new VBox();

			Button button = new Button();
			button.setOnAction(new TowerButton(entry.getKey()));
			ImageView imageView = new ImageView(entry.getValue());
			imageView.setFitWidth(50);
			imageView.setFitHeight(50);
			button.setGraphic(imageView);

			Label towerName = new Label(entry.getKey());
			Label towerPrice = new Label("Cost: " + Integer.toString(controller.getTowerCost(entry.getKey())));

			towerBox.getChildren().addAll(button, towerName, towerPrice);

			towerPane.add(towerBox, j, i);

			j++;
			if (j == TOWER_ROWS) {
				j = 0;
				i++;
			}
		}

		BorderPane statsPane = new BorderPane();
		
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
		
		roundLabel = new Label("Rounds: 0/" + Waves.MAX_WAVES);
		
		statsPane.setLeft(statsBox);
		statsPane.setRight(roundLabel);

		VBox controlBox = new VBox();
		controlBox.setSpacing(5);

		HBox gameSpeedBox = new HBox();
		Button slowButton = new Button("0.5x");
		slowButton.setOnAction(e -> {
			controller.slowDown();
		});

		Button normalButton = new Button("1x");
		normalButton.setOnAction(e -> {
			controller.regularSpeed();
		});

		Button fastButton = new Button("2x");
		fastButton.setOnAction(e -> {
			controller.speedUp();
		});

		Button pauseButton = new Button("Pause Game");
		pauseButton.setOnAction(e -> {
			controller.pause();
			if (controller.getIsPlaying()) {
				pauseButton.setText("Pause Game");
			} else {
				pauseButton.setText("Play Game");
			}
		});

		Button pauseMusicButton = new Button("Pause Music");
		pauseMusicButton.setOnAction(e -> {
			if (player.getStatus().equals(Status.PLAYING)) {
				player.pause();
				pauseMusicButton.setText("Play Music");
			} else {
				player.play();
				pauseMusicButton.setText("Pause Music");
			}
		});

		gameSpeedBox.setSpacing(5);
		gameSpeedBox.getChildren().addAll(slowButton, normalButton, fastButton, pauseButton);

		HBox towerPurchaseBox = new HBox();
		sellButton = new Button("Sell Towers");
		sellButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { // This allows towers to be sold and removed
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

		Button microButton = new Button("Get More Towers!");
		microButton.setOnAction(e -> { // This opens the microtransaction window
			microtransMenu = new TDmicrotransaction();
			microtransMenu.setTitle("MisurdaSoft");
			microtransMenu.initModality(Modality.APPLICATION_MODAL);
			microtransMenu.setResizable(false);
			microtransMenu.showAndWait();

			if (microtransMenu.isBought()) {
				controller.addGold(microtransMenu.getBoughtGold());
			}
		});

		towerPurchaseBox.setSpacing(5);
		towerPurchaseBox.getChildren().addAll(sellButton, microButton);

		HBox waveBox = new HBox();
		newWaveButton = new Button("New Wave");
		newWaveButton.setOnAction(e -> { // Next wave
			controller.nextWave();
			newWaveButton.setDisable(true);
		});

		Slider slider = new Slider(0, 100, 50);
		slider.valueProperty().addListener(e -> {
			player.setVolume(slider.getValue() / 100);
		});
		waveBox.setSpacing(5);
		waveBox.getChildren().addAll(newWaveButton, pauseMusicButton, slider);

		controlBox.getChildren().addAll(towerPurchaseBox, gameSpeedBox, waveBox);

		sidebarPane.setTop(statsPane);
		sidebarPane.setCenter(towerPane);
		sidebarPane.setBottom(controlBox);

		root.setRight(sidebarPane);
	}

	/**
	 * This method gets the according music to the map generated
	 * when the user selects a map.
	 */
	public void getTrack() {
		Media pick;
		if (mapFileName.endsWith("map1.td")) {
			pick = ResourceManager.getAudio("map1");
		} else if (mapFileName.endsWith("map2.td")) {
			pick = ResourceManager.getAudio("map2");
		} else if (mapFileName.endsWith("map3.td")) {
			pick = ResourceManager.getAudio("map3");
		} else {
			pick = ResourceManager.getAudio("default");
		}
		player = new MediaPlayer(pick);
		player.setOnEndOfMedia(() -> {
			player.seek(Duration.ZERO);
			player.play();
		});

		player.setVolume(0.5);
		player.play();
	}

	/**
	 * This method stops the music being played.
	 */
	public void stopMusic() {
		if (player != null) {
			player.stop();
			player = null;
		}
	}

	/**
	 * This method sets the map file to be read so the map can be constructed
	 * 
	 * @param mapFileName This the map file path
	 */
	public void setMapFileName(String mapFileName) {
		this.mapFileName = mapFileName;
	}

	/**
	 * The TowerButton class is the event handler class that will check if the
	 * player can buy a tower, then they can place it on the map. If they can't buy
	 * the tower, then they won't be able to place anything.
	 */
	private class TowerButton implements EventHandler<ActionEvent> {
		private String tower;

		/**
		 * This is the tower button constructor. 
		 * 
		 * @param tower This is a string object
		 */
		public TowerButton(String tower) {
			this.tower = tower;
		}

		/**
		 * The handle method handles the event for when the tower button is clicked. It
		 * sets the selected tower to the current tower if the player can buy it.
		 * 
		 * @param e The ActionEvent object.
		 */
		public void handle(ActionEvent e) {
			if (controller.canPurchaseTower(this.tower)) {
				Image image = controller.getSelectedTowerImage();
				gamePane.setCursor(new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2));
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

	/**
	 * This private class is a event handler that constructs a new map that 
	 * the user selects and gets the according text file to generate that 
	 * map.
	 */
	private class StageButton implements EventHandler<ActionEvent> {
		private String mapFile;

		/**
		 * This constructor is the stage button.
		 * 
		 * @param mapFile This is a map file path
		 */
		public StageButton(String mapFile) {
			this.mapFile = mapFile;
		}

		/**
		 * This method is the handler method that set s the map file path,
		 * stops the music, gets the music track for that map, and then 
		 * generates that map.
		 */
		public void handle(ActionEvent e) {
			mapFileName = MAP_PATH + mapFile;
			controller.stop();
			stopMusic();
			getTrack();
			newGame();
		}
	}
}
