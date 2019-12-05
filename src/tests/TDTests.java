package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.TDController;
import model.GameState;
import model.Player;
import model.enemies.Enemy;
import model.projectiles.Projectile;
import model.towers.Tower;
import view.TDView;

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
	
	@Test
	void testEnemy() {
		Enemy enemy = new Enemy(0, 0, 50, 50, 100, 5, 50, 10);
		Enemy enemy1 = new Enemy(0, 0, 50, 50, 5, 20, 10);
		Enemy enemy2 = new Enemy(0, 0, 50, 50);
		
		enemy.getHealth();
		enemy.getGold();
		enemy.getNode();
		
		enemy.setHealth(50);
		enemy.setGold(10000);
		enemy.setNode(null);
		enemy.getPower();
		enemy.setPower(100);
		
	}
	
	@Test
	void testTower() {
		Tower tower = new Tower();
		Tower tower1 = new Tower(0, 0, 50, 50, 150, 2, 100);
		Tower tower2 = new Tower(0, 0, 50, 50);
		
		tower.getRadius();
		tower.getCost();
		tower.getRate();
		tower.generateProjectile(1);
		tower.getProjectile();
		tower.generateProjectile(10);
		tower.getProjectile();
		tower.update();
	}
	
	@Test
	void testProjectile() {
		Projectile projectile = new Projectile(10, 10, 50, 50, 10, 5, 150);
		Projectile projectile1 = new Projectile(10, 10, 50, 50);
		Projectile projectile2 = new Projectile(10, 10);
		
		projectile.setHeight(45);
		projectile.setWidth(45);
		
		projectile.setDx(1.0);
		projectile.setDy(1.0);
		
		projectile.setSpeed(50);
		
		projectile.getImage();
		projectile.setImage(null);
		
		projectile.setX(5);
		projectile.setY(5);
		
		projectile.getDx();
		projectile.getDy();
		
		projectile.getX();
		projectile.getY();
		
		projectile.update();
		
		projectile.getHeight();
		projectile.getWidth();
		
		projectile.getPower();
		
		projectile.getSpeed();
		
		projectile.getRadius();
		
		projectile.getDistance();
		projectile.setDistance();
	}
	
	@Test
	void testControllerGameOver() {
		TDView view = new TDView();
		
		Player player = new Player(view);
		Player player1 = new Player(view, 100);
		Player player2 = new Player(view, 0, 100);
		Player player3 = new Player();
		
		GameState gameState = new GameState(view);
		
		TDController controller = new TDController(player, gameState);
		TDController controller1 = new TDController(player2, gameState);
		
		assertFalse(controller.isGameOver());
		assertTrue(controller1.isGameOver());
	}
}
