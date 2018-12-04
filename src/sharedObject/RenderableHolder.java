package sharedObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Logic.Word;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();
	private List<IRenderable> entities;
	public static AudioClip fall;
	public static AudioClip menu;
	public static AudioClip wordDead ;
	public static String image_path = ClassLoader.getSystemResource("image/").toString();
	public static String sound_path = ClassLoader.getSystemResource("sound/").toString();
	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();

	}

	public static void loadResource() {

		menu = new AudioClip(sound_path + "switch.mp3");
		fall = new AudioClip(sound_path + "fall.mp3");
		wordDead = new AudioClip(sound_path+"wordDead.mp3");
		// explosionSound = new
		// AudioClip(ClassLoader.getSystemResource("Explosion.wav").toString());

	}

	public void add(IRenderable entity) {
		System.out.println("add");
		entities.add(entity);
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed()) {
				fall.play();
				entities.remove(i);
			}
		}
	}
	public void check(String temp) {
		for (IRenderable i : entities) {
			if (i instanceof Word) {
				if(((Word) i).getWordstring().equalsIgnoreCase(temp)) {
					((Word) i).setIsvisible(false);
					((Word) i).setIsdestory(true);
					wordDead.play();
				}
			}

		}
	}
	

	public void draw(GraphicsContext gc) {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).draw(gc);
		}
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public void updatePos() {
		for (IRenderable i : entities) {
			if (i instanceof Word) {
				((Word) i).updatePos(-0.5);
				if (((Word) i).getY() > 400) {
					((Word) i).setIsvisible(false);
					((Word) i).setIsdestory(true);
				}
			}

		}
	}

}
