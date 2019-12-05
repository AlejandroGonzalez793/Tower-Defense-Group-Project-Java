package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TDStageComplete extends Stage {
	private BorderPane vicPane = new BorderPane();
	private boolean pressed = false;
	
	public TDStageComplete() {
		Label victory = new Label("You won!");
		
		Button continueBtn = new Button("Continue");
		continueBtn.setPadding(new Insets(10, 10, 10, 10));
		continueBtn.setOnAction(e -> {
			pressed = true;
			Node source = (Node) e.getSource();
		    Stage stage = (Stage) source.getScene().getWindow();
		    stage.close();
	    });
		
		VBox vBox = new VBox(victory, continueBtn);
		vBox.setAlignment(Pos.TOP_CENTER);
		vBox.setSpacing(5);
		
		vicPane.setCenter(vBox);
		Scene scene = new Scene(vicPane, 200, 100);
		this.setScene(scene);
	}
	
	public boolean isPressed() {
		return pressed;
	}
	
}
