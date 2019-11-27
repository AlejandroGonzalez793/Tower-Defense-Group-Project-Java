package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


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
		for (Tower tower : towers) {
			if (tower.generateProjectile(ticks)) {
				projectiles.add(tower.getProjectile());
			}
		}
		
		for (Enemy enemy : enemies) {
			enemy.update();
		}
		
		for (Projectile projectile : projectiles) {
			projectile.update();
		}
		
		ticks++;
		setChanged();
		notifyObservers(this);
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
	
	public int getRound() {
		return round;
	}
	
	public void setRound(int round) {
		this.round = round;
	}
	
	public List<Tower> getTowers() {
		return towers;
	}
	
	public List<Enemy> getEnemies() {
		return enemies;
	}
	
	public List<Projectile> getProjectiles() {
		return projectiles;
	}
}
