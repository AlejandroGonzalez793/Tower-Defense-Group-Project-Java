package controller;

import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import model.Enemy;
import model.GameState;
import model.Player;
import model.Projectile;
import model.Stage;
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
	private Stage stage;
	private GameState gameState;
	private Tower selectedTower;
	private Map<String, Class<? extends Tower>> towerMap;
	
	public TDController(Player player, GameState gameState) {
		this.player = player;
		this.gameState = gameState;
		this.stage = new Stage();
		this.selectedTower = null;
		
		this.towerMap = new HashMap<String, Class<? extends Tower>>();
		towerMap.put("Tower", Tower.class);
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
	
	/**
	 * This method checks if the user clicked on a tower. If a tower was clicked on,
	 * then sell it back to the player and reference the selected tower.
	 * 
	 * @param x
	 * @param y
	 * @return True or false depending if a tower was sold back or not.
	 */
	public boolean canSellTower(int x, int y) {
		Rectangle sellRec = new Rectangle(x-(Tower.DEFAULT_WIDTH / 4), y+(Tower.DEFAULT_HEIGHT / 4), 
				Tower.DEFAULT_WIDTH/2, Tower.DEFAULT_HEIGHT/2);
				
		//check tower positions
		for (Tower tower : gameState.getTowers())
		{
			Rectangle towerRec = new Rectangle(tower.getX()-(tower.getWidth() / 2),tower.getY()+(tower.getHeight() / 2),
					tower.getWidth(), tower.getHeight());
					 
				if (sellRec.intersects(towerRec))
				{
					System.out.println("Tower sold back!");
					player.setMoney(player.getMoney() + (int)Math.round(tower.getCost() * Tower.SELLBACK_FACTOR));
					selectedTower = tower;
					return true;
				}
		}
		return false;
	}
	
	public boolean canPlaceTower(int x, int y) {
		List<Line> path = stage.getLines();
		int pathWidth = stage.getPathWidth() / 2;
		
		for (Line line : path) {
			if (Line2D.ptLineDist(line.getStartX(), line.getStartY(), 
					line.getEndX(), line.getEndY(), x, y) < pathWidth) {
				return false;
			}
		}
		
		// using rectangles for collision boxes
		Rectangle selectedTowerRec = new Rectangle(x-(selectedTower.getWidth() / 2), y+(selectedTower.getHeight() / 2), 
				selectedTower.getWidth(), selectedTower.getHeight());
		
		//check tower positions
		for (Tower tower : gameState.getTowers())
		{
			Rectangle towerRec = new Rectangle(tower.getX()-(tower.getWidth() / 2),tower.getY()+(tower.getHeight() / 2),
					tower.getWidth(), tower.getHeight());
			 
			 if (selectedTowerRec.intersects(towerRec))
			 {
				 System.out.println("Collision with towers!");
				 return false;
			 }
		}
		return true;
	}
	
	/**
	 * Check bullet collisions with enemies using rectangles.
	 * 
	 * @param bullet object to be checked with a collision.
	 * @return True or false depending if a collision was made or not.
	 */
	public boolean checkBulletCollision(Projectile bullet)
	{
		Rectangle bulletHitBox = new Rectangle(bullet.getX()-(bullet.getWidth() / 2), bullet.getY()+(bullet.getHeight() / 2), 
				bullet.getWidth(), bullet.getHeight());
		
		for (Enemy enemy : gameState.getEnemies())
		{
			Rectangle enemyHitBox = new Rectangle(enemy.getX()-(enemy.getWidth() / 2), enemy.getY()+(enemy.getHeight() / 2),
					enemy.getWidth(), enemy.getHeight());
			 
			 if (bulletHitBox.intersects(enemyHitBox))
			 {
				 System.out.println("Collision with enemy!");
				 enemy.setHealth(enemy.getHealth() - bullet.getPower());
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
	 * Removes a tower from the current list of towers.
	 * 
	 * @param tower the Tower to remove
	 * @return boolean if the remove was successful or not.
	 */
	public boolean deleteTower() {
		// TODO: Do something if no selected tower
		if (selectedTower != null) {
			gameState.removeTower(selectedTower);
			selectedTower = null;
			return true;
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

}
