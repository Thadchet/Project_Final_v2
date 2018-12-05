package Drawing;

import java.util.ArrayList;

import Logic.Wizard;
import Logic.Word;
import Logic.WordHeal;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class GameWindow extends Canvas {
	private static AnimationTimer gamewindowanimation;
	private ArrayList<Word> wordList = new ArrayList<>();
	private String[] data = { "cat", "dog", "win", "create", "java", "progmeth", "chromatic", "integral", "unique",
			"vertex", "ceiling", "adjacency", "bipartitle", "degree", "edges", "euler", "hamilton", "proof", "iterator",
			"recurrence", "machine", "priority", "discrete", "algorithms", "list", "set", "tuple", "git", "int",
			"float", "double", "and", "or", "nfa", "dfa", "binary", "stack", "vector", "data", "insert", "erase",
			"return", "method", "hash", "python", "sort" };
	private String[] special = { "lol","noop","bnk"};
	private ArrayList<KeyCode> spell = new ArrayList<>();
	public static String temp = "";
	private GameScreen gamescreen;
	private Word word;
	private WordHeal wordheal;
	private Wizard wizard;
	private GraphicsContext gc;
	private Stage stage;
	private Scene scene;
	public String image_path = ClassLoader.getSystemResource("ImWord/").toString();
	public String image_path_special = ClassLoader.getSystemResource("ImWordspecial/").toString();
	public static int score;
	private boolean isGameover = false;
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
		gamewindowanimation = new AnimationTimer() {

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				updateWord();
				updateDetail();
				isGameover();
				isWinner();
			}
		};
		gamewindowanimation.start();
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
					if (KeyEvent.getCode().equals(KeyCode.F3)) {
						////
					}

				} else {
					if (!KeyEvent.getCode().equals(KeyCode.ENTER)) {
						if (KeyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
							temp = temp.substring(0, temp.length() - 1);
						} else {
							if (!(KeyEvent.getCode().equals(KeyCode.UP) || KeyEvent.getCode().equals(KeyCode.DOWN)
									|| KeyEvent.getCode().equals(KeyCode.SHIFT)
									|| KeyEvent.getCode().equals(KeyCode.LEFT)
									|| KeyEvent.getCode().equals(KeyCode.RIGHT)
									|| KeyEvent.getCode().equals(KeyCode.CONTROL))
									|| KeyEvent.getCode().equals(KeyCode.BACK_SLASH)
									|| KeyEvent.getCode().equals(KeyCode.ALT)
									|| KeyEvent.getCode().equals(KeyCode.PAGE_DOWN)
									|| KeyEvent.getCode().equals(KeyCode.PAGE_UP)
									|| KeyEvent.getCode().equals(KeyCode.TAB)) {
								temp += KeyEvent.getCode().toString();
							}
						}
					} else {
						// System.out.println(temp);
						RenderableHolder.getInstance().check(temp);
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

		for (int i = 0; i < data.length; i++) {
			System.out.println(image_path + data[i] + ".png");
			word = new Word(data[i], image_path + data[i] + ".png",2);
			double px = Math.random() * 450;
			double py = Math.random() * -4000;

			word.setPosition(px, py);
			wordList.add(word);
			RenderableHolder.getInstance().add(word);
		}
		for (int i = 0; i < special.length; i++) {
			wordheal = new WordHeal(special[i], image_path_special + special[i] + ".png",1);
			System.out.println(image_path_special + special[i] + ".png");
			double px = Math.random() * 450;
			double py = Math.random() * -4000;
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

	public void isGameover() {
		if (RenderableHolder.getInstance().isGameover()) {
			RenderableHolder.gameover.play();
			gamewindowanimation.stop();
			RenderableHolder.getInstance().clear();
			Gameover.startgameover(gc);
			isGameover = true;
			RenderableHolder.gameplay.stop();
			setHighscore();
		}
	}

	public void setHighscore() {
		if (high_score < wizard.score) {
			high_score = wizard.score;
		}
	}

	public void isWinner() {
		if (score == 45) {
			gamewindowanimation.stop();
			setHighscore();
			RenderableHolder.getInstance().clear();
			RenderableHolder.gameplay.stop();
			///////
		}
	}

	public void setSpell() {
		spell.add(KeyCode.F1);
		spell.add(KeyCode.F2);
		spell.add(KeyCode.F3);
	}
}