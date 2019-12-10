package model;

import java.util.ArrayList;
import java.util.List;

import model.enemies.Balloon;
import model.enemies.Drifblim;
import model.enemies.Enemy;
import model.enemies.GreenPlane;
import model.enemies.HotAirBalloon;
import model.enemies.Pterosaur;
import model.enemies.RedHelicopter;

public class Waves {
	public static final int MAX_WAVES = 10;
	
	public static List<Enemy> getWave(int waveNum, int x, int y) {
		List<Enemy> wave = new ArrayList<>();
		
		if (waveNum == 0) {
			wave.add(new Balloon(x, y));
			wave.add(new HotAirBalloon(x, y));
			wave.add(new RedHelicopter(x, y));
		} else if (waveNum == 1) {
			wave.add(new Balloon(x, y));
			wave.add(new HotAirBalloon(x, y));
			wave.add(new RedHelicopter(x, y));
			wave.add(new Balloon(x, y));
			wave.add(new HotAirBalloon(x, y));
			wave.add(new RedHelicopter(x, y));
			wave.add(new Balloon(x, y));
			wave.add(new HotAirBalloon(x, y));
			wave.add(new RedHelicopter(x, y));
		} else if (waveNum == 2) {
			wave.add(new GreenPlane(x, y));
			wave.add(new Pterosaur(x, y));
		} else if (waveNum == 3) {
			wave.add(new RedHelicopter(x, y));
			wave.add(new Drifblim(x, y));
			wave.add(new GreenPlane(x, y));
		} else if (waveNum == 4) {
			wave.add(new Pterosaur(x, y));
			wave.add(new GreenPlane(x, y));
			wave.add(new RedHelicopter(x, y));
			wave.add(new Balloon(x, y));
			wave.add(new HotAirBalloon(x, y));
			wave.add(new Drifblim(x, y));
		} else if (waveNum == 5) {
			for (int i = 0; i < 5; i++) {
				wave.add(new Pterosaur(x, y));
				wave.add(new GreenPlane(x, y));
				wave.add(new RedHelicopter(x, y));
				wave.add(new Balloon(x, y));
				wave.add(new HotAirBalloon(x, y));
				wave.add(new Drifblim(x, y));
			}
		} else if (waveNum == 6) {
			for (int i = 0; i < 5; i++) {
				wave.add(new Balloon(x, y));
				wave.add(new HotAirBalloon(x, y));
				wave.add(new RedHelicopter(x, y));
				wave.add(new Balloon(x, y));
				wave.add(new HotAirBalloon(x, y));
				wave.add(new RedHelicopter(x, y));
				wave.add(new Balloon(x, y));
				wave.add(new HotAirBalloon(x, y));
				wave.add(new RedHelicopter(x, y));
			}
		} else if (waveNum == 7) {
			for (int i = 0; i < 10; i++) {
				wave.add(new Balloon(x, y));
				wave.add(new HotAirBalloon(x, y));
			}
		} else if (waveNum == 8) {
			for (int i = 0; i < 15; i++) {
				wave.add(new RedHelicopter(x, y));
				wave.add(new Balloon(x, y));
				wave.add(new HotAirBalloon(x, y));
				wave.add(new RedHelicopter(x, y));
			}
		} else if (waveNum == 9) {
			for (int i = 0; i < 15; i++) {
				wave.add(new Balloon(x, y));
				wave.add(new HotAirBalloon(x, y));
				wave.add(new RedHelicopter(x, y));
				wave.add(new Balloon(x, y));
				wave.add(new HotAirBalloon(x, y));
				wave.add(new RedHelicopter(x, y));
				wave.add(new Balloon(x, y));
				wave.add(new HotAirBalloon(x, y));
				wave.add(new RedHelicopter(x, y));
			}
		}
		
		return wave;
	}
}
