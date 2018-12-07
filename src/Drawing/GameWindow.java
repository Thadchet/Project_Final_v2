package Drawing;

import java.util.ArrayList;
import Logic.FireBall;
import Logic.Wizard;
import Logic.Word;
import Logic.WordHeal;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sharedObject.RenderableHolder;

public class GameWindow extends Canvas {
	private static AnimationTimer gamewindowanimation;
	private ArrayList<Word> wordList = new ArrayList<>();
	private String[] data = { "cat", "dog", "win", "create", "java", "progmeth", "chromatic", "integral", "unique",
			"vertex", "ceiling", "adjacency", "bipartite", "degree", "edges", "euler", "hamilton", "proof", "iterator",
			"recurrence", "machine", "priority", "discrete", "algorithms", "list", "set", "tuple", "git", "int",
			"float", "double", "and", "or", "nfa", "dfa", "binary", "stack", "vector", "data", "insert", "erase",
			"return", "method", "hash", "python", "sort", "array", "index", "address", "node", "null", "parent", "root",
			"linux", "window", "static", "numpy", "class", "iot", "tree", "digit", "json", "simple", "print","swap" };
	private String[] special = { "lol", "noob", "bnk", "cherprang", "prayut" };
	private ArrayList<KeyCode> spell = new ArrayList<>();
	public static String temp = "";
	private GameScreen gamescreen;
	private Word word;
	private WordHeal wordheal;
	private Wizard wizard;
	private FireBall fireball;
	private GraphicsContext gc;
	private Stage stage;
	private Scene scene;
	public String image_path = ClassLoader.getSystemResource("ImWord/").toString();
	public String image_path_special = ClassLoader.getSystemResource("ImWordspecial/").toString();
	public static int score;
	private boolean isGameover ;
	public static int high_score = 0;

	public GameWindow(Stage stage) {
		this.stage = stage;
		setWidth(550);
		setHeight(750);
		gc = getGraphicsContext2D();
		StackPane pane = new StackPane();
		pane.getChildren().add(gc.getCanvas());
		scene = new Scene(pane);
		this.stage.setScene(scene);
		addAll();
		setSpell();
		requestFocus();

	}

