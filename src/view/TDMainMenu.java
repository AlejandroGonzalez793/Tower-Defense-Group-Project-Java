package view;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class TDMainMenu extends Stage {
	private BorderPane menuPane = new BorderPane();
	private String chosenMap;
	private VBox stageBox;
	
	public TDMainMenu() {
		Button startBtn = new Button("Start");
		startBtn.setOnAction(new StageButton("map1.td"));
		
		Button stageBtn = new Button("Stage Select");
		stageBtn.setOnAction(e -> {
			stageBox.setVisible(true);
	    });
		
		Button exitBtn = new Button("Exit");
		exitBtn.setOnAction(e -> {
			System.exit(1);		
	    });	
		
		Button stageOneBtn = new Button("Stage 1");
		stageOneBtn.setOnAction(new StageButton("map1.td"));
		Button stageTwoBtn = new Button("Stage 2");
		stageTwoBtn.setOnAction(new StageButton("map2.td"));
		Button stageThreeBtn = new Button("Stage 3");
		stageThreeBtn.setOnAction(new StageButton("map3.td"));
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
		
		VBox menuBox = new VBox(startBtn, stageBtn, exitBtn);
		menuBox.setAlignment(Pos.CENTER);
		
		stageBox = new VBox(stageOneBtn, stageTwoBtn, stageThreeBtn, selectMapBtn);
		stageBox.setAlignment(Pos.CENTER);
		stageBox.managedProperty().bind(stageBox.visibleProperty());
		stageBox.setVisible(false);
		
		HBox hBox = new HBox(menuBox, stageBox);
		hBox.setSpacing(15);
		
		BorderPane.setAlignment(hBox, Pos.CENTER);
		BorderPane.setMargin(hBox, new Insets(50, 0, 100, 185)); 
		
		menuPane.setBottom(hBox);
		Scene scene = new Scene(menuPane, 450, 450);
		this.setScene(scene);
		System.out.println("Menu Created");
	}
	
	public String getMapImage() {
		return chosenMap;
	}
	
	class StageButton implements EventHandler<ActionEvent> {
		private String mapFile;
		
		public StageButton(String mapFile) {
			this.mapFile = mapFile;
		}
		
		public void handle(ActionEvent e) {	
			chosenMap = TDView.MAP_PATH + mapFile;
			Node source = (Node) e.getSource();
		    Stage stage = (Stage) source.getScene().getWindow();
		    stage.close();
		}
	}
	
} 
