package Drawing;

import Logic.Background;
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

public class Gameover{
	private static GraphicsContext gc;
	private static boolean isframeUp = true ;
	private static AnimationTimer gameoveranimation ;
	private static Background gameoverbg = new Background(RenderableHolder.image_path+"gameoverbg.png",0,0);
	
	public Gameover() {
	}
	public static void drawGameover(GraphicsContext gc) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int check = 0 ;
				while(check < 100) {
					System.out.println(check);
					setBackground1(gc);
					try {
						Thread.sleep(3000);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					setBackground2(gc);
					check++ ;
				}
			}
		});
		thread.start();
	}
	
	public static void setBackground1(GraphicsContext gc) {
		gameoverbg.draw(gc);
		gc.setFill(Color.ORANGERED);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		gc.fillText("GAME OVER", 220, 250);
		gc.strokeText("GAME OVER", 220, 250);
	}

	
	public static void setBackground2(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, 800, 450);
		gameoverbg.draw(gc);
		
	}
	public static void startgameover(GraphicsContext gc) {
		drawGameover(gc);
	}

}
