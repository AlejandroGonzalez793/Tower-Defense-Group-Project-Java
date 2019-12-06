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
	private Node start;
	private Node end;
	private int ticks;
	private int round;
	
	public GameState(Observer o) {
		this.towers = new ArrayList<>();
		this.enemies = new ArrayList<>();
		this.projectiles = new ArrayList<>();
		
		if (o != null) {
			addObserver(o);
		}
	}
	
	public void tick() {        
		for (Enemy enemy : enemies) {
			Node node = enemy.getNode();
			if (node != null) {
				Rectangle rect = node.getRectangle();
				if (enemy.getX() > rect.getX() + rect.getWidth() || enemy.getX() < rect.getX() - rect.getWidth()) {
					enemy.incrementNode();
				} else if (enemy.getY() > rect.getY() + rect.getHeight() || enemy.getY() < rect.getY() - rect.getHeight()) {
					enemy.incrementNode();
				}
				enemy.update();
			}
		}
		
		for (Tower tower : towers) {
			for (Enemy enemy : enemies) {				
				double x = Math.abs(enemy.getX() - tower.getX());
				double y = Math.abs(enemy.getY() - tower.getY());
				double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
				if (distance <= tower.getRadius()) {
					if (tower.generateProjectile(ticks)) {
						Projectile projectile = tower.getProjectile();
						
						if (x < y) {
							y /= x;
							x = 1;
						} else { 
							x /= y;
							y = 1;
						}
						
						if (enemy.getX() < tower.getX()) {
							x = -x;
						}
						
						if (enemy.getY() < tower.getY()) {
							y = -y;
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
	
	public int getRound() {
		return round;
	}
	
	public void addTower(Tower tower) {
		towers.add(tower);
		setChanged();
		notifyObservers(this);
	}
	
	public void removeTower(Tower tower) {
		towers.remove(tower);
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * Sets the next round in the game.
	 * @param round the next round in the game.
	 */
	public void setRound(int round) {
		this.round = round;
	}
	
	/**
	 * Gets the list of towers in the current GameState.
	 * @return towers is a list of towers in the current GameState.
	 */
	public List<Tower> getTowers() {
		return towers;
	}
	
	/**
	 * Gets the list of enemies in the current GameState.
	 * @return enemies is a list of enemies in the current GameState.
	 */
	public List<Enemy> getEnemies() {
		return enemies;
	}
	
	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}
	
	/**
	 * Gets the list of projectiles in the current GameState.
	 * @return towers is a list of projectiles in the current GameState.
	 */
	public List<Projectile> getProjectiles() {
		return projectiles;
	}
	
	public void resetProjectiles() {
		this.projectiles = new ArrayList<>();
	}
	
	/**
	 * Sets the starting position where the enemies first appear on the board.
	 * @param start a node that indicates the starting positioning for the enemies.
	 */
	public void setStart(Node start) {
		this.start = start;
	}
	
	/**
	 * Gets the starting position node where the enemies will first appear on 
	 * the board.
	 * @return start a node that indicates the starting positioning for the enemies.
	 */
	public Node getStart() {
		return start;
	}
	
	public void addEnemy(Enemy enemy) {
		enemy.setNode(start);
		enemies.add(enemy);
	}
	
	public void removeEnemy(Enemy enemy) {
		enemies.remove(enemy);
		setChanged();
		notifyObservers(this);
	}
	
	public void addNode(Node node) {
		if (start == null) {
			start = node;
			return;
		}
		
		Node curr = start;
		while (curr.getNext() != null) {
			curr = curr.getNext();
		}
		
		curr.setNext(node);
		end = node;
	}
	
	/**
	 * Checks if enemy has gone off the board.
	 * 
	 * @return enemy or null is returned to indicate if enemy has gone 
	 * off the board.
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
}
