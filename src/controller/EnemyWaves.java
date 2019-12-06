package controller;

import javafx.scene.shape.Rectangle;
import model.GameState;
import model.enemies.Balloon;
import model.enemies.Drifblim;
import model.enemies.Enemy;
import model.enemies.GreenPlane;
import model.enemies.HotAirBalloon;
import model.enemies.Pterosaur;
import model.enemies.RedHelicopter;

public class EnemyWaves {

	public static void enemyWave(GameState gameStateRef, int waveNumber) {
		Rectangle start = gameStateRef.getStart().getRectangle();
		int x = (int)start.getX();
		int y = (int)start.getY();
		
		if (waveNumber == 0) {
			Enemy enemy = new Pterosaur(x, y);
		    Enemy enemy2 = new GreenPlane(x, y);
		    Enemy enemy3 = new RedHelicopter(x, y);
		    Enemy enemy4 = new Balloon(x, y);
		    Enemy enemy5 = new HotAirBalloon(x, y);
		    Enemy enemy6 = new Drifblim(x, y);
			gameStateRef.addEnemy(enemy);
			gameStateRef.addEnemy(enemy2);
			gameStateRef.addEnemy(enemy3);
			gameStateRef.addEnemy(enemy4);
			gameStateRef.addEnemy(enemy5);
			gameStateRef.addEnemy(enemy6);
			
			Enemy enemy7 = new Drifblim(x, y);
			Enemy enemy8 = new Drifblim(x, y);
			Enemy enemy9 = new Drifblim(x, y);
			Enemy enemy10 = new Drifblim(x, y);
			gameStateRef.addEnemy(enemy7);
			gameStateRef.addEnemy(enemy8);
			gameStateRef.addEnemy(enemy9);
			gameStateRef.addEnemy(enemy10);
		} else if (waveNumber == 1) {
			Enemy enemy = new Pterosaur(x, y);
			gameStateRef.addEnemy(enemy);
		} else if (waveNumber == 2) {
			Enemy enemy = new GreenPlane(x, y);
			gameStateRef.addEnemy(enemy);
		} else if (waveNumber == 3) {
			Enemy enemy = new RedHelicopter(x, y);
			gameStateRef.addEnemy(enemy);
		} else if (waveNumber == 4) {
			Enemy enemy = new Pterosaur(x, y);
			Enemy enemy2 = new Drifblim(x, y);
			Enemy enemy3 = new GreenPlane(x, y);
			gameStateRef.addEnemy(enemy);
			gameStateRef.addEnemy(enemy2);
			gameStateRef.addEnemy(enemy3);
		}
	}
	
	public static void stage1Wave(GameState gameStateRef, int waveNumber) {
		Rectangle start = gameStateRef.getStart().getRectangle();
		int x = (int)start.getX();
		int y = (int)start.getY();
		
		if (waveNumber == 0) {
			Enemy enemy = new Pterosaur(x, y);
		    Enemy enemy2 = new GreenPlane(x, y);
		    Enemy enemy3 = new RedHelicopter(x, y);
		    Enemy enemy4 = new Balloon(x, y);
		    Enemy enemy5 = new HotAirBalloon(x, y);
		    Enemy enemy6 = new Drifblim(x, y);
			gameStateRef.addEnemy(enemy);
			gameStateRef.addEnemy(enemy2);
			gameStateRef.addEnemy(enemy3);
			gameStateRef.addEnemy(enemy4);
			gameStateRef.addEnemy(enemy5);
			gameStateRef.addEnemy(enemy6);
			
			Enemy enemy7 = new Drifblim(x, y);
			Enemy enemy8 = new Drifblim(x, y);
			Enemy enemy9 = new Drifblim(x, y);
			Enemy enemy10 = new Drifblim(x, y);
			gameStateRef.addEnemy(enemy7);
			gameStateRef.addEnemy(enemy8);
			gameStateRef.addEnemy(enemy9);
			gameStateRef.addEnemy(enemy10);
		} else if (waveNumber == 1) {
			Enemy enemy = new Pterosaur(x, y);
			gameStateRef.addEnemy(enemy);
		} else if (waveNumber == 2) {
			Enemy enemy = new GreenPlane(x, y);
			gameStateRef.addEnemy(enemy);
		} else if (waveNumber == 3) {
			Enemy enemy = new RedHelicopter(x, y);
			gameStateRef.addEnemy(enemy);
		} else if (waveNumber == 4) {
			Enemy enemy = new Drifblim(x, y);
			gameStateRef.addEnemy(enemy);
		}
	}
	
