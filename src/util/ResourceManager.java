package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class ResourceManager {
	private static Map<String, Image> images = new HashMap<>();
	
	public static void loadImages() {
		try {
			images.put("Default", new Image(new FileInputStream("resources/images/towers/default_tower.png")));
			
			// Towers
			images.put("Tower", new Image(new FileInputStream("resources/images/towers/default_tower.png")));
			images.put("AreaTower", new Image(new FileInputStream("resources/images/towers/area_tower.png")));
			images.put("CheapTower", new Image(new FileInputStream("resources/images/towers/cheap_tower.png")));
			images.put("MultiShotTower", new Image(new FileInputStream("resources/images/towers/tall_tower.png")));
			images.put("OneShotTower", new Image(new FileInputStream("resources/images/towers/one_shot_tower.png")));
			images.put("PiercingTower", new Image(new FileInputStream("resources/images/Thicc_Yoshi.gif")));	
			images.put("RapidTower", new Image(new FileInputStream("resources/images/towers/rapid_tower.png")));
			
			// Enemies
			images.put("Balloon", new Image(new FileInputStream("resources/images/enemies/balloon_purple.gif")));
			images.put("Drifblim", new Image(new FileInputStream("resources/images/enemies/Drifblim.gif")));
			images.put("GreenPlane", new Image(new FileInputStream("resources/images/enemies/green_plane.gif")));
			images.put("HotAirBalloon", new Image(new FileInputStream("resources/images/enemies/hot_air_balloon.gif")));
			images.put("Pterosaur", new Image(new FileInputStream("resources/images/enemies/pterosaur_small.gif")));
			images.put("RedHelicopter", new Image(new FileInputStream("resources/images/enemies/red_helicopter.gif")));
			
			// Projectiles
			images.put("AreaBullet", new Image(new FileInputStream("resources/images/projectiles/area_bullet.png")));
			images.put("MultiBullet", new Image(new FileInputStream("resources/images/projectiles/multi_bullet.png")));
			images.put("OneShotBullet", new Image(new FileInputStream("resources/images/projectiles/one_shot_bullet.png")));
			images.put("PiercingBullet", new Image(new FileInputStream("resources/images/projectiles/piercing_bullet.png")));
			images.put("Projectile", new Image(new FileInputStream("resources/images/projectiles/bullet_bill.png")));
			images.put("RapidBullet", new Image(new FileInputStream("resources/images/projectiles/rapid_bullet.png")));
			images.put("StrongBullet", new Image(new FileInputStream("resources/images/projectiles/strong_bullet.png")));
			images.put("WeakBullet", new Image(new FileInputStream("resources/images/projectiles/weak_bullet.png")));
		} catch (FileNotFoundException e) {
			System.err.println("Missing resource");
			e.printStackTrace();
		}
		
	}
	
	public static Image getImage(String name) {
		return images.get(name);
	}
}
