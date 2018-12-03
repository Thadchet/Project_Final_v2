package sharedObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.media.AudioClip;


public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();
	private List<IRenderable> entities ;
	private Comparator<IRenderable> comparator ;
	public static AudioClip switchmenu ;
	public static AudioClip soundbg ;
	static {
		loadResource();
	}
	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1 , IRenderable o2) -> {
			if(o1.getZ() > o2.getZ()) 
				return 1 ;
			return -1 ;
		};
		
	}
	public static void loadResource() {

		switchmenu = new AudioClip(ClassLoader.getSystemResource("res/sound/switch.mp3").toString());
//		soundbg = new AudioClip(ClassLoader.getSystemResource("sound/intro.mp3").toString());
		
		
	}
	public void add(IRenderable entity) {
		System.out.println("add");
		entities.add(entity);
		Collections.sort(entities,comparator);
	}
	public void update() {
		for(int i = entities.size() -1 ; i >= 0 ; i--) {
			if(entities.get(i).isDestroyed()) {
				entities.remove(i);
			}
		}
	}
	public static RenderableHolder getInstance() {
		return instance ;
	}
	
}
