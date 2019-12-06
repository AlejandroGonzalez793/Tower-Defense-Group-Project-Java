package view;

import java.io.File;

import controller.TDController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class TDMainMenu extends Stage {
	private BorderPane menuPane = new BorderPane();
	private String chosenMap;
	private GridPane stageBox;
	private boolean gameStarted = false;
	private String mapFileName;
	private TDController controller;
	private TDView view;
	
	public TDMainMenu() {		
		Button startBtn = new Button("Start");
		startBtn.setPadding(new Insets(10, 10, 10, 10));
		startBtn.setOnAction(new StageButton("map1.td"));
		
		Button stageBtn = new Button("Stage Select");
		stageBtn.setPadding(new Insets(10, 10, 10, 10));
		stageBtn.setOnAction(e -> {
			stageBox.setVisible(!stageBox.visibleProperty().getValue());
	    });
		
		Button exitBtn = new Button("Exit");
		exitBtn.setPadding(new Insets(10, 10, 10, 10));
		exitBtn.setOnAction(e -> {
			System.exit(1);		
	    });	
		
		Button stageOneBtn = new Button("Stage 1");
		stageOneBtn.setOnAction(new StageButton("map1.td"));
		stageOneBtn.setPadding(new Insets(20, 20, 20, 20));
		
		Button stageTwoBtn = new Button("Stage 2");
		stageTwoBtn.setOnAction(new StageButton("map2.td"));
		stageTwoBtn.setPadding(new Insets(20, 20, 20, 20));

		Button stageThreeBtn = new Button("Stage 3");
		stageThreeBtn.setOnAction(new StageButton("map3.td"));
		stageThreeBtn.setPadding(new Insets(20, 20, 20, 20));
		
		Button selectMapBtn = new Button("Select Map");
		selectMapBtn.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Map File");
			FileChooser.ExtensionFilter mapFilter = new ExtensionFilter(
					"Map Files (*.td)", "*.td");
			FileChooser.ExtensionFilter allFilter = new ExtensionFilter(
					"All Files", "*.*");
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
		selectMapBtn.setPadding(new Insets(20, 20, 20, 20));
		
		HBox menuBox = new HBox(startBtn, stageBtn, exitBtn);
		menuBox.setAlignment(Pos.TOP_CENTER);
		menuBox.setSpacing(5);
		
		stageBox = new GridPane();
		stageBox.add(stageOneBtn, 0, 0);
		stageBox.add(stageTwoBtn, 0, 1);
		stageBox.add(stageThreeBtn, 0, 2);
		stageBox.add(selectMapBtn, 0, 3);
		stageBox.setAlignment(Pos.CENTER);
		stageBox.setVisible(false);
		stageBox.setVgap(10);
		
		GridPane.setHalignment(stageOneBtn, HPos.CENTER);
		GridPane.setHalignment(stageTwoBtn, HPos.CENTER);
		GridPane.setHalignment(stageThreeBtn, HPos.CENTER);
		GridPane.setHalignment(selectMapBtn, HPos.CENTER);

		BorderPane.setAlignment(menuBox, Pos.CENTER);
		
		menuPane.setTop(menuBox);
		menuPane.setCenter(stageBox);
		BorderPane.setMargin(menuBox, new Insets(10, 0, 0, 0));
		Scene scene = new Scene(menuPane, 450, 450);
		this.setScene(scene);
	}
	
	public String getMapImage() {
		return chosenMap;
	}
	
	public void setReferences(TDView view, TDController controller, String mapFileName) {
		this.view = view;
		this.mapFileName = mapFileName;
		this.controller = controller;
	}
	
	class StageButton implements EventHandler<ActionEvent> {
		private String mapFile;
		
		public StageButton(String mapFile) {
			this.mapFile = mapFile;
		}
		
		public void handle(ActionEvent e) {	
			if (!gameStarted) {
				chosenMap = TDView.MAP_PATH + mapFile;
				gameStarted = true;
				stageBox.setVisible(false);
				Node source = (Node) e.getSource();
			    Stage stage = (Stage) source.getScene().getWindow();
			    stage.close();
			} else {
				stageBox.setVisible(false);
				mapFileName = TDView.MAP_PATH + mapFile;
				controller.stopAnimation();
				controller.reset();
				view.newGame();
				Node source = (Node) e.getSource();
			    Stage stage = (Stage) source.getScene().getWindow();
			    stage.close();
			}		
		}
	}
	
} 
