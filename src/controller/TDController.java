package controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.Enemy;
import model.Entity;
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
	private List<Tower> towers;
	private List<Enemy> enemies;
	private List<Projectile> projectiles;
	private String TOWER_PACKAGE = "model.";
	
	public TDController(Player player) {
		this.player = player;
		this.towers = new ArrayList<>();
		this.enemies = new ArrayList<>();
		this.projectiles = new ArrayList<>();
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
	 * Subtracts a set amount from the player's health
	 * 
	 * @param amount the amount of health to subtract
	 */
	public void subtractHealth(int amount) {
		player.setHealth(player.getHealth() - amount);
	}
	
	/**
	 * Adds a tower to the current list of towers
	 * 
	 * @param tower the Tower to add
	 */
	public void addTower(Tower tower) {
		towers.add(tower);
	}
	
	/**
	 * Adds an enemy to the current list of enemies
	 * 
	 * @param enemy the Enemy to add
	 */
	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}
	
	/**
	 * Figures out if there is a collision on the board and updates the models
	 * accordingly.
	 * 
	 * TODO: Make this not garbage
	 */
	public void finalAllCollisions() {
		for (Projectile projectile : projectiles) {
			for (Enemy enemy : enemies) {
				if (getCollision(projectile, enemy)) {
					enemy.setHealth(enemy.getHealth() - projectile.getPower());
				}
			}
		}
	}
	
	/**
	 * Determines if there is a collision between two entities.
	 * 
	 * @param e1 the first Entity to check
	 * @param e2 the second Entity to check
	 * @return true if the two entities are colliding, false otherwise
	 */
	public boolean getCollision(Entity e1, Entity e2) {
		return e1.getX() < e2.getX() + e2.getWidth() &&
				e1.getX() + e1.getWidth() > e2.getX() &&
				e1.getY() < e2.getY() + e2.getHeight() &&
				e1.getY() + e1.getHeight() > e2.getY();
	}
	
	/**
	 * Gets an instance of a random tower specified by the TowerType enum
	 * 
	 * @return a random Tower
	 */
	public Tower getRandomTower() {
		Object object;
		try { 
			TowerType type = TowerType.randomTower();
			Class<?> c = Class.forName(TOWER_PACKAGE + type.getName());
			Constructor<?> cons = c.getConstructor(int.class, int.class, int.class, int.class, int.class);
			object = cons.newInstance(0, 0, 50, 50, 50);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
				InvocationTargetException | NoSuchMethodException | SecurityException |
				ClassNotFoundException e) {
			e.printStackTrace();
			object = new Tower(0, 0, 50, 50, 50);
		}
		
		return (Tower) object;
	}
	
	/**
	 * Gets a specific tower by name by doing a lookup in the TowerType enum and then
	 * getting and instance of the class through reflection. If a tower of the 
	 * specified name is not found, the default tower is returned instead.
	 * 
	 * @param name the name of the tower to get
	 * @return A Tower object
	 */
	public Tower getTowerByName(String name) {
		Object object;
		try { 
			TowerType type = TowerType.valueOf(name);
			Class<?> c = Class.forName(TOWER_PACKAGE + type.getName());
			Constructor<?> cons = c.getConstructor(int.class, int.class, int.class, int.class, int.class);
			object = cons.newInstance(0, 0, 50, 50, 50);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
				InvocationTargetException | NoSuchMethodException | SecurityException |
				ClassNotFoundException e) {
			e.printStackTrace();
			object = new Tower(0, 0, 50, 50, 50);
		}
		
		return (Tower) object;
	}
	
	/**
	 * Gets all of the towers in the TowerType name.
	 * 
	 * @return a List of all of the possible Tower objects
	 */
	public List<Tower> getAllTowers() {
		List<TowerType> towerTypes = Arrays.asList(TowerType.values());
		List<Tower> towers = new ArrayList<>();
		
		for (TowerType type : towerTypes) {
			try {
				Class<?> c = Class.forName(TOWER_PACKAGE + type.getName());
				Constructor<?> cons = c.getConstructor(int.class, int.class, int.class, int.class, int.class);
				Object object = cons.newInstance(0, 0, 50, 50, 50);
				towers.add((Tower)object);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
					InvocationTargetException | NoSuchMethodException | SecurityException |
					ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return towers;
	}
	
	/**
	 * The setTowerCoordinates method sets the coordinates of the
	 * tower placement.
	 * 
	 * @param tower A tower object that was placed.
	 * @param x The tower's x coordinate.
	 * @param y The tower's Y coordinate.
	 */
	public void setTowerCoordinates(Tower tower, int x, int y) {
		tower.setX(x);
		tower.setY(y);
	}
	
	public enum TowerType {
		CheapTower("CheapTower");
		
		private String name;
		
		private static final List<TowerType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
		private static final int SIZE = VALUES.size();
		private static final Random RANDOM = new Random();
		
		private TowerType(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public static TowerType randomTower()  {
			return VALUES.get(RANDOM.nextInt(SIZE));
		}
	}
}
