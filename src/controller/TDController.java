package controller;

import java.util.ArrayList;
import java.util.List;

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
}
