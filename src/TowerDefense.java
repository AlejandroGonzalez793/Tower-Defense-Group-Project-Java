import javafx.application.Application;
import view.TDView;

/**
 * The TowerDefense class will launch the Tower Defense game "Flying Defense!"
 * The game comes with three stages and a map selection that allows users
 * to play on custom maps. Various towers and enemies are present along with 
 * an orchestrated tone that will play in the background.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu)
 */
public class TowerDefense {
	/**
	 * The main class launches the Tower Defense game.
	 * 
	 * @param args launch arguments.
	 */
	public static void main(String[] args) {
		Application.launch(TDView.class, args);
	}
}