	public void drawGameWindow() {
		addEvent(gc);
		RenderableHolder.soundgame.play();
		gamewindowanimation = new AnimationTimer() {

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				updateWord();
				updateDetail();
				isGamefinish();
				playmusic();
			}
		};
		gamewindowanimation.start();
	}

	public void playmusic() {
		if (!RenderableHolder.soundgame.isPlaying() && RenderableHolder.getInstance().isGamefinish()) {
			RenderableHolder.soundgame.play();
		}
	}

	public void addEvent(GraphicsContext gc) {
		this.setOnKeyPressed((KeyEvent) -> {
			if (!isGameover) {
				if (spell.contains(KeyEvent.getCode())) {
					if (KeyEvent.getCode().equals(KeyCode.F1)) {
						if (!gamescreen.isSkillused1()) {
							RenderableHolder.getInstance().reduceSpeed();
							gamescreen.setisSkillused1(true); 
						}
					}
					if (KeyEvent.getCode().equals(KeyCode.F2)) {
						if (!gamescreen.isSkillused2()) {
							RenderableHolder.getInstance().destroyAllscreen();
							gamescreen.setisSkillused2(true);
						}
					}

				} else {
					if (!KeyEvent.getCode().equals(KeyCode.ENTER)) {
						if (KeyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
							if (!temp.equals("")) {
								temp = temp.substring(0, temp.length() - 1);
							}
						} else {
							if (!(KeyEvent.getCode().equals(KeyCode.UP) || KeyEvent.getCode().equals(KeyCode.DOWN)
									|| KeyEvent.getCode().equals(KeyCode.SHIFT)
									|| KeyEvent.getCode().equals(KeyCode.LEFT)
									|| KeyEvent.getCode().equals(KeyCode.RIGHT)
									|| KeyEvent.getCode().equals(KeyCode.CONTROL)
									|| KeyEvent.getCode().equals(KeyCode.BACK_SLASH)
									|| KeyEvent.getCode().equals(KeyCode.ALT)
									|| KeyEvent.getCode().equals(KeyCode.PAGE_DOWN)
									|| KeyEvent.getCode().equals(KeyCode.PAGE_UP)
									|| KeyEvent.getCode().equals(KeyCode.SPACE)
									|| KeyEvent.getCode().equals(KeyCode.TAB)
									|| KeyEvent.getCode().equals(KeyCode.ALT_GRAPH)
									|| KeyEvent.getCode().equals(KeyCode.CAPS)
									|| KeyEvent.getCode().equals(KeyCode.F1)
									|| KeyEvent.getCode().equals(KeyCode.F2)
									|| KeyEvent.getCode().equals(KeyCode.F3)
									|| KeyEvent.getCode().equals(KeyCode.F4)
									|| KeyEvent.getCode().equals(KeyCode.F5)
									|| KeyEvent.getCode().equals(KeyCode.F6)
									|| KeyEvent.getCode().equals(KeyCode.F7)
									|| KeyEvent.getCode().equals(KeyCode.F8)
									|| KeyEvent.getCode().equals(KeyCode.F9)
									|| KeyEvent.getCode().equals(KeyCode.F10)
									|| KeyEvent.getCode().equals(KeyCode.F11)
									|| KeyEvent.getCode().equals(KeyCode.F12)
									|| KeyEvent.getCode().equals(KeyCode.PAUSE)
									|| KeyEvent.getCode().equals(KeyCode.INSERT)
									|| KeyEvent.getCode().equals(KeyCode.HOME)
									|| KeyEvent.getCode().equals(KeyCode.END)
									|| KeyEvent.getCode().equals(KeyCode.UNDEFINED))) {
								temp += KeyEvent.getCode().toString();
							}
						}
					} else {
						word = RenderableHolder.getInstance().check(temp);
						if (word != null) {
							fireball = new FireBall(word.getX(), word.getY(), wizard.getX(), wizard.getY());
							RenderableHolder.getInstance().add(fireball);
						}
						temp = "";
					}
				}
			} else {
				if (KeyEvent.getCode().equals(KeyCode.ENTER)) {
					StartGame startwindow = new StartGame(stage);
					startwindow.startAnimation();
					isGameover = false;
				}
			}
		});

	}

	public void addGamescreen() {
		gamescreen = new GameScreen();
		gamescreen.setStatus(10, 0);  ////// set life and score 
		RenderableHolder.getInstance().add(gamescreen);
	}

	public void addWizard() {
		wizard = new Wizard();
		RenderableHolder.getInstance().add(wizard);
	}

	public void addWord() {
		for (int i = 0; i < data.length; i++) {
			//System.out.println(image_path + data[i] + ".png");
			word = new Word(data[i], image_path + data[i] + ".png", 1);
			double px = Math.random() * 450;
			double py = (Math.random() * -2000);

			if (i > 56) {
				word.setSpeed(word.getSpeed() - 2);
				py -= 15000;
			} else if (i > 42) {
				word.setSpeed(word.getSpeed() - 1.5);
				py -= 11000;
			} else if (i > 28) {
				word.setSpeed(word.getSpeed() - 1);
				py -= 6500;
			} else if (i > 14) {
				word.setSpeed(word.getSpeed() - 0.5);
				py -= 3500;
			}
			if(i==data.length-1) {
				word.setSpeed(-3);
				py -= 14000;
			}

			word.setPosition(px, py);
			wordList.add(word);
			RenderableHolder.getInstance().add(word);
		}
		for (int i = 0; i < special.length; i++) {
			wordheal = new WordHeal(special[i], image_path_special + special[i] + ".png", 1);
			//System.out.println(image_path_special + special[i] + ".png");
			double px = Math.random() * 450;
			double py = (Math.random() * -2000);

			if (i > 4) {
				word.setSpeed(word.getSpeed() - 2);
				py -= 15000;
			} else if (i > 3) {
				word.setSpeed(word.getSpeed() - 1.5);
				py -= 11000;
			} else if (i > 2) {
				word.setSpeed(word.getSpeed() - 1);
				py -= 6500;
			} else if (i > 1) {
				word.setSpeed(word.getSpeed() - 0.5);
				py -= 3500;
			}

			wordheal.setPosition(px, py);
			wordList.add(wordheal);
			RenderableHolder.getInstance().add(wordheal);
		}

	}

	public void addAll() {
		addGamescreen();
		addWord();
		addWizard();
	}

	public void updateWord() {
		RenderableHolder.getInstance().updatePos();
	}

	public void updateDetail() {
		RenderableHolder.getInstance().draw(gc);
		RenderableHolder.getInstance().update();
	}

	public void isGamefinish() {
		if (RenderableHolder.getInstance().isGamefinish()) {
			gamewindowanimation.stop();
			RenderableHolder.soundgame.stop();
			//System.out.println("is Here");
			if (!RenderableHolder.getInstance().isWinner()) {
				RenderableHolder.gameover.play();
				isGameover = true;
				GameFinish.startgamefinish(gc,isGameover,gamescreen);
			}
			else {
				RenderableHolder.winner.play();
				isGameover = false ;
				GameFinish.startgamefinish(gc,isGameover,gamescreen);
			}
			setHighscore();
			temp = "" ;
			RenderableHolder.getInstance().clear();
		}
	}

	public void setHighscore() {
		if(gamescreen.getScore() > high_score) {
			high_score = gamescreen.getScore();
		}
	}


	public void setSpell() {
		spell.add(KeyCode.F1);
		spell.add(KeyCode.F2);
	}
}