package Drawing;

import Logic.Background;
import Logic.Wizard;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class StartWindow {
	public static GraphicsContext gc;
	public String image_path = ClassLoader.getSystemResource("image/").toString();
	public String sound_path = ClassLoader.getSystemResource("sound/").toString();
	public AudioClip menu = new AudioClip(sound_path + "switch.mp3");
	private AnimationTimer animation;
	private AnimationTimer soundanimation;
	private Stage primarystage;
	private Canvas canvas;
	public AudioClip intro;
	public Scene scene;
	private Wizard wizard; 
	static Background bg;
	private boolean isframeUp = true;
	private int numberselected = 1;
	private GameWindow gamewindow ;
	

	public StartWindow(Stage primarystage) {
		canvas = new Canvas(550, 750);
		this.primarystage = primarystage;
		gc = canvas.getGraphicsContext2D();
		intro = new AudioClip(sound_path + "intro.mp3");
		wizard = new Wizard(300, 500, 200, 200);
		bg = new Background(0, 0);
		gamewindow  = new GameWindow(primarystage);
	}

	public void setBackground() {
		// gc.setFill(Color.BLUE)
		GraphicsContext gc = canvas.getGraphicsContext2D();
		bg.render(gc);
		wizard.render(gc);
		gc.setLineWidth(2);
		gc.setFill(Color.WHITE);
		gc.setStroke(Color.YELLOW);
		Font thefont = Font.font("Times New Roman", FontWeight.BOLD, 60);
		gc.setFont(thefont);
		gc.strokeText("Word Fantacy", 100, 70);
	}

	public void drawFrame(GraphicsContext gc) {

		if (this.isframeUp) {
			gc.setLineWidth(4);
			gc.setStroke(Color.WHITE);
			gc.strokeRect(230, 195, 100, 50);
		} else if (!isframeUp) {
			gc.setLineWidth(4);
			gc.setStroke(Color.WHITE);
			gc.strokeRect(230, 265, 100, 50);
		}

	}

	public void setText(GraphicsContext gc) {
		Font thefont = Font.font("Times New Roman", FontWeight.BOLD, 30);
		gc.setFont(thefont);
		gc.setFill(Color.YELLOW);
		gc.fillText("PLAY", 240, 230);
		gc.fillText("QUIT", 240, 300);
	}

	public void draw(GraphicsContext gc) {
		StackPane pane = new StackPane();
		pane.setPrefSize(550, 750);
		pane.getChildren().addAll(canvas);
		scene = new Scene(pane);
		setBackground();
		addAction();
		primarystage.setScene(scene);
		primarystage.setTitle("Word Fantacy");
		animation = new AnimationTimer() {

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				setBackground();
				setText(gc);
				drawFrame(gc); // to call update();
			}
		};
		animation.start();

		soundanimation = new AnimationTimer() {

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				if (intro.isPlaying() == false)
					playsong();
			}
		};
		soundanimation.start();

	}

	public void drawSelectedColor() {
		setBackground();
		if (numberselected == 0) {
			gc.setStroke(Color.DARKSLATEGREY);
			gc.setLineWidth(5);
			gc.strokeRect(500, 223, 150, 50);
		}
		if (numberselected == 1) {
			gc.setStroke(Color.DARKSLATEGREY);
			gc.setLineWidth(5);
			gc.strokeRect(506, 283, 130, 50);
		}
	}

	public void startAnimation() {
		draw(gc);
	}

	public void playsong() {
		intro = new AudioClip(sound_path + "intro.mp3");
		intro.play();
	}

	public void next() {
		animation.stop();
		soundanimation.stop();
		intro.stop();
		gamewindow.startanimation();
		
	}

	public void addAction() {
		scene.setOnKeyPressed((KeyEvent) -> {
			if (KeyEvent.getCode() == KeyCode.DOWN) {
				if (numberselected != 0) {
					menu.play();
					numberselected--;
				}
				this.isframeUp = false;
			}
			if (KeyEvent.getCode() == KeyCode.UP) {
				if (numberselected != 1) {
					menu.play();
					numberselected++;
				}
				this.isframeUp = true;
			}
			if (KeyEvent.getCode() == KeyCode.ENTER) {
				if (numberselected == 1) {
					next();
				} else {
					Platform.exit();
				}
			}
		});

	}

	public GraphicsContext getGc() {
		return gc;
	}

	public String getImage_path() {
		return image_path;
	}

	public String getSound_path() {
		return sound_path;
	}

	public AnimationTimer getAnimation() {
		return animation;
	}

	public AnimationTimer getSoundanimation() {
		return soundanimation;
	}

	public Stage getPrimarystage() {
		return primarystage;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public AudioClip getIntro() {
		return intro;
	}

	public Scene getScene() {
		return scene;
	}

}
