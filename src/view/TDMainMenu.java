package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

import controller.TDController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;

public class TDMainMenu extends Stage {
	private BorderPane menuPane = new BorderPane();
	private String chosenMap;
	private GridPane stageBox;
	private boolean gameStarted = false;
	private TDController controller;
	private TDView view;
	private MediaPlayer playerMenu;

	public TDMainMenu(TDView view, TDController controller) {
		this.view = view;
		this.controller = controller;
		
		stopMenuMusic();
		Media pick = new Media(Paths.get("resources/music/Slam_of_Fates.mp3").toUri().toString());
		playerMenu = new MediaPlayer(pick);
		playerMenu.setOnEndOfMedia(() -> {
			playerMenu.seek(Duration.ZERO);
			playerMenu.play();
		});;
		playerMenu.setVolume(0.5);
		playerMenu.play();

		Image backgroundImage = null;
		try {
			backgroundImage = new Image(new FileInputStream("resources/images/Title4.png"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	    menuPane.setBackground(new Background(new BackgroundImage(backgroundImage,
	            BackgroundRepeat.REPEAT,
	            BackgroundRepeat.REPEAT,
	            BackgroundPosition.DEFAULT,
	           BackgroundSize.DEFAULT)));
		
		Button startBtn = new Button("Start");
		startBtn.setPadding(new Insets(10, 50, 10, 10));
		startBtn.setOnAction(new StageButton("map1.td"));

		/*
		Button stageBtn = new Button("Stage Select");
		stageBtn.setPadding(new Insets(10, 10, 10, 10));
		stageBtn.setOnAction(e -> {
			stageBox.setVisible(!stageBox.visibleProperty().getValue());
		});
		*/

		Button exitBtn = new Button("Exit");
		exitBtn.setPadding(new Insets(10, 50, 10, 10));
		exitBtn.setOnAction(e -> {
			System.exit(1);
		});

		Button stageOneBtn = new Button("Stage 1");
		stageOneBtn.setOnAction(new StageButton("map1.td"));
		stageOneBtn.setPadding(new Insets(20, 55, 20, 20));

		Button stageTwoBtn = new Button("Stage 2");
		stageTwoBtn.setOnAction(new StageButton("map2.td"));
		stageTwoBtn.setPadding(new Insets(20, 55, 20, 20));

		Button stageThreeBtn = new Button("Stage 3");
		stageThreeBtn.setOnAction(new StageButton("map3.td"));
		stageThreeBtn.setPadding(new Insets(20, 55, 20, 20));

		
		Button selectMapBtn = new Button("Select Map");
		selectMapBtn.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Map File");
			FileChooser.ExtensionFilter mapFilter = new ExtensionFilter("Map Files (*.td)", "*.td");
			FileChooser.ExtensionFilter allFilter = new ExtensionFilter("All Files", "*.*");
			fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
			fileChooser.getExtensionFilters().addAll(mapFilter, allFilter);
			File file = fileChooser.showOpenDialog(this);
			if (file != null) {
				chosenMap = file.getPath();
				Node source = (Node) e.getSource();
				Stage stage = (Stage) source.getScene().getWindow();
				stage.close();
			}
		});
		selectMapBtn.setPadding(new Insets(20, 60, 20, 20));
		
		//stageOneBtn.setVisible(false);
		//stageOneBtn.setManaged(true);
		//stageOneBtn.setOpaque(false);
		//stageOneBtn.setContentAreaFilled(false);
		//stageOneBtn.setBorderPainted(false);

			
		//stageTwoBtn.setVisible(false);
		//stageThreeBtn.setVisible(false);
		//selectMapBtn.setVisible(false);
		//startBtn.setVisible(false);
		//exitBtn.setVisible(false);
		
		VBox menuBox = new VBox(startBtn, exitBtn);
		menuBox.setAlignment(Pos.CENTER);
		menuBox.setSpacing(5);
		//menuBox.setVisible(false);

		stageBox = new GridPane();
		stageBox.add(stageOneBtn, 0, 0);
		stageBox.add(stageTwoBtn, 1, 0);
		stageBox.add(stageThreeBtn, 2, 0);
		stageBox.add(selectMapBtn, 1, 1);
		stageBox.setAlignment(Pos.CENTER);
		//stageBox.setVisible(false);
		stageBox.setVgap(10);
		stageBox.setHgap(15);

		GridPane.setHalignment(stageOneBtn, HPos.CENTER);
		GridPane.setHalignment(stageTwoBtn, HPos.CENTER);
		GridPane.setHalignment(stageThreeBtn, HPos.CENTER);
		GridPane.setHalignment(selectMapBtn, HPos.CENTER);

		BorderPane.setAlignment(menuBox, Pos.CENTER); 

		menuPane.setCenter(menuBox);
		menuPane.setBottom(stageBox);
		BorderPane.setMargin(menuBox, new Insets(120, 0, 0, 0));
		Scene scene = new Scene(menuPane, 450, 450);
		this.setScene(scene);
	}

	public void playMenuMusic() {
		if (playerMenu != null) {
			playerMenu.play();
		}
	}

	public void stopMenuMusic() {
		if (playerMenu != null) {
			playerMenu.stop();
			playerMenu = null;
		}
	}
	
	public String getMapImage() {
		return chosenMap;
	}

	private class StageButton implements EventHandler<ActionEvent> {
		private String mapFile;

		public StageButton(String mapFile) {
			this.mapFile = mapFile;
		}
	
		public void handle(ActionEvent e) {	
			if (!gameStarted) {
				playerMenu.stop();
				chosenMap = TDView.MAP_PATH + mapFile;
				gameStarted = true;
				stageBox.setVisible(false);
				Node source = (Node) e.getSource();
			    Stage stage = (Stage) source.getScene().getWindow();
			    stage.close();
			} else {
				playerMenu.stop();
				stageBox.setVisible(false);
				controller.stop();
				view.setMapFileName(TDView.MAP_PATH + mapFile);
				view.newGame();
				Node source = (Node) e.getSource();
			    Stage stage = (Stage) source.getScene().getWindow();
			    stage.close();
			}		
		}
	}

}