	public static void stage2Wave(GameState gameStateRef, int waveNumber) {
		Rectangle start = gameStateRef.getStart().getRectangle();
		int x = (int)start.getX();
		int y = (int)start.getY();
		
		if (waveNumber == 0) {
			Enemy enemy = new Pterosaur(x, y);
		    Enemy enemy2 = new GreenPlane(x, y);
		    Enemy enemy3 = new RedHelicopter(x, y);
		    Enemy enemy4 = new Balloon(x, y);
		    Enemy enemy5 = new HotAirBalloon(x, y);
		    Enemy enemy6 = new Drifblim(x, y);
			gameStateRef.addEnemy(enemy);
			gameStateRef.addEnemy(enemy2);
			gameStateRef.addEnemy(enemy3);
			gameStateRef.addEnemy(enemy4);
			gameStateRef.addEnemy(enemy5);
			gameStateRef.addEnemy(enemy6);
			
			Enemy enemy7 = new Drifblim(x, y);
			Enemy enemy8 = new Drifblim(x, y);
			Enemy enemy9 = new Drifblim(x, y);
			Enemy enemy10 = new Drifblim(x, y);
			gameStateRef.addEnemy(enemy7);
			gameStateRef.addEnemy(enemy8);
			gameStateRef.addEnemy(enemy9);
			gameStateRef.addEnemy(enemy10);
		} else if (waveNumber == 1) {
			Enemy enemy = new Pterosaur(x, y);
			gameStateRef.addEnemy(enemy);
		} else if (waveNumber == 2) {
			Enemy enemy = new GreenPlane(x, y);
			gameStateRef.addEnemy(enemy);
		} else if (waveNumber == 3) {
			Enemy enemy = new RedHelicopter(x, y);
			gameStateRef.addEnemy(enemy);
		} else if (waveNumber == 4) {
			Enemy enemy = new Pterosaur(x, y);
			Enemy enemy2 = new Drifblim(x, y);
			Enemy enemy3 = new RedHelicopter(x, y);
			gameStateRef.addEnemy(enemy);
			gameStateRef.addEnemy(enemy2);
			gameStateRef.addEnemy(enemy3);
		}
	}
	
	public static void stage3Wave(GameState gameStateRef, int waveNumber) {
		Rectangle start = gameStateRef.getStart().getRectangle();
		int x = (int)start.getX();
		int y = (int)start.getY();
		
		if (waveNumber == 0) {
			Enemy enemy = new Pterosaur(x, y);
		    Enemy enemy2 = new GreenPlane(x, y);
		    Enemy enemy3 = new RedHelicopter(x, y);
		    Enemy enemy4 = new Balloon(x, y);
		    Enemy enemy5 = new HotAirBalloon(x, y);
		    Enemy enemy6 = new Drifblim(x, y);
			gameStateRef.addEnemy(enemy);
			gameStateRef.addEnemy(enemy2);
			gameStateRef.addEnemy(enemy3);
			gameStateRef.addEnemy(enemy4);
			gameStateRef.addEnemy(enemy5);
			gameStateRef.addEnemy(enemy6);
			
			Enemy enemy7 = new Drifblim(x, y);
			Enemy enemy8 = new Drifblim(x, y);
			Enemy enemy9 = new Drifblim(x, y);
			Enemy enemy10 = new Drifblim(x, y);
			gameStateRef.addEnemy(enemy7);
			gameStateRef.addEnemy(enemy8);
			gameStateRef.addEnemy(enemy9);
			gameStateRef.addEnemy(enemy10);
		} else if (waveNumber == 1) {
			Enemy enemy = new Pterosaur(x, y);
			gameStateRef.addEnemy(enemy);
		} else if (waveNumber == 2) {
			Enemy enemy = new GreenPlane(x, y);
			gameStateRef.addEnemy(enemy);
		} else if (waveNumber == 3) {
			Enemy enemy = new RedHelicopter(x, y);
			gameStateRef.addEnemy(enemy);
		} else if (waveNumber == 4) {
			Enemy enemy = new Pterosaur(x, y);
			Enemy enemy2 = new Drifblim(x, y);
			Enemy enemy3 = new GreenPlane(x, y);
			Enemy enemy4 = new Balloon(x, y);
			gameStateRef.addEnemy(enemy);
			gameStateRef.addEnemy(enemy2);
			gameStateRef.addEnemy(enemy3);
			gameStateRef.addEnemy(enemy4);
		}
	}

}
