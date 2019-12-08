package model;

import javafx.scene.shape.Rectangle;
import model.enemies.Balloon;
import model.enemies.Drifblim;
import model.enemies.Enemy;
import model.enemies.GreenPlane;
import model.enemies.HotAirBalloon;
import model.enemies.Pterosaur;
import model.enemies.RedHelicopter;

public class Waves {
	private GameState gameState;
	
	public Waves(GameState gameState) {
		this.gameState = gameState;
	}
	
	public void getWave(int waveNum) {
		Rectangle start = gameState.getStart().getRectangle();
		int x = (int)start.getX();
		int y = (int)start.getY();
		
		if (waveNum == 0) {
			Enemy enemy = new Balloon(x, y);
			Enemy enemy2 = new HotAirBalloon(x, y);
			Enemy enemy3 = new GreenPlane(x, y);
			gameState.addEnemy(enemy);
			gameState.addEnemy(enemy2);
			gameState.addEnemy(enemy3);
		} else if (waveNum == 1) {
			Enemy enemy = new Balloon(x, y);
			Enemy enemy2 = new HotAirBalloon(x, y);
			Enemy enemy3 = new RedHelicopter(x, y);
			gameState.addEnemy(enemy);
			gameState.addEnemy(enemy2);
		    gameState.addEnemy(enemy3);
		} else if (waveNum == 2) {
			Enemy enemy = new GreenPlane(x, y);
			Enemy enemy2 = new Pterosaur(x, y);
			gameState.addEnemy(enemy2);
			gameState.addEnemy(enemy);
		} else if (waveNum == 3) {
			Enemy enemy = new RedHelicopter(x, y);
			Enemy enemy2 = new Drifblim(x, y);
			Enemy enemy3 = new GreenPlane(x, y);
			gameState.addEnemy(enemy);
			gameState.addEnemy(enemy2);
			gameState.addEnemy(enemy3);
		} else if (waveNum == 4) {
			Enemy enemy = new Pterosaur(x, y);
		    Enemy enemy2 = new GreenPlane(x, y);
		    Enemy enemy3 = new RedHelicopter(x, y);
		    Enemy enemy4 = new Balloon(x, y);
		    Enemy enemy5 = new HotAirBalloon(x, y);
		    Enemy enemy6 = new Drifblim(x, y);
		    gameState.addEnemy(enemy);
		    gameState.addEnemy(enemy2);
		    gameState.addEnemy(enemy3);
		    gameState.addEnemy(enemy4);
		    gameState.addEnemy(enemy5);
		    gameState.addEnemy(enemy6);
		}
	}

}
