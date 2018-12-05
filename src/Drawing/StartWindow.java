package Drawing;

import Logic.Background;
import Logic.Wizard;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.Event;
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
	private AnimationTimer animation;
	private AnimationTimer soundanimation;
	private Stage primarystage;
	private Canvas canvas;
	public AudioClip intro;
	static Scene scene;
	private Wizard wizard;
	private Background background;
	private boolean isframeUp = true;
	private int numberselected = 1;
	static StackPane pane;

	public StartWindow(Stage primarystage) {
		canvas = new Canvas(550, 750);
		this.primarystage = primarystage;
		primarystage.setResizable(false);
		gc = canvas.getGraphicsContext2D();
		intro = new AudioClip(sound_path + "open.mp3");
		wizard = new Wizard();
		background = new Background(RenderableHolder.image_path+"bg.gif",0, 0);

	}

	public void setBackground() {

		background.draw(gc);
		wizard.draw(gc);
		gc.setLineWidth(2);
		gc.setFill(Color.WHITE);
		gc.setStroke(Color.YELLOW);
		Font thefont = Font.font("Agency FB", FontWeight.BOLD, 75);
		gc.setFont(thefont);
		gc.strokeText("Word Fantacy", 115, 95);
	}

	public void drawFrame(GraphicsContext gc) {

		if (this.isframeUp) {
			gc.setLineWidth(4);
			gc.setStroke(Color.WHITE);
			gc.strokeRect(215, 195, 100, 50);
		} else if (!isframeUp) {
			gc.setLineWidth(4);
			gc.setStroke(Color.WHITE);
			gc.strokeRect(215, 265, 100, 50);
		}

	}

	public void setText(GraphicsContext gc) {
		Font thefont = Font.font("Agency FB", FontWeight.BOLD, 30);

		gc.setFont(thefont);
		gc.setFill(Color.YELLOW);
		gc.fillText("PLAY", 240, 230);
		gc.fillText("QUIT", 240, 300);
	}

	public void draw(GraphicsContext gc) {
		pane = new StackPane();
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


	public void startAnimation() {
		draw(gc);
	}

	public void playsong() {
		intro = new AudioClip(sound_path + "open.mp3");
		intro.play();
	}

	public void next() {
		animation.stop();
		soundanimation.stop();
		intro.stop();
		GameWindow gamewindow = new GameWindow(primarystage);
		gamewindow.drawGameWindow();

	}

	public void addAction() {
		scene.setOnKeyPressed((KeyEvent) -> {
			if (KeyEvent.getCode() == KeyCode.DOWN) {
				System.out.println("Down");
				if (numberselected != 0) {
					RenderableHolder.menu.play();
					numberselected--;
				}
				this.isframeUp = false;
			}
			if (KeyEvent.getCode() == KeyCode.UP) {
				System.out.println("Up");
				if (numberselected != 1) {
					RenderableHolder.menu.play();
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
