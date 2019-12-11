import javafx.application.Application;
import view.TDView;

/**
 * The TowerDefense class will launch the Tower Defense game "Flying Defense!"
 * The game comes with three stages and a map selection that allows users to
 * play on custom maps. Various towers and enemies are present along with an
 * orchestrated tone that will play in the background.
 * 
 * <p>
 * <b>Compilation: </b>javac *.java model/*.java model/enemies/*.java
 * model/projectiles/*.java model/towers/*.java util/*.java view/*.java
 * controller/*.java
 * </p>
 * 
 * <p>
 * <b>Execution: </b>java TowerDefense
 * </p>
 * 
 * <p>
 * The game also allows the users to provides a custom map format for users to
 * create their own maps. A custom map must be a text file that starts with the
 * number of rows on one line, number of columns on the next line and then an
 * ASCII representation of the board using the following characters:<br>
 * <br>
 * `*` - free space (a place that a tower can be placed)<br>
 * `-` - path space (where the enemies can travel)<br>
 * `+` - start of the path (where the enemies will start)<br>
 * `=` - end of the path (where the enemies will stop) <br>
 * `d` - blocked tile space where nothing can be placed <br>
 * <br>
 * <b>Example:</b><br>
 * 16<br>
 * 13<br>
 * ****----**=*****<br>
 * ****-**-**-*****<br>
 * **---**-**-----*<br>
 * **-****-******-*<br>
 * **-****-******-*<br>
 * **---**-**-----*<br>
 * ****-**-**-*****<br>
 * ****-**-**-*****<br>
 * **---**-**-*----<br>
 * **-****-**-*-**-<br>
 * +--****-**---**-<br>
 * *******-*******-<br>
 * *******---------<br>
 * </p>
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
