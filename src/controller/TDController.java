package controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import model.GameState;
import model.Player;
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
	
	public TDController(Player player, GameState gameState) {
		this.player = player;
		this.gameState = gameState;
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
	 * Adds the current selected tower to the tower list and 
	 * subtracts the toer's cost from the player's money.
	 * 
	 * @param tower the Tower to add
	 */
	public void addTower(int x, int y) {
		// TODO: Do something if no selected tower
		if (selectedTower != null) {
			selectedTower.setX(x);
			selectedTower.setY(y);
			
			player.setMoney(player.getMoney() - selectedTower.getCost());
			gameState.getTowers().add(selectedTower);
			selectedTower = null;
		}
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
	 * Returns a set of all of the possible tower names that can be
	 * added to the board.
	 * 
	 * @return A Set containing the names of all of the towers available
	 */
	public Set<String> getTowerNames() {
		return towerMap.keySet();
	}
}
