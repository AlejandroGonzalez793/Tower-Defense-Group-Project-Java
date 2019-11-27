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
	private Map<String, Class<? extends Tower>> towerMap;
	
	public TDController(Player player, GameState gameState) {
		this.player = player;
		this.gameState = gameState;
		
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
	 * Adds a tower to the current list of towers and subtracts
	 * the money from the player
	 * 
	 * @param tower the Tower to add
	 */
	public boolean addTower(int x, int y, String name) {
		Tower tower = getTowerByName(name);
		tower.setX(x);
		tower.setY(y);

		if (tower.getCost() <= player.getMoney()) {
			player.setMoney(player.getMoney() - tower.getCost());
			gameState.getTowers().add(tower);
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
	 * Returns a set of all of the possible tower names that can be
	 * added to the board.
	 * 
	 * @return A Set containing the names of all of the towers available
	 */
	public Set<String> getTowerNames() {
		return towerMap.keySet();
	}
}
