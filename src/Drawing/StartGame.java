package Drawing;

import Logic.Background;
import Logic.Wizard;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sharedObject.RenderableHolder;

public class StartGame {
	public static GraphicsContext gc;
	private AnimationTimer animation;
	private AnimationTimer soundanimation;
	private Stage primarystage;
	private Canvas canvas;
	static Scene scene;
	private Wizard wizard;
	private Background background;
	private boolean isframeUp = true;
	private int menuSelected = 1;
	static StackPane pane;

	public StartGame(Stage primarystage) {
		canvas = new Canvas(550, 750);
		this.primarystage = primarystage;
		primarystage.setResizable(false);
		gc = canvas.getGraphicsContext2D();
		RenderableHolder.open.play();
		wizard = new Wizard();
		background = new Background(RenderableHolder.image_path+"background.gif",0, 0);

	}

	public void setBackground() {

		background.draw(gc);
		wizard.draw(gc);
		wizard.updatePosstart();
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
				if (RenderableHolder.open.isPlaying() == false)
					RenderableHolder.open.play();
			}
		};
		soundanimation.start();

	}


	public void startAnimation() {
		draw(gc);
	}

	public void next() {
		animation.stop();
		soundanimation.stop();
		RenderableHolder.open.stop();
		GameWindow gamewindow = new GameWindow(primarystage);
		gamewindow.drawGameWindow();

	}

	public void addAction() {
		scene.setOnKeyPressed((KeyEvent) -> {
			if (KeyEvent.getCode() == KeyCode.DOWN) {
				System.out.println("Down");
				if (menuSelected != 0) {
					RenderableHolder.menu.play();
					menuSelected--;
				}
				this.isframeUp = false;
			}
			if (KeyEvent.getCode() == KeyCode.UP) {
				System.out.println("Up");
				if (menuSelected != 1) {
					RenderableHolder.menu.play();
					menuSelected++;
				}
				this.isframeUp = true;
			}
			if (KeyEvent.getCode() == KeyCode.ENTER) {
				if (menuSelected == 1) {
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

	public Scene getScene() {
		return scene;
	}

}
