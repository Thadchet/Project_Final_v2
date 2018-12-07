package sharedObject;

import java.util.ArrayList;
import java.util.List;

import Drawing.GameScreen;
import Drawing.GameWindow;
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
	public static AudioClip open;
	public static AudioClip menu;
	public static AudioClip wordDead;
	public static AudioClip gameover;
	public static AudioClip soundgame;
	public static AudioClip winner;
	public static AudioClip heal;
	public static AudioClip skill1;
	public static AudioClip skill2;
	public static AudioClip wrong;
	public static AudioClip typing ;
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

		open = new AudioClip(sound_path + "open.mp3");
		menu = new AudioClip(sound_path + "switch.mp3");
		fall = new AudioClip(sound_path + "fall.mp3");
		wordDead = new AudioClip(sound_path + "wordDead.mp3");
		gameover = new AudioClip(sound_path + "gameover.mp3");
		soundgame = new AudioClip(sound_path + "gameplay.mp3");
		heal = new AudioClip(sound_path + "heal.mp3");
		skill1 = new AudioClip(sound_path + "skill1.m4a");
		skill2 = new AudioClip(sound_path + "skill2.mp3");
		spell = new Image(image_path + "spell.gif");
		winner = new AudioClip(sound_path + "winner.mp3");
		wrong = new AudioClip(sound_path + "wrong.mp3");
		typing = new AudioClip(sound_path+"typing.mp3");
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
		for (IRenderable g : entities) {
			if (g instanceof GameScreen) {
				for (IRenderable i : entities) {
					if (i instanceof Word && !(i.getClass().getSimpleName().equals("WordHeal"))) {
						if (((Word) i).getWordstring().equalsIgnoreCase(temp) && ((Word) i).getY() > 0) {
							((GameScreen) g).addScore();
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
							((GameScreen) g).increaseLife();
							((GameScreen) g).addScore();
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
				wrong.play();
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
		for (IRenderable g : entities) {
			if (g instanceof GameScreen)
				for (IRenderable i : entities) {
					if (i instanceof Wizard) {
						((Wizard) i).updatePosren();
						for (IRenderable f : entities) {
							if (f instanceof FireBall) {
								((FireBall) f).updatePoswiz(((Wizard) i).getPosx(), ((Wizard) i).getPosy());
							}
						}
					}
					if (i instanceof Word) {
						((Word) i).updatePos(((Word) i).getSpeed());
						if (((Word) i).getY() > 650) {
							fall.play();
							((GameScreen) g).decreaseLife();
							((Word) i).setIsvisible(false);
							((Word) i).setIsdestory(true);
						}
					}
				}
		}
	}

	public void reduceSpeed() {
		for (IRenderable i : entities) {
			if (i instanceof Word) {
				double lastspeed = ((Word) i).getSpeed();
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
		for (IRenderable g : entities) {
			if (g instanceof GameScreen) {
				for (IRenderable i : entities) {
					if (i instanceof Word && !(i.getClass().getSimpleName().equals("WordHeal"))) {
						if (((Word) i).getY() > 0) {
							((GameScreen) g).addScore();
							((Word) i).setIsvisible(false);
							((Word) i).setIsdestory(true);
							skill2.play();
						}
					}
					if(i instanceof WordHeal) {
						if (((Word) i).getY() > 0) {
							((GameScreen) g).addScore();
							((GameScreen) g).increaseLife();
							((WordHeal) i).setIsvisible(false);
							((WordHeal) i).setIsdestory(true);
						}
					}
				}
			}
		}
	}


	public boolean isGamefinish() {
		for (IRenderable g : entities) {
			if (g instanceof GameScreen) {
				if (((GameScreen) g).getLife()==0) {
					System.out.println("gameover");
					return true;
				}
				if (isWinner()) {
					System.out.println("winner");
					return true;
				}
			}
		}
		return false;
	}

	public boolean isWinner() {
		for (IRenderable w : entities) {
			if (w instanceof Word) {
				return false;
			}
		}
		return true;
	}

	public void clear() {
		entities.clear();
	}

}
