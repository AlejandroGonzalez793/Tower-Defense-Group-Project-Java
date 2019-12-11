package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

/**
 * The ResourceManager class makes it so images/audio are all referenced from a Map.
 * This makes it so multiple images are not being created at once which helps with 
 * lag in game. 
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu)
 */
public class ResourceManager {
	private static Map<String, Image> images = new HashMap<>();
	private static Map<String, Media> audio = new HashMap<>();

	/**
	 * Add images (png or gif files) to the images list for the resource manager.
	 * 
	 * @throws FileNotFoundException when an image can not be found.
	 */
	public static void loadImages() {
		try {
			images.put("Default", new Image(new FileInputStream("resources/images/towers/default_tower.png")));

			// Towers
			images.put("Tower", new Image(new FileInputStream("resources/images/towers/default_tower.png")));
			images.put("AreaTower", new Image(new FileInputStream("resources/images/towers/area_tower.png")));
			images.put("CheapTower", new Image(new FileInputStream("resources/images/towers/cheap_tower.png")));
			images.put("MultiShotTower", new Image(new FileInputStream("resources/images/towers/tall_tower.png")));
			images.put("OneShotTower", new Image(new FileInputStream("resources/images/towers/one_shot_tower.png")));
			images.put("PiercingTower", new Image(new FileInputStream("resources/images/towers/Thicc_Yoshi.gif")));
			images.put("RapidTower", new Image(new FileInputStream("resources/images/towers/rapid_tower.png")));
			images.put("ExpensiveTower", new Image(new FileInputStream("resources/images/towers/expensive_tower.png")));

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
			images.put("OneShotBullet",
					new Image(new FileInputStream("resources/images/projectiles/one_shot_bullet.png")));
			images.put("PiercingBullet",
					new Image(new FileInputStream("resources/images/projectiles/piercing_bullet.png")));
			images.put("Projectile", new Image(new FileInputStream("resources/images/projectiles/bullet_bill.png")));
			images.put("RapidBullet", new Image(new FileInputStream("resources/images/projectiles/rapid_bullet.png")));
			images.put("StrongBullet",
					new Image(new FileInputStream("resources/images/projectiles/strong_bullet.png")));
			images.put("WeakBullet", new Image(new FileInputStream("resources/images/projectiles/weak_bullet.png")));
		} catch (FileNotFoundException e) {
			System.err.println("Missing resource");
			e.printStackTrace();
		}

	}

	/**
	 * Add soundtracks (mp3 files) to the audio list for the resource manager.
	 */
	public static void loadAudio() {
		audio.put("map1", new Media(Paths.get("resources/music/Rowdy_Rumble.mp3").toUri().toString()));
		audio.put("map2", new Media(Paths.get("resources/music/Desire_for_All_That_Is_Lost.mp3").toUri().toString()));
		audio.put("map3", new Media(Paths.get("resources/music/Rage_Awakening.mp3").toUri().toString()));
		audio.put("default", new Media(Paths.get("resources/music/Vim_and_Vigor.mp3").toUri().toString()));
	}

	/**
	 * Get a music track to use for a stage in the game.
	 * 
	 * @param name the name of the soundtrack
	 * @return a media object
	 */
	public static Media getAudio(String name) {
		return audio.get(name);
	}

	/**
	 * Get an image to use for a tower, enemy or projectile.
	 * 
	 * @param name the name of the image
	 * @return an image object
	 */
	public static Image getImage(String name) {
		return images.get(name);
	}
}
