package Drawing;

import java.util.ArrayList;

import Logic.Word;
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
	private static AnimationTimer gamewindowanimation ;
	private ArrayList<Word> wordList = new ArrayList<>();
	private String temp = "" ;
	private GameScreen gamescreen ;
	private Word word ;
	private GraphicsContext gc ;
	private Stage stage ;
	private Scene scene ;
	public String image_path = ClassLoader.getSystemResource("image/").toString();
	public static int score;
	
	public GameWindow(Stage stage) {
		this.stage = stage ;
		setWidth(550);
		setHeight(750);
		gc = getGraphicsContext2D();
		StackPane pane = new StackPane();
		pane.getChildren().add(gc.getCanvas());
		scene = new Scene(pane);
		this.stage.setScene(scene);
		addAll();
		requestFocus();
	}
	public void drawGameWindow() {
		addEvent(gc);
		gamewindowanimation = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				//System.out.println("s");
				updateWord();
				updateDetail();
	
			}
		};
		gamewindowanimation.start();
	}
	public void addEvent(GraphicsContext gc) {
		this.setOnKeyPressed((KeyEvent) ->{
			if(!KeyEvent.getCode().equals(KeyCode.ENTER)) {
				temp += KeyEvent.getCode().toString();
			}
			else {
				System.out.println(temp);
				RenderableHolder.getInstance().check(temp);
				score ++;
				temp = "" ;
			}
		});
	}
	public void addGamescreen() {
		gamescreen = new GameScreen();
		RenderableHolder.getInstance().add(gamescreen);
	}
	
	public void addWord() {
		for (int i = 0; i < 1; i++) {
			word = new Word();

			double px = 600 * Math.random();
			double py = -900 * Math.random();

			word.setPosition(px, py);
			word.setWordstring("cat");
			wordList.add(word);
			RenderableHolder.getInstance().add(word);
		}
	}
	public void addAll() {
		addGamescreen();
		addWord();
	}
	
	public void updateWord() {
		RenderableHolder.getInstance().updatePos();
	}
	public void updateDetail() {
		RenderableHolder.getInstance().draw(gc);
		RenderableHolder.getInstance().update();
	}
	
}