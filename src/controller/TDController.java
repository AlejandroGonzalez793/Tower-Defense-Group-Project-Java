package controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import model.GameState;
import model.Node;
import model.Player;
import model.enemies.Balloon;
import model.enemies.Drifblim;
import model.enemies.Enemy;
import model.enemies.GreenPlane;
import model.enemies.HotAirBalloon;
import model.enemies.Pterosaur;
import model.enemies.RedHelicopter;
import model.projectiles.AreaTower;
import model.projectiles.Projectile;
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
	private double animationSpeed = 1.0;
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
		towerMap.put("PiercingTower", PiercingTower.class);
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
	 * This method returns the cost of a tower object by its associated name.
	 * 
	 * @param towerName A string that is the name of the tower object.
	 * @return The cost of the specified tower object.
	 */
	public int getTowerCost(String towerName) {
		return getTowerByName(towerName).getCost();
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
	
	public void speedUp() {
		animationSpeed = 0.5;
	}
	
	public void regularSpeed() {
		animationSpeed = 1.0;
	}
	
	public void slowDown() {
		animationSpeed = 2.0;
	}
	
	public void pause() {
		playing = !playing;
	}
    
	/**
	 * Creates a new thread that runs while the games is in progress. Calls the
	 * tick method to update all of the objects on the screen every
	 * {@value #TICK_SPEED} milliseconds.
	 */
	public void startGame() {
		AnimationTimer at = new AnimationTimer() {
			private long lastUpdate = 0;
			@Override
			public void handle(long now) {
				if (playing && now - lastUpdate >= (TICK_SPEED * animationSpeed) * 1000000) {
					lastUpdate = now;
					Enemy enemy = gameState.enemyContact();
					if (enemy != null)
					{
						player.setHealth(player.getHealth() - enemy.getPower());
						gameState.removeEnemy(enemy);
					}
					tick();
				}
			}
		};
		at.start();
	}
	
	/**
	 * Creates a new wave and starts the game loop
	 */
	public void newWave() {
		Rectangle start = gameState.getStart().getRectangle();
		int x = (int)start.getX();
		int y = (int)start.getY();
		
		Enemy enemy = new Pterosaur(x, y);
	    Enemy enemy2 = new GreenPlane(x, y);
	    Enemy enemy3 = new RedHelicopter(x, y);
	    Enemy enemy4 = new Balloon(x, y);
	    Enemy enemy5 = new HotAirBalloon(x, y);
	    Enemy enemy6 = new Drifblim(x, y);
		gameState.addEnemy(enemy);
		gameState.addEnemy(enemy2);
		gameState.addEnemy(enemy3);
		gameState.addEnemy(enemy4);
		gameState.addEnemy(enemy5);
		gameState.addEnemy(enemy6);
		playing = true;
		startGame();
	}
	
	/**
	 * Similar to new wave, just removes all enemies already on the board
	 */
	public void clearBoard() {
		for (Enemy enemy : gameState.getEnemies())
			gameState.removeEnemy(enemy);
		for (Tower tower : gameState.getTowers())
			gameState.removeTower(tower);
		playing = true;
		startGame();
	}
	
}
