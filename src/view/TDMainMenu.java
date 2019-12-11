package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

import controller.TDController;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;

/**
 * The TDMainMenu class provides the main menu of the Tower Defense game.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu)
 */
public class TDMainMenu extends Stage {
	private BorderPane menuPane = new BorderPane();
	private String chosenMap;
	private GridPane stageBox;
	private boolean gameStarted = false;
	private TDController controller;
	private TDView view;
	private MediaPlayer playerMenu;

	/**
	 * Create the main menu that has clickable labels Start, Exit,
	 * Stage 1, Stage 2, Stage 3 and Map Select. Start makes it so the
	 * user begins at stage 1. Exit closes the application. Stage 1, Stage 2 and
	 * Stage 3 will put the user to stage 1, 2 or 3 respectively. The Map
	 * Selection will allow the user to access a custom map of their choice.
	 * 
	 * @param view the TDView object for reference.
	 * @param controller the TDcontroller object for reference.
	 * @throws FileNotFoundException when an image can not be found.
	 */
	public TDMainMenu(TDView view, TDController controller) {
		this.view = view;
		this.controller = controller;

		stopMenuMusic();
		Media pick = new Media(Paths.get("resources/music/Slam_of_Fates.mp3").toUri().toString());
		playerMenu = new MediaPlayer(pick);
		playerMenu.setOnEndOfMedia(() -> {
			playerMenu.seek(Duration.ZERO);
			playerMenu.play();
		});
		
		playerMenu.setVolume(0.5);
		playerMenu.play();

		Image backgroundImage = null;
		try {
			backgroundImage = new Image(new FileInputStream("resources/images/Title4.png"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		menuPane.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

		Label startBtn = new Label("Start");
		startBtn.setPadding(new Insets(25, 25, 10, 25));
		startBtn.setOnMouseClicked(new StageButton("map1.td"));
		startBtn.setFont(new Font("Arial", 35));

		Label exitBtn = new Label("Exit");
		exitBtn.setPadding(new Insets(10, 25, 25, 25));
		exitBtn.setOnMouseClicked(e -> {
			System.exit(1);
		});
		exitBtn.setFont(new Font("Arial", 35));

		Label stageOneBtn = new Label("Stage 1");
		stageOneBtn.setOnMouseClicked(new StageButton("map1.td"));
		stageOneBtn.setPadding(new Insets(20, 20, 20, 20));
		stageOneBtn.setFont(new Font("Arial", 20));

		Label stageTwoBtn = new Label("Stage 2");
		stageTwoBtn.setOnMouseClicked(new StageButton("map2.td"));
		stageTwoBtn.setPadding(new Insets(20, 20, 20, 20));
		stageTwoBtn.setFont(new Font("Arial", 20));

		Label stageThreeBtn = new Label("Stage 3");
		stageThreeBtn.setOnMouseClicked(new StageButton("map3.td"));
		stageThreeBtn.setPadding(new Insets(20, 20, 20, 20));
		stageThreeBtn.setFont(new Font("Arial", 20));

		Label selectMapBtn = new Label("Select Map");
		selectMapBtn.setOnMouseClicked(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Map File");
			FileChooser.ExtensionFilter mapFilter = new ExtensionFilter("Map Files (*.td)", "*.td");
			FileChooser.ExtensionFilter allFilter = new ExtensionFilter("All Files", "*.*");
			fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
			fileChooser.getExtensionFilters().addAll(mapFilter, allFilter);
			File file = fileChooser.showOpenDialog(this);
			if (file != null) {
				if (!gameStarted) {
					playerMenu.stop();
					chosenMap = file.getPath();
					gameStarted = true;
					Node source = (Node) e.getSource();
					Stage stage = (Stage) source.getScene().getWindow();
					stage.close();
				} else {
					playerMenu.stop();
					controller.stop();
					view.setMapFileName(file.getPath());
					view.newGame();
					Node source = (Node) e.getSource();
					Stage stage = (Stage) source.getScene().getWindow();
					stage.close();
				}
			}
		});
		selectMapBtn.setPadding(new Insets(20, 20, 20, 20));
		selectMapBtn.setFont(new Font("Arial", 20));

		VBox menuBox = new VBox(startBtn, exitBtn);
		menuBox.setAlignment(Pos.CENTER);
		menuBox.setSpacing(5);

		stageBox = new GridPane();
		stageBox.add(stageOneBtn, 0, 0);
		stageBox.add(stageTwoBtn, 1, 0);
		stageBox.add(stageThreeBtn, 2, 0);
		stageBox.add(selectMapBtn, 1, 1);
		stageBox.setAlignment(Pos.CENTER);
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

	/**
	 * If the MediaPlayer object is not null, music will be played.
	 */
	public void playMenuMusic() {
		if (playerMenu != null) {
			playerMenu.play();
		}
	}

	/**
	 * If the MediaPlayer object is not null, stop playing music
	 * and set the MediaPlayer to null.
	 */
	public void stopMenuMusic() {
		if (playerMenu != null) {
			playerMenu.stop();
			playerMenu = null;
		}
	}

	/**
	 * Get the chosen map name for the stage to be played.
	 * 
	 * @return the chosen map String for the stage selection
	 */
	public String getMapImage() {
		return chosenMap;
	}

	/**
	 * The StageButton is used for the Stage 1, 2 and 3 labels as well as the
	 * Start label. When the user clicks on the label, the MouseEvent will
	 * pass in the correct map name and this will be sent to the TDView so
	 * the correct stage will be played.
	 */
	private class StageButton implements EventHandler<MouseEvent> {
		private String mapFile;

		/**
		 * Constructor for StageButton that takes in the selected map name.
		 * 
		 * @param mapFile the map name for the stage selection
		 */
		public StageButton(String mapFile) {
			this.mapFile = mapFile;
		}

		/**
		 * If the game hasn't been played for the first time, then the chosenMap variable
		 * will be used to pass in the map name to the TDView. If the game has already been 
		 * played at least once, then the mapFile will be directly passed into the TDView 
		 * for the stage selection.
		 */
		public void handle(MouseEvent e) {
			if (!gameStarted) {
				playerMenu.stop();
				chosenMap = TDView.MAP_PATH + mapFile;
				gameStarted = true;
				Node source = (Node) e.getSource();
				Stage stage = (Stage) source.getScene().getWindow();
				stage.close();
			} else {
				playerMenu.stop();
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
