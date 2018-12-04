package Drawing;

import Logic.Background;
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

public class Gameover extends Canvas{
	private static GraphicsContext gc;
	private static boolean isframeUp = true ;
	private static AnimationTimer gameoveranimation ;
	
	public Gameover() {
		
	}
	public static void drawGameover(GraphicsContext gc) {
		gameoveranimation = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				setBackground(gc);
			}
		};
		gameoveranimation.start();
	}
	
	public static void setBackground(GraphicsContext gc) {
		gc.setFill(Color.GRAY);
		gc.fillRect(0, 0, 550, 750);
		Font thefont = Font.font("Times New Roman", FontWeight.BOLD, 60);
		gc.setFont(thefont);
		gc.strokeText("Game Over", 100, 70);
	}
	public static void drawFrame(GraphicsContext gc) {

		if (isframeUp) {
			gc.setLineWidth(4);
			gc.setStroke(Color.WHITE);
			gc.strokeRect(110, 300, 100, 50);
		} else if (!isframeUp) {
			gc.setLineWidth(4);
			gc.setStroke(Color.WHITE);
			gc.strokeRect(370, 300, 100, 50);
		}

	}
	
	public static void setText(GraphicsContext gc) {
		Font thefont = Font.font("Times New Roman", FontWeight.BOLD, 30);
		gc.setFont(thefont);
		gc.setFill(Color.YELLOW);
		gc.fillText("PLAY AGAIN", 110, 300);
		gc.fillText("QUIT", 370, 300);
	}
	public static void startgameover(GraphicsContext gc) {
		drawGameover(gc);
	}

}
