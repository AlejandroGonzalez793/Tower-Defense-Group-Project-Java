package view;

import java.awt.Event;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import controller.TDController;
import controller.TDTowerEconomyController;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Entity;
import model.Player;
import model.Tower;
import model.Enemy;


public class TDView extends Application implements Observer {
	
	private TDController mainController;
	private TDTowerEconomyController ecoController; 
	private BorderPane root;
	private Canvas canvas;
	private Scene scene;
	private GraphicsContext gc;
	private char[][] path;
	private int rows;
	private int columns;
	private List<String> portals;
	private Path loonPath;
	
	
	private Text money;
	private Text health;
	
	private Tower selectedTower;
	private GridPane towerPane;
	
	private static final char FREE_CHAR = '*';
	private static final char ROAD_CHAR = '-';
	private static final char PORTAL_CHAR = '+';
	private static final char EXIT_CHAR = '#';
	private static final char TOWER_CHAR = 't';
	private static final int TOWER_ROWS = 2;
	private static final String IMAGE_PATH = "resources/images/";
	private static final String TOWER_IMAGE_PATH = "resources/images/towers/";

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Application.Parameters params = this.getParameters(); 
		List<String> rawParams = params.getRaw();
		gc = createMap(rawParams);
		Player player = new Player(this);
		this.mainController = new TDController(player);
		this.ecoController = new TDTowerEconomyController(player);
		createLayout();
		scene = new Scene(root);
		primaryStage.setTitle("Tower Defense");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		enemy();
		balloonPath();
	      Circle circle = new Circle(); 
	      
	      //Setting the position of the circle 
	      circle.setCenterX(300.0f); 
	      circle.setCenterY(135.0f); 
	      
	      //Setting the radius of the circle 
	      circle.setRadius(25.0f); 
	      
	      //Setting the color of the circle 
	      circle.setFill(Color.BLUE); 
	      
	      //Setting the stroke width of the circle 
	      circle.setStrokeWidth(20);
	    PathTransition pathTransition = new PathTransition(); 
	      
	      //Setting the duration of the transition 
	    pathTransition.setDuration(Duration.millis(8000));       
	      
	      //Setting the node for the transition 
	    pathTransition.setNode(circle); 
	      
	      //Setting the path for the transition 
	    pathTransition.setPath(loonPath); 
	      
	      //Setting the orientation of the path 
	    pathTransition.setOrientation(
	         PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT); 
	      
	      //Setting the cycle count for the transition 
        pathTransition.setCycleCount(50); 
	      
	      //Setting auto reverse value to true 
        pathTransition.setAutoReverse(false); 
	      
	      //Playing the animation 
	    pathTransition.play();
	    root.getChildren().add(circle);
	}
	
	public void balloonPath() {
		loonPath = new Path();
		String x = portals.get(0).substring(0, portals.get(0).indexOf(','));
		String y = portals.get(0).substring(portals.get(0).indexOf(',') + 1);
		MoveTo moveTo = new MoveTo(Integer.valueOf(x), Integer.valueOf(y));
		loonPath.getElements().add(moveTo);
		int i = Integer.valueOf(x);
		int j = Integer.valueOf(y);
		int prevX = i;
		int prevY = j;
		LineTo line;
		while (path[i][j] != EXIT_CHAR) { // Check prev, bounds, and chg direction
			if ((i+1 <= rows) && (path[i+1][j] == ROAD_CHAR 
					|| path[i+1][j] == EXIT_CHAR) && 
					(((i+1) != prevX) ||  (j) != prevY)) {
				prevX = i;
				prevY = j;
				i++;
			} else if ((j+1 <= columns) && (path[i][j+1] == ROAD_CHAR
					|| path[i][j+ 1] == EXIT_CHAR) && 
					(((i) != prevX) ||  (j+1) != prevY)) {
				prevX = i;
				prevY = j;
				j++;
			} else if ((j-1 >= 0) && (path[i][j-1] == ROAD_CHAR 
					|| path[i][j-1] == EXIT_CHAR) && 
					(((i) != prevX) ||  (j-1) != prevY)) {
				prevX = i;
				prevY = j;
				j--;
			} else if ((i-1 >= 0) && (path[i-1][j] == ROAD_CHAR 
					|| path[i-1][j] == EXIT_CHAR) && 
					(((i-1) != prevX) ||  (j) != prevY)) {
				prevX = i;
				prevY = j;
				i--;
			}
			line = new LineTo(j*Entity.DEFAULT_HEIGHT, i*Entity.DEFAULT_WIDTH);
			loonPath.getElements().add(line);
			System.out.println(i);
			System.out.println(j);
		}
	}
	
	public void enemy() throws FileNotFoundException {
		List<Enemy> enemies = mainController.getAllEnemies();
		for (int i = 0; i < 10; i++) {
			for (String coord : portals) {
				String x = coord.substring(0, coord.indexOf(','));
				String y = coord.substring(coord.indexOf(',') + 1);
				Enemy enemy = new Enemy(Integer.valueOf(x), Integer.valueOf(y),
						Entity.DEFAULT_WIDTH, Entity.DEFAULT_HEIGHT);
				enemies.add(enemy);
			}
			
		}
		for (Enemy loon : enemies) {
			FileInputStream input = new FileInputStream("resources/Images/Balloons/camo.png");
 		    Image image = new Image(input);  
 		    gc.drawImage(image, loon.getY()*50, loon.getX()*50, 50, 50);
		}
		FileInputStream input = new FileInputStream("resources/Images/Balloons/camo.png");
		Image image = new Image(input);  
		ImageView ivm = new ImageView(image);
		ivm.setTranslateX(enemies.get(0).getY()*50);
		ivm.setTranslateX(enemies.get(0).getX()*50);
		//root.getChildren().add(ivm);
		Timeline t = new Timeline();
		t.setCycleCount(Timeline.INDEFINITE);
		t.getKeyFrames().add(new KeyFrame(
				Duration.millis(10000)));
		t.play();
		
		//gc.drawImage(image, loon.getY()*50, loon.getX()*50);
		
		
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
		portals = new ArrayList<String>();
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
            	   } else if (tile == ROAD_CHAR || tile == EXIT_CHAR) {
            		   path[k][i] = tile;
            		   input = new FileInputStream(IMAGE_PATH + "Ground.png");
            		   Image image = new Image(input); 
            		   gc.drawImage(image, i * Entity.DEFAULT_WIDTH,
            				   k * Entity.DEFAULT_HEIGHT);
            	   } else if (tile == PORTAL_CHAR) {
            		   path[k][i] = tile;
            		   portals.add(Integer.toString(k) + "," + Integer.toString(i));
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
		NextWaveClicked wave = new NextWaveClicked();
		newWaveButton.setOnAction(wave);
		
		controlBox.getChildren().add(newWaveButton);
		
		sidebarPane.setTop(statsBox);
		sidebarPane.setCenter(towerPane);
		sidebarPane.setBottom(controlBox);
		
		root.setRight(sidebarPane);
	}
	
	class NextWaveClicked implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			
			
		}
		
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
