import javafx.application.Application;
import util.ResourceManager;
import view.TDView;

public class TowerDefense {
	public static void main(String[] args) {
		ResourceManager.loadImages();
		ResourceManager.loadAudio();
		
		Application.launch(TDView.class, args);
	}
}
