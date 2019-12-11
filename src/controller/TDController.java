package controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import model.Entity;
import model.GameState;
import model.Node;
import model.Player;
import model.Waves;
import model.enemies.Enemy;
import model.projectiles.Projectile;
import model.towers.AreaTower;
import model.towers.CheapTower;
import model.towers.ExpensiveTower;
import model.towers.MultiShotTower;
import model.towers.OneShotTower;
import model.towers.PiercingTower;
import model.towers.RapidTower;
import model.towers.Tower;

/**
 * The general controller used to interact with the game in its current state.
 * 
 * Contains a <tt>Player</tt> reference that holds the state of current user
 * that is playing the game. Also contains a reference to <tt>GameState</tt>
 * which holds lists for all of the towers, enemies, projectiles as well as
 * logic for changing all of the entities at each tick of the game.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu)
 */
public class TDController {
	private Player player;
	private GameState gameState;
	private Tower selectedTower;
	private Map<String, Class<? extends Tower>> towerMap;
	private AnimationTimer at;
	private boolean playing;
	private double animationSpeed = 1.0;
	private boolean newRound = true;
	private int waveNumber = 0;
	public static final int TICK_SPEED = 40;

	public TDController(Player player, GameState gameState) {
		this.player = player;
		this.gameState = gameState;
		this.selectedTower = null;
		this.playing = false;

		this.towerMap = new HashMap<String, Class<? extends Tower>>();
		towerMap.put("CheapTower", CheapTower.class);
		towerMap.put("Tower", Tower.class);
		towerMap.put("ExpensiveTower", ExpensiveTower.class);
		towerMap.put("MultiShotTower", MultiShotTower.class);
		towerMap.put("RapidTower", RapidTower.class);
		towerMap.put("AreaTower", AreaTower.class);
		towerMap.put("Thicc Yoshi", PiercingTower.class);
		towerMap.put("OneShotTower", OneShotTower.class);
	}

	/**
	 * Calls the tick method in the game state to update the positions of all of the
	 * entities on the board.
	 */
	public void tick() {
		gameState.tick();
	}

	/**
	 * Figure out whether the player is dead based on their health.
	 * If it is less than or equal to 0, they are dead.
	 * 
	 * @return true of the player is dead, false otherwise
	 */
	public boolean isPlayerDead() {
		return player.getHealth() <= 0;
	}
	
	/**
	 * Figure out if the game is over based on which wave we are on.
	 * 
	 * @return true if the game is over, false otherwise
	 */
	public boolean isGameOver() {
		return newRound && waveNumber > Waves.MAX_WAVES;
	}
	
	/**
	 * Determines if the player can purchase a tower specified by its name
	 * 
	 * @param name the name of the tower
	 * @return true if the tower can be purchased, false otherwise
	 */
	public boolean canPurchaseTower(String name) {
		selectedTower = getTowerByName(name);
		return selectedTower.getCost() <= player.getMoney();
	}

