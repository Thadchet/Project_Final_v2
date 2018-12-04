package Drawing;

import Logic.Background;
import Logic.Wizard;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sharedObject.RenderableHolder;

public class Gameover {
	private static GraphicsContext gc;
	private static boolean isframeUp = true;
	private static AnimationTimer gameoveranimation;
	private static Background gameoverbg = new Background(RenderableHolder.image_path + "gameoverbg.png", 0, 0);

	public Gameover() {
	}

	public static void drawGameover(GraphicsContext gc) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				int i = 1 ;
				// TODO Auto-generated method stub
				setBackground1(gc);
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(i);
				setBackground2(gc);
			}
		});
		thread.start();
	}

	public static void setBackground1(GraphicsContext gc) {
		gc.setFill(Color.ORANGERED);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		Font font = new Font("Agency FB", 60);
		gc.setFont(font);
		gc.fillText("GAME OVER", 100, 150);
		gc.strokeText("GAME OVER", 100, 150);
	}

	public static void setBackground2(GraphicsContext gc) {
		gameoverbg.draw(gc);
		gc.setFill(Color.ORANGERED);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		Font font = new Font("Agency FB", 60);
		gc.setFont(font);
		gc.fillText("GAME OVER", 100, 150);
		gc.strokeText("GAME OVER", 100, 150);
		gc.fillText("YOUR SCORE", 100,350);
		gc.setFill(Color.BLACK);
		gc.fillText(String.valueOf(Wizard.score),170,450);
	}

	public static void startgameover(GraphicsContext gc) {
		drawGameover(gc);
	}

}
