package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import controller.TDController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GameState;
import model.Player;
import model.Tower;

public class TDView extends Application implements Observer {
	private BorderPane root;
	private Canvas canvas;
	private GraphicsContext gc;
	private TDController controller;
	
	private Text money;
	private Text health;
	private GridPane towerPane;
	
	private static final String IMAGE_MAP_PATH = "resources/images/maps/";
	private static final int TOWER_ROWS = 2;

	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new TDController(new Player(this), new GameState(this));
		root = new BorderPane();
		canvas = new Canvas();
		createMap();
		createLayout();
		
		canvas.setOnMouseClicked(e -> {
			System.out.println("X: " + e.getX());
			System.out.println("Y: " + e.getY());
			
			if (controller.canPlaceTower((int)e.getX(), (int)e.getY())) {
				controller.addTower((int) e.getX(), (int) e.getY());
				towerPane.setDisable(false);
				canvas.setDisable(true);
			}
		});
		
		primaryStage.setTitle("Tower Defense");
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof GameState) {
			GameState gameState = (GameState)arg;
			createMap();
			for (Tower tower : gameState.getTowers()) {
				gc.drawImage(tower.getImage(), tower.getX(), tower.getY(), 
						tower.getWidth(), tower.getHeight());
			}
		} else if (arg instanceof Player) {
			Player player = (Player)arg;
			money.setText(Integer.toString(player.getMoney()));
			health.setText(Integer.toString(player.getHealth()));
		}
	}

	public void createMap() {
		FileInputStream input;
		
		gc = canvas.getGraphicsContext2D();
		try {
			canvas.setHeight(650); // will be number of pixels in background photo
			canvas.setWidth(800); // will be number of pixels in background photo
			input = new FileInputStream(IMAGE_MAP_PATH + "TDMap2.png");
			Image image = new Image(input);
			gc.drawImage(image, 0, 0, 800, 650);
		} catch (FileNotFoundException e) {
			System.out.println("Your image could not be found.");
			e.printStackTrace();
		}
		
		root.setCenter(canvas);
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
			button.setGraphic(new ImageView(entry.getValue()));
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
	 * The TowerButton class is the event handler class that will check if the 
	 * player can buy a tower, then they can place it on the map. If they can't 
	 * buy the tower, then they won't be able to place anything.
	 */
	class TowerButton implements EventHandler<ActionEvent> {
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
				towerPane.setDisable(true);
				canvas.setDisable(false);
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Can't buy this tower");
				alert.showAndWait();
			}
		}
	}
}