	/**
	 * Determine if the selected tower can be placed at a specified x or y position.
	 * 
	 * A tower must be selected before this method is called.
	 * 
	 * The x and y position passed into this function should correspond to the
	 * middle of where the tower should be placed.
	 * 
	 * @param x the x coordinate at the middle of where the tower should be placed
	 * @param y the y coordinate at the middle of where the tower should be placed
	 * @param height the height of the board that we are placing the tower on
	 * @param width the width of the board that we are placing the tower on
	 * @return true if the selected tower can be placed at the specified position,
	 *         false otherwise
	 */
	public boolean canPlaceTower(int x, int y, int height, int width) {
		int shiftedX = x - (selectedTower.getWidth() / 2);
		int shiftedY = y - (selectedTower.getHeight() / 2);

		// Check if tower collides with path
		Node node = gameState.getStart();
		while (node != null) {
			Rectangle rect = node.getRectangle();
			if (rect.intersects(shiftedX, shiftedY, selectedTower.getWidth(), selectedTower.getHeight())) {
				return false;
			}

			node = node.getNext();
		}
		// check if tower goes off board
		if (shiftedX < 0 || shiftedY < 0 || (selectedTower.getWidth() / 2 + x) > width 
				|| (selectedTower.getHeight() / 2 + y) > height) {
			return false;
		}
		
		for (Rectangle zone : gameState.getDeadZones()) {
			if (zone.intersects(shiftedX, shiftedY, selectedTower.getWidth(), selectedTower.getHeight())) {
				return false;
			}
		}
		
		// Check if tower collides with another tower
		for (Tower tower : gameState.getTowers()) {
			Rectangle rect = new Rectangle(tower.getX(), tower.getY(), tower.getWidth(), tower.getHeight());
			if (rect.intersects(shiftedX, shiftedY, selectedTower.getWidth(), selectedTower.getHeight())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if a Projectile has collided with any enemies.
	 * 
	 * @param bullet Projectile to be checked with a collision.
	 * @return True or false depending if a collision was made or not.
	 */
	public boolean checkBulletCollision(Projectile bullet) {
		for (Enemy enemy : gameState.getEnemies()) {
			if (getCollision(bullet, enemy)) {
				enemy.setHealth(enemy.getHealth() - bullet.getPower());
				return true;
			}
		}
		return false;
	}

	/**
	 * Determines if there is a collision between two entities.
	 * 
	 * @param e1 the first Entity to check
	 * @param e2 the second Entity to check
	 * @return true if the two entities are colliding, false otherwise
	 */
	public boolean getCollision(Entity e1, Entity e2) {
		return e1.getX() < e2.getX() + e2.getWidth() && e1.getX() + e1.getWidth() > e2.getX()
				&& e1.getY() < e2.getY() + e2.getHeight() && e1.getY() + e1.getHeight() > e2.getY();
	}

	/**
	 * Adds the current selected tower to the tower list and subtracts the tower's
	 * cost from the player's money.
	 * 
	 * @param x This is a x coordinate
	 * @param y This is a y coordinate
	 */
	public void addTower(int x, int y) {
		// TODO: Do something if no selected tower
		if (selectedTower != null) {
			int shiftedX = x - (selectedTower.getWidth() / 2);
			int shiftedY = y - (selectedTower.getHeight() / 2);
			selectedTower.setX(shiftedX);
			selectedTower.setY(shiftedY);

			player.setMoney(player.getMoney() - selectedTower.getCost());
			gameState.addTower(selectedTower);
			selectedTower = null;
		}
	}

	/**
	 * Checks if the user clicked on a tower. If a tower was clicked on, then sell
	 * it back to the player and reference the selected tower.
	 * 
	 * @param x the x coordinate of where the player clicked
	 * @param y the y coordinate of where the player clicked
	 * @return True or false depending if a tower was sold back or not.
	 */
	public boolean sellTower(int x, int y) {
		for (Tower tower : gameState.getTowers()) {
			if (x > tower.getX() && x < tower.getX() + tower.getWidth() && y > tower.getY()
					&& y < tower.getY() + tower.getHeight()) {
				player.setMoney(player.getMoney() + (int) (tower.getCost() * Tower.SELLBACK_FACTOR));
				gameState.removeTower(tower);
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the cost of a tower object by its associated name.
	 * 
	 * @param towerName the name of the tower object to get
	 * @return The cost of the specified tower object.
	 */
	public int getTowerCost(String towerName) {
		return getTowerByName(towerName).getCost();
	}

	/**
	 * Adds gold to the Player's money
	 * 
	 * @param gold the amount of money to add to the Player
	 */
	public void addGold(int gold) {
		player.setMoney(player.getMoney() + gold);
	}

	/**
	 * Gets a specific tower by name by doing a lookup in the TowerType enum and
	 * then getting and instance of the class through reflection. If a tower of the
	 * specified name is not found, the default tower is returned instead.
	 * 
	 * @param name the name of the tower to get
	 * @return A Tower object
	 */
	private Tower getTowerByName(String name) {
		Class<?> c = towerMap.get(name);

		if (c == null) {
			return new Tower();
		}

		Object object;
		try {
			Constructor<?> cons = c.getConstructor();
			object = cons.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException 
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			object = new Tower();
		}

		return (Tower) object;
	}

	/**
	 * Gets a map of all of the tower names to its associated image.
	 * 
	 * @return A Map of String to Image with the key being the tower's name and the
	 *         value being the tower's Image
	 */
	public Map<String, Image> getTowerImageMap() {
		Map<String, Image> imageMap = new TreeMap<>();

		for (Entry<String, Class<? extends Tower>> towerEntry : towerMap.entrySet()) {
			try {
				Constructor<?> cons = towerEntry.getValue().getConstructor();
				Tower tower = (Tower) cons.newInstance();
				imageMap.put(towerEntry.getKey(), tower.getImage());
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
		return imageMap;
	}

	/**
	 * Returns a set of all of the possible tower names that can be added to the
	 * board.
	 * 
	 * @return A Set containing the names of all of the towers available
	 */
	public Set<String> getTowerNames() {
		return towerMap.keySet();
	}

	/**
	 * Adds a "path tile" to the game state. Used for keeping track of which
	 * portions of the map are going to be used as the path for enemies to take
	 * 
	 * @param x      top left x coordinate of the tile
	 * @param y      top left y coordinate of the tile
	 * @param width  width of the tile in pixels
	 * @param height height of the tile in pixels
	 */
	public void addPathTile(int x, int y, int width, int height) {
		gameState.addNode(new Node(new Rectangle(x, y, width, height)));
	}

	/**
	 * Gets the image to use for the currently selected tower.
	 * 
	 * @return the currently selected tower's Image.
	 */
	public Image getSelectedTowerImage() {
		return selectedTower.getImage();
	}

	/**
	 * Doubles the animation speed of the game
	 */
	public void speedUp() {
		animationSpeed = 0.2;
	}

	/**
	 * Sets the animation speed to the normal amount (1x)
	 */
	public void regularSpeed() {
		animationSpeed = 1.0;
	}

	/**
	 * Halves the animation speed of the game
	 */
	public void slowDown() {
		animationSpeed = 2.0;
	}

	/**
	 * Stops any animations from being run in the game
	 */
	public void pause() {
		playing = !playing;
	}

	/**
	 * Gets whether or not the current wave is running or paused.
	 * 
	 * @return playing This is a boolean that determines if current 
	 * wave is playing.
	 */
	public boolean getIsPlaying() {
		return playing;
	}

	/**
	 * Creates a new AnimationTimer that runs while the games is in progress. Calls
	 * the tick method to update all of the objects on the screen every
	 * {@value #TICK_SPEED} milliseconds.
	 */
	public void startGame() {
		if (at != null) {
			return;
		}
		
		at = new AnimationTimer() {
			private long lastUpdate = 0;

			@Override
			public void handle(long now) {
				if (playing && now - lastUpdate >= (TICK_SPEED * animationSpeed) * 1000000) {
					
					lastUpdate = now;
					Enemy enemy = gameState.enemyContact();
					if (enemy != null) {
						player.setHealth(player.getHealth() - enemy.getPower());
						gameState.removeEnemy(enemy);
					}
					
					// if wave is done, set playing to false and resetProjectiles.
					if (gameState.getEnemies().isEmpty()) {
						playing = false;
						newRound = true;
						gameState.resetProjectiles();
					}
					
					tick();
				}
			}
		};
		at.start();
	}

	/**
	 * Return the state of the round.
	 * 
	 * @return true if the round ended, false if a round is still running.
	 */
	public boolean isNewRound() {
		return newRound;
	}

	/**
	 * Gets the next wave of enemies, adds them to the game state and then 
	 * starts the wave.
	 */
	public void nextWave() {
		playing = true;
		newRound = false;
		
		Rectangle rect = gameState.getStart().getRectangle();
		int x = (int) rect.getX();
		int y = (int) rect.getY();
		
		gameState.setEnemies(Waves.getWave(waveNumber, x, y));
		waveNumber++;
		startGame();
	}
	
	/**
	 * Sets the enemy wave number
	 * 
	 * @param waveNumber the number to set the wave to
	 */
	public void setWaveNumber(int waveNumber) {
		this.waveNumber = waveNumber;
	}

	/**
	 * Gets the current enemy wave number.
	 * 
	 * @return Integer the wave number
	 */
	public int getWaveNumber() {
		return waveNumber;
	}
	
	
	/**
	 * Adds a rectangle to a list of dead zones in the model
	 * 
	 * @param zone Rectangle object that represents a dead zone
	 */
	public void addDeadzone(Rectangle zone) {
		this.gameState.addDeadzones(zone);
	}

	
	/**
	 * Stops any running wave by setting playing to false and then
	 * completely stops the animation if it is running.
	 */
	public void stop() {
		playing = false;
		if (at != null) {
			at.stop();
		}
	}
}
