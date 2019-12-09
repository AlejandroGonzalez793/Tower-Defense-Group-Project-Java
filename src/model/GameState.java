package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.shape.Rectangle;
import model.enemies.Enemy;
import model.projectiles.Projectile;
import model.towers.Tower;

/**
 * Contains the game state for the current game. Holds lists of all of the
 * towers, enemies, projectiles, and can update them at every tick.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu)
 */
public class GameState extends Observable {
	private List<Tower> towers;
	private List<Enemy> enemies;
	private List<Projectile> projectiles;
	private List<Rectangle> deadzones;
	private Node start;
	private Node end;
	private int ticks;
	private int round;
	private int nEnemy;

	public GameState(Observer o) {
		this.towers = new ArrayList<>();
		this.enemies = new ArrayList<>();
		this.projectiles = new ArrayList<>();
		this.deadzones = new ArrayList<>();
		this.nEnemy = 0;

		if (o != null) {
			addObserver(o);
		}
	}

	/**
	 * Updates all of the enemies, towers, and projectiles currently active in the
	 * game (in that order).
	 * 
	 * For each enemy in the game, we first check to see if it has passed over its
	 * current node in the path and if it has, increment the node. We then updte the
	 * enemies' positions by calling their respective update method.
	 * 
	 * For each tower in the game, we iterate through all of the enemies to find the
	 * first one in its radius, and if it can generate a projectile we generate a
	 * projectile and point it in the direction of the enemy.
	 * 
	 * For each projectile in the game, we call its respective update method to
	 * update its position on the board.
	 */
	public void tick() {
		for (int i = 0; i < enemies.size(); i++) {
			if (nEnemy != enemies.size() && i == nEnemy ) {
				nEnemy++;
				break;
			}
			Enemy enemy = enemies.get(i);
			Node node = enemy.getNode();
			if (node != null) {
				Rectangle rect = node.getRectangle();
				if (enemy.getX() > rect.getX() + rect.getWidth() || enemy.getX() < rect.getX() - rect.getWidth()) {
					enemy.incrementNode();
				} else if (enemy.getY() > rect.getY() + rect.getHeight()
						|| enemy.getY() < rect.getY() - rect.getHeight()) {
					enemy.incrementNode();
				}
				enemy.update();
			}
			
			
		}

		for (Tower tower : towers) {
			if (tower.generateProjectile(ticks)) {
				for (Enemy enemy : enemies) {
					double x = Math.abs(enemy.getX() - tower.getX());
					double y = Math.abs(enemy.getY() - tower.getY());
					double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
					if (distance <= tower.getRadius()) {
						Projectile projectile = tower.getProjectile();
						
						if (x < y) {
							y /= x;
							x = 1;
						} else {
							x /= y;
							y = 1;
						}
						
						if (enemy.getX() < tower.getX()) {
							x = -x-projectile.getSpeed();
						}else {
							x = x+projectile.getSpeed();
						}

						if (enemy.getY() < tower.getY()) {
							y = -y-projectile.getSpeed();
						}else {
							y = y + projectile.getSpeed(); // add speed here
						}
						projectile.setDx(x);
						projectile.setDy(y);
						projectiles.add(projectile);
						break;
					}
				}
			}
		}

		for (Projectile projectile : projectiles) {
			projectile.update();
		}

		ticks++;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Adds a tower to the list of towers currently in the game and notifies
	 * observers of this observable.
	 * 
	 * @param tower the Tower to add to the game
	 */
	public void addTower(Tower tower) {
		towers.add(tower);
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Removes a tower from the list of towers currently in the game and notifies
	 * observers of this observable.
	 * 
	 * @param tower the Tower to remove from the game
	 */
	public void removeTower(Tower tower) {
		towers.remove(tower);
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Adds an enemy to the game state and associates its position with the first
	 * node in the list of path nodes.
	 * 
	 * @param enemy the Enemy to add to the game
	 */
	public void addEnemy(Enemy enemy) {
		enemy.setNode(start);
		enemies.add(enemy);
	}

	/**
	 * Removes an enemy from the list of enemies and notifies observers of this
	 * observable.
	 * 
	 * @param enemy the Enemy to remove from the list of enemies
	 */
	public void removeEnemy(Enemy enemy) {
		enemies.remove(enemy);
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Gets the list of towers in the current GameState.
	 * 
	 * @return towers is a list of towers in the current GameState.
	 */
	public List<Tower> getTowers() {
		return towers;
	}

	/**
	 * Gets the list of enemies in the current GameState.
	 * 
	 * @return enemies is a list of enemies in the current GameState.
	 */
	public List<Enemy> getEnemies() {
		return enemies;
	}

	/**
	 * Gets the list of projectiles in the current GameState.
	 * 
	 * @return towers is a list of projectiles in the current GameState.
	 */
	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	/**
	 * Gets the current round that the game is in.
	 * 
	 * @return
	 */
	public int getRound() {
		return round;
	}

	/**
	 * Sets the next round in the game.
	 * 
	 * @param round the next round in the game.
	 */
	public void setRound(int round) {
		this.round = round;
	}

	/**
	 * Sets the starting position where the enemies first appear on the board.
	 * 
	 * @param start a node that indicates the starting positioning for the enemies.
	 */
	public void setStart(Node start) {
		this.start = start;
	}

	/**
	 * Gets the starting position node where the enemies will first appear on the
	 * board.
	 * 
	 * @return start a node that indicates the starting positioning for the enemies.
	 */
	public Node getStart() {
		return start;
	}

	/**
	 * Adds a node to the end of the path node linked list
	 * 
	 * @param node the node to add to the end of the list
	 */
	public void addNode(Node node) {
		if (start == null) {
			start = node;
			end = node;
			return;
		}

		end.setNext(node);
		end = node;
	}
	
	public void addDeadzones(Rectangle zone) {
		this.deadzones.add(zone);
	}
	
	public List<Rectangle> getDeadZones() {
		return this.deadzones;
	}

	/**
	 * Checks if enemy has gone off the board.
	 * 
	 * @return enemy or null is returned to indicate if enemy has gone off the
	 *         board.
	 */
	public Enemy enemyContact() {
		if (end == null) {
			return null;
		}

		for (Enemy enemy : enemies) {
			Node node = enemy.getNode();
			if (node == end) {
				return enemy;
			}
		}
		return null;
	}
	
	public void setEnemies(List<Enemy> enemies) {
		for (Enemy enemy : enemies) {
			enemy.setNode(start);
		}
		
		this.enemies = enemies;
	}
	
	public void resetProjectiles() {
		this.projectiles = new ArrayList<>();
	}
}
