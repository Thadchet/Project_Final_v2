package Drawing;

import java.util.ArrayList;
import Logic.FireBall;
import Logic.FireSkill2;
import Logic.SnowSkill1;
import Logic.Wizard;
import Logic.Word;
import Logic.WordHeal;
import exception.UsedskillException;
import exception.WrongInputException;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
			"linux", "window", "static", "numpy", "class", "iot", "tree", "digit", "json", "simple", "print", "swap" };
	private String[] special = { "lol", "noob", "bnk", "cherprang", "prayut" };
	private ArrayList<KeyCode> spell = new ArrayList<>();
	private ArrayList<Word> wordInScreen = new ArrayList<>();
	public static String temp = "";
	private GameScreen gamescreen;
	private Word word;
	private WordHeal wordheal;
	private Wizard wizard;
	private FireBall fireball;
	private FireSkill2 fireSkill2;
	private SnowSkill1 snowskill1;
	private GraphicsContext gc;
	private Stage stage;
	private Scene scene;
	public String image_path = ClassLoader.getSystemResource("ImWord/").toString();
	public String image_path_special = ClassLoader.getSystemResource("ImWordspecial/").toString();
	private boolean isGameover;
	public static boolean isCanEnter = true;
	public static int high_score = 0;
	private boolean repeatedlyenter = false;

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
						try {
							if (!gamescreen.isSkillused1()) {
								RenderableHolder.getInstance().reduceSpeed();
								for (int i = 0; i != 70; i++) {
									snowskill1 = new SnowSkill1();
									RenderableHolder.getInstance().add(snowskill1);
								}
								Thread thread = new Thread(new Runnable() {
									public void run() {
										try {
											Thread.sleep(5000);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										RenderableHolder.getInstance().deleteSnow();
									}
								});
								thread.start();
								gamescreen.setisSkillused1(true);
							}
						} catch (UsedskillException e) {
							System.out.println(e.getErrorMessage());
						}
					}
					if (KeyEvent.getCode().equals(KeyCode.F2)) {
						try {
							if (!gamescreen.isSkillused2()) {
								RenderableHolder.getInstance().destroyAllscreen(wordInScreen);
								for (int i = 0; i < wordInScreen.size(); i++) {
									fireSkill2 = new FireSkill2(wordInScreen.get(i).getX(), wordInScreen.get(i).getY(),
											wizard.getX(), wizard.getY());
									RenderableHolder.getInstance().add(fireSkill2);
									System.out.println("add FireSkill2");
								}
								gamescreen.setisSkillused2(true);
							}
						} catch (UsedskillException e) {
							System.out.println(e.getErrorMessage());
						}
					}

				} else {
					if (!KeyEvent.getCode().equals(KeyCode.ENTER)) {
						if (KeyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
							if (!temp.equals("")) {
								temp = temp.substring(0, temp.length() - 1);
							}
						} else {
							try {
								if (checkInput(KeyEvent)) {
									temp += KeyEvent.getCode().toString();
								}
							} catch (WrongInputException e) {
								System.out.println(e.getErrorMessage());
							}
							repeatedlyenter = false;
						}
					} else {
						if (!repeatedlyenter) {
							word = RenderableHolder.getInstance().check(temp);
							if (word != null) {
								fireball = new FireBall(word.getX(), word.getY(), wizard.getX(), wizard.getY());
								RenderableHolder.getInstance().add(fireball);
							}
							temp = "";
							repeatedlyenter = true;
						}
					}
				}
			} else {
				if (KeyEvent.getCode().equals(KeyCode.ENTER)) {
					if (isCanEnter) {
						StartGame startwindow = new StartGame(stage);
						startwindow.startAnimation();
						isGameover = false;
						RenderableHolder.backmenu.play();
					}
				}
			}
		});

	}

	public void addGamescreen() {
		gamescreen = new GameScreen();
		gamescreen.setStatus(10, 0); // set life and score
		RenderableHolder.getInstance().add(gamescreen);
	}

	public void addWizard() {
		wizard = new Wizard();
		RenderableHolder.getInstance().add(wizard);
	}

	public void addWord() {

		for (int i = 0; i < data.length; i++) {
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
			if (i == data.length - 1) {
				word.setSpeed(-2);
				py -= 14000;
			}

			word.setPosition(px, py);
			wordList.add(word);
			RenderableHolder.getInstance().add(word);
		}
		for (int i = 0; i < special.length; i++) {
			wordheal = new WordHeal(special[i], image_path_special + special[i] + ".png", 1);
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
			if (!RenderableHolder.getInstance().isWinner()) {
				RenderableHolder.gameover.play();
				isGameover = true;
				GameFinish.startgamefinish(gc, isGameover, gamescreen);
				setHighscore();
			} else {
				RenderableHolder.winner.play();
				isGameover = false;
				GameFinish.startgamefinish(gc, isGameover, gamescreen);
				setHighscore();
				GameFinish.startgamefinish(gc, isGameover, gamescreen);
			}

			setHighscore();
			temp = "";
			RenderableHolder.getInstance().clear();
		}
	}

	public void setHighscore() {
		if (gamescreen.getScore() > high_score) {
			high_score = gamescreen.getScore();
		}
	}

	public void setSpell() {
		spell.add(KeyCode.F1);
		spell.add(KeyCode.F2);
	}

	public boolean checkInput(KeyEvent k) throws WrongInputException {
		if (k.getCode().equals(KeyCode.A) || k.getCode().equals(KeyCode.B) || k.getCode().equals(KeyCode.C)
				|| k.getCode().equals(KeyCode.D) || k.getCode().equals(KeyCode.E) || k.getCode().equals(KeyCode.F)
				|| k.getCode().equals(KeyCode.G) || k.getCode().equals(KeyCode.H) || k.getCode().equals(KeyCode.I)
				|| k.getCode().equals(KeyCode.J) || k.getCode().equals(KeyCode.K) || k.getCode().equals(KeyCode.L)
				|| k.getCode().equals(KeyCode.M) || k.getCode().equals(KeyCode.N) || k.getCode().equals(KeyCode.O)
				|| k.getCode().equals(KeyCode.P) || k.getCode().equals(KeyCode.Q) || k.getCode().equals(KeyCode.R)
				|| k.getCode().equals(KeyCode.S) || k.getCode().equals(KeyCode.T) || k.getCode().equals(KeyCode.U)
				|| k.getCode().equals(KeyCode.V) || k.getCode().equals(KeyCode.W) || k.getCode().equals(KeyCode.X)
				|| k.getCode().equals(KeyCode.Y) || k.getCode().equals(KeyCode.Z)) {
			return true;
		} else
			throw new WrongInputException();
	}
}