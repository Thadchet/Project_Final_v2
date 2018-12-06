package sharedObject;

import java.util.ArrayList;
import java.util.List;

import Logic.FireBall;
import Logic.Wizard;
import Logic.Word;
import Logic.WordHeal;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();
	private List<IRenderable> entities;
	public static AudioClip fall;
	public static AudioClip menu;
	public static AudioClip wordDead;
	public static AudioClip gameover;
	public static AudioClip soundgame;
	public static AudioClip winner ;
	public static AudioClip heal;
	public static AudioClip skill1;
	public static AudioClip skill2;
	public static Image explosion;
	public static Image spell;
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
		wordDead = new AudioClip(sound_path + "wordDead.mp3");
		gameover = new AudioClip(sound_path + "gameover.mp3");
		soundgame = new AudioClip(sound_path + "game2.m4a");
		heal = new AudioClip(sound_path + "heal.mp3");
		skill1 = new AudioClip(sound_path + "skill1.m4a");
		skill2 = new AudioClip(sound_path + "skill2.mp3");
		spell = new Image(image_path + "spell.gif");
		winner = new AudioClip(sound_path+"winner.mp3");
	}

	public void add(IRenderable entity) {
		// System.out.println("add");
		entities.add(entity);
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed()) {
				entities.remove(i);
			}
		}
	}

	public Word check(String temp) {
		for (IRenderable w : entities) {
			if (w instanceof Wizard) {
				for (IRenderable i : entities) {
					if (i instanceof Word && !(i.getClass().getSimpleName().equals("WordHeal"))) {
						if (((Word) i).getWordstring().equalsIgnoreCase(temp) && ((Word) i).getY()>0) {
							((Wizard) w).addScore();
							((Word) i).setIsvisible(false);
							Thread t = new Thread(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									try {
										Thread.sleep(250);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									((Word) i).setIsdestory(true);
								}
							});
							t.start();
							wordDead.play();
							return (Word) i;
						}
					}
					if (i instanceof WordHeal) {
						if (((WordHeal) i).getWordstring().equalsIgnoreCase(temp)) {
							((Wizard) w).addScore();
							((Word) i).setIsvisible(false);
							Thread t = new Thread(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									try {
										Thread.sleep(250);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									((Word) i).setIsdestory(true);
									heal.play();
								}
							});
							t.start();
							return (Word) i;
						}
					}
				}
			}
		}
		return null;

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
		for (IRenderable w : entities) {
			if (w instanceof Wizard) {
				((Wizard) w).updatePosren();
				for(IRenderable f : entities) {
					if (f instanceof FireBall) {
						((FireBall) f).updatePoswiz(((Wizard) w).getPosx(), ((Wizard) w).getPosy());;
					}
				}
				for (IRenderable i : entities) {
					if (i instanceof Word) {
						((Word) i).updatePos(((Word) i).getSpeed());
						if (((Word) i).getY() > 650) {
							fall.play();
							((Wizard) w).decreaseLife();
							((Word) i).setIsvisible(false);
							((Word) i).setIsdestory(true);
						}
					}
				}
			}
		}
	}

	public void reduceSpeed() {
		for (IRenderable i : entities) {
			if (i instanceof Word ) {
				double lastspeed = ((Word)i).getSpeed();
				((Word) i).setSpeed(-0.2);
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						((Word) i).setSpeed(lastspeed);
					}
				});
				t.start();
				skill1.play();
				
			}
		}
	}

	public void destroyAllscreen() {
		for (IRenderable w : entities) {
			if (w instanceof Wizard) {
				for (IRenderable i : entities) {
					if (i instanceof Word || i instanceof WordHeal) {
						if (((Word) i).getY() > 0) {
							((Word) i).setIsvisible(false);
							((Word) i).setIsdestory(true);
							((Wizard) w).addScore();
							skill2.play();
						}
					}
				}
			}
		}
	}

	public void updateWordHeal() {
		for (IRenderable w : entities) {
			if (w instanceof Wizard) {
				for (IRenderable h : entities) {
					if (h instanceof WordHeal) {
						if (h.isDestroyed()) {
							((Wizard) w).increaseLife();
						}
					}
				}
			}
		}
	}

	public boolean isGamefinish() {
		for (IRenderable w : entities) {
			if (w instanceof Wizard) {
				if (((Wizard) w).life == 0) {
					System.out.println("gameover");
					return true;
				}
				if(((Wizard) w).score == 75) {
					System.out.println("winner");
					return true ;
				}
			}
		}
		return false;
	}
	public boolean isWinner() {
		for(IRenderable w : entities) {
			if( w instanceof Wizard) {
				if(((Wizard) w).score == 75) {
					return true ;
				}
			}
		}
		return false ;
	}

	public void clear() {
		entities.clear();
	}
	public void test() {
		for(IRenderable i : entities) {
			if(i instanceof Word)
				System.out.println(((Word) i).getWordstring() + " " +((Word) i).getX());
		}
	}
}
