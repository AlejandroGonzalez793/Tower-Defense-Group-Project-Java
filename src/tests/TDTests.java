package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.TDController;
import javafx.stage.Stage;
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
		
		enemy.setHealth(50);
		assertEquals(enemy.getHealth(), 50);
		
		enemy.setGold(10000);
		assertEquals(enemy.getGold(), 10000);
		
		assertNotNull(enemy.getNode());
		enemy.setNode(null);
		assertNull(enemy.getNode());
		
		enemy.setPower(100);
		assertEquals(enemy.getPower(), 100);	
	}
	
	@Test
	void testTower() {
		Tower tower1 = new Tower();
		Tower tower = new Tower(0, 0, 50, 50, 150, 2, 100);
		Tower tower2 = new Tower(0, 0, 50, 50);
		
		tower.getRadius();
		assertEquals(tower.getRadius(), 150);
		
		tower.getCost();
		assertEquals(tower.getCost(), 100);
		
		tower.getRate();
		assertEquals(tower.getRate(), 2);
		
		assertFalse(tower.generateProjectile(1));
		
		assertTrue(tower.generateProjectile(10));
		assertNotNull(tower.getProjectile());
		
		tower.update();
		assertEquals(tower.getX(), 0);
		assertEquals(tower.getY(), 0);
	}
	
	@Test
	void testProjectile() {
		Projectile projectile = new Projectile(10, 10, 50, 50, 10, 5, 150);
		Projectile projectile1 = new Projectile(10, 10, 50, 50);
		Projectile projectile2 = new Projectile(10, 10);
		
		projectile.setHeight(45);
		projectile.setWidth(45);
		assertEquals(projectile.getHeight(), 45);
		assertEquals(projectile.getWidth(), 45);
		
		projectile.setDx(1.0);
		projectile.setDy(1.0);
		assertEquals(projectile.getDx(), 1.0);
		assertEquals(projectile.getDy(), 1.0);
		
		projectile.setSpeed(50);
		assertEquals(projectile.getSpeed(), 50);
		
		projectile.setImage(null);
		assertNotNull(projectile.getImage());
		
		projectile.setX(5);
		projectile.setY(5);
		assertEquals(projectile.getX(), 5);
		assertEquals(projectile.getY(), 5);
		
		projectile.update();
		assertEquals(projectile.getX(), 6);
		assertEquals(projectile.getY(), 6);
		
		assertEquals(projectile.getPower(), 5);
		
		projectile.getRadius();
		assertEquals(projectile.getRadius(), 150);
		
		assertEquals(projectile.getDistance(), 0);
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
	
	@Test 
	void testControllerPurchaseTower() {
		String tower = "Tower";
		
		TDView view = new TDView();
		
		Player player = new Player(view);
		Player player1 = new Player(view, 100, 0);
		
		GameState gameState = new GameState(view);
		
		TDController controller = new TDController(player, gameState);
		TDController controller1 = new TDController(player1, gameState);
		
		assertTrue(controller.canPurchaseTower(tower));
		assertFalse(controller1.canPurchaseTower(tower));
		assertFalse(controller1.canPurchaseTower(null));
	}
	
	@Test
	void testControllerPlaceTower() {
		String tower = "Tower";
		String tower1 = "Tower";
		
		TDView view = new TDView();
		
		Player player = new Player(view);
		Player player1 = new Player(view, 100, 0);
		
		GameState gameState = new GameState(view);
		
		TDController controller = new TDController(player, gameState);
		TDController controller1 = new TDController(player1, gameState);
		
		controller.canPurchaseTower(tower);
		assertTrue(controller.canPlaceTower(0, 0));
		controller.addTower(0, 0);
		
		controller.canPurchaseTower(tower1);
		assertFalse(controller.canPlaceTower(0, 0));
	}
}
