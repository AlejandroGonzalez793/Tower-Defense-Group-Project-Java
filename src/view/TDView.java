package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TDView extends Application {
	
	private BorderPane root;
	private Scene scene;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		root = new BorderPane();
		
		scene = new Scene(root, 650, 450);
		primaryStage.setTitle("Tower Defense");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
