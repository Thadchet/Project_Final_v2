package Drawing;

import java.util.ArrayList;
import java.util.Random;

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
	private Random randomGenerator;
	private ArrayList<Word> wordList = new ArrayList<>();
	private String[] data = { "cat", "dog", "win", "create", "java", "progmeth", "chromatic", "integral", "unique",
			"vertex", "ceiling", "adjacency", "bipartitle", "degree", "edges", "euler", "hamilton", "proof", "iterator",
			"recurrence", "machine", "priority", "discrete", "algorithms", "list", "set", "tuple", "git", "int",
			"float", "double", "and", "or", "nfa", "dfa", "binary", "stack", "vector", "data", "insert", "erase",
			"return", "method", "hash", "python", "sort", "array", "index", "address", "node", "null", "parent", "root",
			"linux", "window", "static", "numpy", "class", "iot", "tree", "digit", "json", "simple", "print" };
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
	public static boolean skillused1 = false;
	public static boolean skillused2 = false;
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
						if (!skillused1) {
							RenderableHolder.getInstance().reduceSpeed();
							skillused1 = true;
						}
					}
					if (KeyEvent.getCode().equals(KeyCode.F2)) {
						if (!skillused2) {
							RenderableHolder.getInstance().destroyAllscreen();
							skillused2 = true;
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
									|| KeyEvent.getCode().equals(KeyCode.TAB))) {
								temp += KeyEvent.getCode().toString();
							}
						}
					} else {
						// System.out.println(temp);
						word = RenderableHolder.getInstance().check(temp);
						// System.out.println(String.valueOf(word.getX())+" "
						// +String.valueOf(word.getY()));
						if (word != null) {
							fireball = new FireBall(word.getX(), word.getY(), wizard.getX(), wizard.getY());
							RenderableHolder.getInstance().add(fireball);
						}

						temp = "";
					}
				}
			} else {
				if (KeyEvent.getCode().equals(KeyCode.ENTER)) {
					StartWindow startwindow = new StartWindow(stage);
					startwindow.startAnimation();
					isGameover = false;
					wizard.life = 5;
					wizard.score = 0;
				}
			}
		});

	}

	public void addGamescreen() {
		gamescreen = new GameScreen();
		RenderableHolder.getInstance().add(gamescreen);
	}

	public void addWizard() {
		wizard = new Wizard();
		RenderableHolder.getInstance().add(wizard);
	}

	public void addWord() {
		System.out.println(data.length);
		for (int i = 0; i < data.length; i++) {
			System.out.println(image_path + data[i] + ".png");
			word = new Word(data[i], image_path + data[i] + ".png", 1);
			double px = Math.random() * 450;
			double py = (Math.random() * -2000);

			if (i > 56) {
				word.setSpeed(word.getSpeed() - 2.5);
				py -= 15000;
			} else if (i > 42) {
				word.setSpeed(word.getSpeed() - 1.5);
				py -= 9500;
			} else if (i > 28) {
				word.setSpeed(word.getSpeed() - 1);
				py -= 6500;
			} else if (i > 14) {
				word.setSpeed(word.getSpeed() - 0.5);
				py -= 3500;
			}

			word.setPosition(px, py);
			wordList.add(word);
			RenderableHolder.getInstance().add(word);
		}
		for (int i = 0; i < special.length; i++) {
			wordheal = new WordHeal(special[i], image_path_special + special[i] + ".png", 0.5);
			System.out.println(image_path_special + special[i] + ".png");
			double px = Math.random() * 450;
			double py = (Math.random() * -2000);

			if (i > 4) {
				word.setSpeed(word.getSpeed() - 2.5);
				py -= 15000;
			} else if (i > 3) {
				word.setSpeed(word.getSpeed() - 1.5);
				py -= 9500;
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
		RenderableHolder.getInstance().updateWordHeal();
	}

	public void updateDetail() {
		RenderableHolder.getInstance().draw(gc);
		RenderableHolder.getInstance().update();
	}

	public void isGamefinish() {
		if (RenderableHolder.getInstance().isGamefinish()) {
			gamewindowanimation.stop();
			RenderableHolder.soundgame.stop();
			System.out.println("is Here");
			if (!RenderableHolder.getInstance().isWinner()) {
				RenderableHolder.gameover.play();
				isGameover = true;
				GameFinish.startgamefinish(gc,isGameover);
				setHighscore();
			}
			else {
				RenderableHolder.winner.play();
				isGameover = false ;
				GameFinish.startgamefinish(gc,isGameover);
				setHighscore();
			}
			RenderableHolder.getInstance().clear();
		}
	}

	public void setHighscore() {
		if (high_score < wizard.score) {
			high_score = wizard.score;
		}
	}


	public void setSpell() {
		spell.add(KeyCode.F1);
		spell.add(KeyCode.F2);
	}
}