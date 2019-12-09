package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.TDController;
import javafx.scene.shape.Rectangle;
import model.GameState;
import model.Node;
import model.Player;
import model.Waves;
import model.enemies.Enemy;
import model.projectiles.Projectile;
import model.towers.Tower;
import util.ResourceManager;
import view.TDView;

public class TDTests {
	/**
	 * Tests if there is a collision between a projectile and enemy. Both the
	 * projectile and enemy start at the coordinates 10,10, so they are trivially
	 * colliding
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
	 * partially overlapping vertically (e.g. the projectile hitting the enemy
	 * directly from the top)
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
	 * partially overlapping horizontally (e.g. the projectile hitting the enemy
	 * directly from the size)
	 */
	@Test
	void testHorizonalCollision() {
		TDController controller = new TDController(null, null);
		Projectile projectile = new Projectile(30, 20); // x from 30 to 40, y from 20 to 30
		Enemy enemy = new Enemy(34, 10, 50, 50); // x from 35 to 85, y from 10 to 50

		assertTrue(controller.getCollision(projectile, enemy));
	}

	/**
	 * Tests setting and getting the fields in a default enemy
	 */
	@Test
	void testEnemy() {
		Enemy enemy = new Enemy(0, 0, 50, 50, 100, 5, 50, 10);

		enemy.setHealth(50);
		enemy.setGold(10000);
		enemy.setPower(100);
		Node node = new Node(new Rectangle(0, 0, 50, 50));
		Node nodeNext = new Node(new Rectangle(0, 0, 50, 50));
		node.setNext(nodeNext);
		enemy.setNode(node);

		assertEquals(50, enemy.getHealth());
		assertEquals(10000, enemy.getGold());
		assertEquals(100, enemy.getPower());
		assertEquals(node, enemy.getNode());

	}

	/**
	 * Tests setting and getting the fields in the default towers
	 */
	@Test
	void testTower() {
		Tower tower1 = new Tower();
		Tower tower = new Tower(0, 0, 50, 50, 150, 2, 100);
		Tower tower2 = new Tower(0, 0, 50, 50);

		assertNotNull(tower);
		assertNotNull(tower1);
		assertNotNull(tower2);

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

	/**
	 * Tests setting and getting the fields in the default Projectile
	 */
	@Test
	void testProjectile() {
		Projectile projectile = new Projectile(10, 10, 50, 50, 10, 5, 150);
		assertNotNull(projectile);

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

	/** 
	 * Tests if the game is not over through the controller. If the controller has 
	 * just been created, the game should not be considered over. 
	 */ 
	@Test 
	void testIsGameNotOver() { 
		Player player = new Player(null); 
 
		assertNotNull(player); 
 
		GameState gameState = new GameState(null); 
		gameState.setStart(new Node(new Rectangle(0, 0, 10, 10))); 
 
		TDController controller = new TDController(player, gameState); 
 
		assertTrue(controller.isNewRound()); 
		assertFalse(controller.getIsPlaying()); 
		assertEquals(0, controller.getWaveNumber()); 
		assertFalse(controller.isGameOver()); 
		assertFalse(controller.isPlayerDead()); 
		 
		controller.setWaveNumber(4); 
		controller.nextWave(); 
		assertFalse(controller.isGameOver()); 
	} 

	/**
	 * Tests if the game is over. If the wave number is 5 or greater, the game
	 * should be complete.
	 */
	@Test
	void testIsGameOver() {
		Player player = new Player();
		TDController controller = new TDController(player, new GameState(null));
		controller.setWaveNumber(5);
		assertTrue(controller.isGameOver());

		player.setHealth(0);
		assertTrue(controller.isPlayerDead());
	}

	/**
	 * Tests if we can purchase a tower. A player's starting money is 1000 and the
	 * default tower's cost is less than though, so we should be able to purchase
	 * it.
	 */
	@Test
	void testControllerPurchaseTower() {
		TDController controller = new TDController(new Player(), new GameState(null));
		assertTrue(controller.canPurchaseTower("Tower"));

		// selects default tower instead
		assertTrue(controller.canPurchaseTower("Some random tower that doesn't exist"));
	}

	/**
	 * Tests if we can't purchase a tower because we don't have enough money
	 */
	@Test
	void testControllerCantPurchaseTower() {
		TDController controller = new TDController(new Player(), new GameState(null));
		controller.addGold(-1000);
		assertFalse(controller.canPurchaseTower("Tower"));
	}

	/**
	 * Test if we can place a tower on the board.
	 */
	@Test
	void testControllerPlaceTower() {
		TDController controller = new TDController(new Player(), new GameState(null));

		controller.addTower(0, 0); // Nothing should happen since no tower is selected

		controller.canPurchaseTower("Tower");
		assertTrue(controller.canPlaceTower(50, 50, 500, 500));
		controller.addTower(50, 50);
	}

	/**
	 * Tests if there are any bullet collisions with enemies.
	 */
	@Test
	void checkBulletCollision() {
		Projectile projectile = new Projectile(0, 0, 50, 50);
		GameState gameState = new GameState(null);
		TDController controller = new TDController(new Player(), gameState);

		assertFalse(controller.checkBulletCollision(projectile));

		gameState.addEnemy(new Enemy(100, 100, 1, 1));
		gameState.addEnemy(new Enemy(0, 0, 10, 10));
		assertTrue(controller.checkBulletCollision(projectile));
	}

	/**
	 * Tests that a user can sell a tower.
	 */
	@Test
	void testSellTower() {
		TDController controller = new TDController(new Player(), new GameState(null));

		controller.canPurchaseTower("Tower");
		controller.canPlaceTower(50, 50, 500, 500);
		controller.addTower(50, 50);

		assertFalse(controller.sellTower(0, 0));
		assertTrue(controller.sellTower(50, 50));
	}

	/**
	 * Tests by getting the tower price.
	 */
	@Test
	void testTowerCost() {
		TDController controller = new TDController(new Player(), new GameState(null));
		assertEquals(controller.getTowerCost("Tower"), 50);
	}

	/**
	 * Tests the wave class to make sure it is returning enemies on whichever wave.
	 */
	@Test
	void testWaves() {
		assertNotNull(new Waves());
		for (int i = 0; i < 6; i++) {
			assertNotNull(Waves.getWave(i, 0, 0));
		}
	}

	/**
	 * Tests the player class to make sure the constructors function properly.
	 */
	@Test
	void testPlayer() {
		assertNotNull(new Player());
		assertNotNull(new Player(new TDView()));
		assertNotNull(new Player(new TDView(), 1000));
		assertNotNull(new Player(new TDView(), 1000, 1000));
	}
	
	@Test
	void testTick() {
		GameState state = new GameState(null);
		
		TDController controller = new TDController(new Player(), state);
		
		for (int i = 0; i < 10; i++) {
			controller.addPathTile(i, 0, 50, 50);
		}
		
		controller.setWaveNumber(1);
		controller.nextWave();
		
		controller.canPurchaseTower("Tower");
		controller.canPlaceTower(50, 50, 500, 500);
		controller.addTower(50, 50);
		
		for (int i = 0; i < 10; i++) {
			state.tick();
		}
	}

}
