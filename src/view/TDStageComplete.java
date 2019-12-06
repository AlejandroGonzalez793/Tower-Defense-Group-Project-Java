package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TDStageComplete extends Stage {
	private BorderPane vicPane = new BorderPane();
	private Button continueBtn;
	
	public TDStageComplete() {
		Label victory = new Label("You won!");
		
		continueBtn = new Button("Continue");
		continueBtn.setPadding(new Insets(10, 10, 10, 10));
		
		VBox vBox = new VBox(victory, continueBtn);
		vBox.setAlignment(Pos.TOP_CENTER);
		vBox.setSpacing(5);
		
		vicPane.setCenter(vBox);
		Scene scene = new Scene(vicPane, 200, 100);
		this.setScene(scene);
	}
	
	public Button getContinueBtn() {
		return continueBtn;
	}
	
}
