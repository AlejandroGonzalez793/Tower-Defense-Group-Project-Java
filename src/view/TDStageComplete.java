package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class displays the game over features
 * 
 * Stage Complete creates a game over message that allows the
 * user to continue and go back to the main menu.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu)
 */
public class TDStageComplete extends Stage {
	private BorderPane vicPane = new BorderPane();
	private Button continueBtn;
	private Label gameOverMessage;
	
	/**
	 * This is the TDStageComplete constructor that displays
	 * a game over message.
	 */
	public TDStageComplete() {
		gameOverMessage = new Label("Game Ended");
		gameOverMessage.setMinSize(50, 50);
		
		continueBtn = new Button("Continue");
		continueBtn.setPadding(new Insets(10, 10, 10, 10));
		
		VBox vBox = new VBox(gameOverMessage, continueBtn);
		vBox.setAlignment(Pos.TOP_CENTER);
		vBox.setSpacing(5);
		
		vicPane.setCenter(vBox);
		Scene scene = new Scene(vicPane, 200, 100);
		this.setScene(scene);
	}
	
	/**
	 * This method sets the label for the game over message.
	 * 
	 * @param message This is a string that indicates if the user 
	 * won or lost.
	 */
	public void setLabel(String message) {
		gameOverMessage.setText(message);
	}
	
	/**
	 * This method returns a button object for continue.
	 * @return Button
	 */
	public Button getContinueBtn() {
		return continueBtn;
	}
	
}
