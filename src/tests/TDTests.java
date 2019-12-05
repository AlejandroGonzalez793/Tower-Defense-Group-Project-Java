package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.TDController;
import model.GameState;
import model.enemies.Enemy;
import model.projectiles.Projectile;

public class TDTests {
	
	/**
	 * Tests if there is a collision between a projectile and enemy. Both the projectile and
	 * enemy start at the coordinates 10,10, so they are trivially colliding
	 */
	@Test
	void testCollision() {
		TDController controller = new TDController(null, null);
		Projectile projectile = new Projectile(10, 10); // x = 10, y = 10, radius = 10
		Enemy enemy = new Enemy(10, 10, 50, 50); // x = 25, y = 25, width = 50, height = 50
		
		assertTrue(controller.getCollision(projectile, enemy));
	}
	
	/**
	 * Tests if there is a collision between a projectile and enemy that are not
	 * colliding. 
	 */
	@Test
	void testNoCollision() {
		TDController controller = new TDController(null, null);
		Projectile projectile = new Projectile(10, 10); // x = 10, y = 10, radius = 10
		Enemy enemy = new Enemy(100, 100, 50, 50); // x = 100, y = 100, width = 50, height = 50
		
		assertFalse(controller.getCollision(projectile, enemy));
	}
	
	/**
	 * Tests if there is a collision between a projectile and enemy that are
	 * partially overlapping vertically (e.g. the projectile hitting the 
	 * enemy directly from the top)
	 */
	@Test
	void testVerticalCollision() {
		TDController controller = new TDController(null, null);
		Projectile projectile = new Projectile(30, 20); // x from 30 to 40, y from 20 to 30
		Enemy enemy = new Enemy(10, 25, 50, 50); // x from 10 to 60, y from 25 to 75
		
		assertTrue(controller.getCollision(projectile, enemy));
	}
	
	/**
	 * Tests if there is a collision between a projectile and enemy that are
	 * partially overlapping horizontally (e.g. the projectile hitting the 
	 * enemy directly from the size)
	 */
	@Test
	void testHorizonalCollision() {
		TDController controller = new TDController(null, null);
		Projectile projectile = new Projectile(30, 20); // x from 30 to 40, y from 20 to 30
		Enemy enemy = new Enemy(34, 10, 50, 50); // x from 35 to 85, y from 10 to 50
		
		assertTrue(controller.getCollision(projectile, enemy));
	}
}
