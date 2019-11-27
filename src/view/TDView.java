package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TDView extends Application implements Observer{
	private BorderPane root;
	private Canvas canvas;
	private GraphicsContext gc;
	
	private Text money;
	private Text health;
	
	private GridPane towerPane;
	
	private static final String IMAGE_MAP_PATH = "resources/images/maps/";
	private static final String TOWER_IMAGE_PATH = "resources/images/towers/";

	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new BorderPane();
		canvas = new Canvas();
		createMap();
		createLayout();
		
		
		primaryStage.setTitle("Tower Defense");
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.show();
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
			
			canvas.setOnMouseClicked(e ->{
				
			});
		} catch (FileNotFoundException e) {
			System.out.println("Your image could not be found.");
			e.printStackTrace();
		}
		
		root.setCenter(canvas);
	}
	
	public void createLayout() {
		BorderPane sidebarPane = new BorderPane();
		towerPane = new GridPane();
		towerPane.setPadding(new Insets(5, 5, 5, 5));
		towerPane.setVgap(5);
		towerPane.setHgap(5);
		
		// need to place towers here!!!
		
		VBox statsBox = new VBox();
		statsBox.setSpacing(5);
		
		HBox hpBox = new HBox();
		Label hpLabel = new Label("HP: ");
		health = new Text(Integer.toString(1000)); // needs to be player health!
		hpBox.getChildren().addAll(hpLabel, health);
		
		HBox moneyBox = new HBox();
		Label moneyLabel = new Label("Gold: ");
		money = new Text(Integer.toString(1000)); // player starting money!
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
	
   
	
	

	@Override
	public void update(Observable o, Object arg) {
		// Need to re-draw everything in the view
		// arg will be the GameState object 
		createMap(); // canvas
		// projectiles
		// enemies
		// towers
		
	}
	


}
