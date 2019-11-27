package view;

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
import javafx.stage.Stage;

public class TDMainMenu extends Stage{
	private BorderPane menuPane = new BorderPane();
	private String choosenMap;
	private VBox stageBox;
	
	public TDMainMenu() {
		Button startBtn = new Button("Start");
		startBtn.setMinWidth(80.0);
		startBtn.setPrefWidth(80.0);
		startBtn.setMaxWidth(80.0);
		startBtn.setOnAction(new StageButton("TDMap2.png"));
		
		Button stageBtn = new Button("Stage Select");
		stageBtn.setMinWidth(100.0);
		stageBtn.setPrefWidth(100.0);
		stageBtn.setMaxWidth(100.0);
		stageBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) {
				stageBox.setVisible(true);
			}
	    });
		Button exitBtn = new Button("Exit");
		exitBtn.setMinWidth(80.0);
		exitBtn.setPrefWidth(80.0);
		exitBtn.setMaxWidth(80.0);
		exitBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event) {
				System.exit(1);		
			}
	    });	
		
		Button stageOneBtn = new Button("Stage 1");
		stageOneBtn.setOnAction(new StageButton("TDMap2.png"));
		Button stageTwoBtn = new Button("Stage 2");
		stageTwoBtn.setOnAction(new StageButton("TDMap1.png"));
		Button stageThreeBtn = new Button("Stage 3");
		stageThreeBtn.setOnAction(new StageButton("TDMap2.png"));
		
		VBox menuBox = new VBox(startBtn, stageBtn, exitBtn);
		menuBox.setAlignment(Pos.CENTER);
		
		stageBox = new VBox(stageOneBtn, stageTwoBtn, stageThreeBtn);
		stageBox.setAlignment(Pos.CENTER);
		stageBox.managedProperty().bind(stageBox.visibleProperty());
		stageBox.setVisible(false);
		
		HBox hBox = new HBox(menuBox, stageBox);
		hBox.setSpacing(15);
		
		BorderPane.setAlignment(hBox, Pos.CENTER);
		BorderPane.setMargin(hBox, new Insets(50,0,100,185)); 
		
		menuPane.setBottom(hBox);
		Scene scene = new Scene(menuPane, 450, 450);
		this.setScene(scene);
		System.out.println("Menu Created");
	}// end TDMainMenu constructor
	
	public String getMapImage()
	{
		return choosenMap;
	}
	
	class StageButton implements EventHandler<ActionEvent> {
		private String mapFile;
		
		public StageButton(String mapFile) {
			this.mapFile = mapFile;
		}
		
		public void handle(ActionEvent e) {	
			choosenMap = mapFile;
			Node source = (Node) e.getSource();
		    Stage stage = (Stage) source.getScene().getWindow();
		    stage.close();
		}
	}
	
}// end TDMainMenu class
