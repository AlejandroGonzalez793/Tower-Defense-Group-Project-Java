package controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import model.CheapTower;
import model.Enemy;
import model.ExpensiveTower;
import model.GameState;
import model.MultiShotTower;
import model.OneShotTower;
import model.Player;
import model.Projectile;
import model.Tower;


/**
 * The general controller used to interact with the game in its current state.
 * 
 * Contains auxiliary lists of towers and enemies at all times to keep track
 * of what is currently on the board in the view without needing to use a 
 * separate aggregate model.
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
	private boolean playing;
	public static final int TICK_SPEED = 100;
	
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
		towerMap.put("OneShotTower", OneShotTower.class);
	}
	
	/**
	 * Calls the tick method in the game state to update the positions
	 * of all of the entities on the board.
	 */
	public void tick() {
		gameState.tick();
	}
	
	/**
	 * Figure out whether the game is over or not
	 * 
	 * @return true of the game is over, false otherwise
	 */
	public boolean isGameOver() {
		return player.getHealth() <= 0;
	}
	
	/**
	 * Determines if the player can purchase a tower 
	 * specified by its name
	 * 
	 * @param name the name of the tower
	 * @return true if the tower can be purchased, false otherwise
	 */
	public boolean canPurchaseTower(String name) {
		selectedTower = getTowerByName(name);
		return selectedTower.getCost() <= player.getMoney();
	}
	
	public boolean canPlaceTower(int x, int y) {
		List<Rectangle> path = gameState.getPath();
		int shiftedX = x - (selectedTower.getWidth() / 2);
		int shiftedY = y - (selectedTower.getHeight() / 2);
		
		// Check if tower collides with path
		for (Rectangle rect : path) {
			if (rect.intersects(shiftedX, shiftedY, selectedTower.getWidth(), selectedTower.getHeight())) {
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
	 * Check bullet collisions with enemies.
	 * 
	 * @param bullet object to be checked with a collision.
	 * @return True or false depending if a collision was made or not.
	 */
	public boolean checkBulletCollision(Projectile bullet) {
		for (Enemy enemy : gameState.getEnemies()) {
			if (gameState.getCollision(bullet, enemy)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds the current selected tower to the tower list and 
	 * subtracts the tower's cost from the player's money.
	 * 
	 * @param tower the Tower to add
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
	 * This method checks if the user clicked on a tower. If a tower was clicked on,
	 * then sell it back to the player and reference the selected tower.
	 * 
	 * @param x
	 * @param y
	 * @return True or false depending if a tower was sold back or not.
	 */
	public boolean sellTower(int x, int y) {				
		for (Tower tower : gameState.getTowers()) {			
			if (x > tower.getX() && x < tower.getX() + tower.getWidth() && 
					y > tower.getY() && y < tower.getY() + tower.getHeight()) {
				player.setMoney(player.getMoney() + (int)(tower.getCost() * Tower.SELLBACK_FACTOR));
				gameState.removeTower(tower);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets a specific tower by name by doing a lookup in the TowerType enum and then
	 * getting and instance of the class through reflection. If a tower of the 
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
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
				InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			object = new Tower();
		}
		
		return (Tower) object;
	}
	
	/**
	 * Gets a map of all of the tower names to its associated image.
	 * 
	 * @return A Map of String to Image with the key being the tower's name and
	 * the value being the tower's Image
	 */
	public Map<String, Image> getTowerImageMap() {
		Map<String, Image> imageMap = new HashMap<>();
		
		for (Entry<String, Class<? extends Tower>> towerEntry : towerMap.entrySet()) {
			try {
				Constructor<?> cons = towerEntry.getValue().getConstructor();
				Tower tower = (Tower)cons.newInstance();
				//Image newImage = tower.getImage().getScaledInstance(Tower.DEFAULT_WIDTH, Tower.DEFAULT_HEIGHT, Image.SCALE_DEFAULT);
				imageMap.put(towerEntry.getKey(), tower.getImage());
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
					InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
		return imageMap;
	}
	
	/**
	 * Returns a set of all of the possible tower names that can be
	 * added to the board.
	 * 
	 * @return A Set containing the names of all of the towers available
	 */
	public Set<String> getTowerNames() {
		return towerMap.keySet();
	}
	
	/**
	 * Adds a "path tile" to the game state. Used for keeping track of which portions
	 * of the map are going to be used as the path for enemies to take
	 * 
	 * @param x top left x coordinate of the tile
	 * @param y top left y coordinate of the tile
	 * @param width width of the tile in pixels
	 * @param height height of the tile in pixels
	 */
	public void addPathTile(int x, int y, int width, int height) {
		gameState.addPath(new Rectangle(x, y, width, height));
	}
	
	public void startGame() {
		Thread thread = new Thread(() -> {
			while (playing) {
				tick();
				
				try {
					Thread.sleep(TICK_SPEED);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
	
	public void newWave() {
		Enemy enemy = new Enemy(0, 25, 30, 30, 10, 0);
		Enemy enemy2 = new Enemy(0, 25, 30, 30, 0, 5);
		gameState.getEnemies().add(enemy);
		gameState.getEnemies().add(enemy2);
		playing = true;
		startGame();
	}
}
