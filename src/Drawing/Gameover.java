package Drawing;

import Logic.Background;
import Logic.Wizard;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import sharedObject.RenderableHolder;

public class Gameover {
	
	private static Background gameoverbg = new Background(RenderableHolder.image_path + "gameoverbg.png", 0, 0);

	public Gameover() {
	}

	public static void drawGameover(GraphicsContext gc) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				int i = 1 ;
				// TODO Auto-generated method stub
				setTextGameover(gc);
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(i);
				setTextGameFinish(gc);
			}
		});
		thread.start();
	}
	
	public static void drawGameWinner(GraphicsContext gc) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				int i = 1 ;
				// TODO Auto-generated method stub
				setTextGameWinner(gc);
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(i);
				setTextGameFinish(gc);
			}
		});
		thread.start();
	}

	public static void setTextGameover(GraphicsContext gc) {
		gc.setFill(Color.ORANGERED);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		Font font = new Font("Agency FB", 100);
		gc.setFont(font);
		gc.fillText("GAME OVER", 100, 150);
		gc.strokeText("GAME OVER", 100, 150);
	} 
	public static void setTextGameWinner(GraphicsContext gc) {
		gc.setFill(Color.ORANGERED);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		Font font = new Font("Agency FB", 100);
		gc.setFont(font);
		gc.fillText("WINNER", 100, 150);
		gc.strokeText("WINNER", 100, 150);
	} 

	public static void setTextGameFinish(GraphicsContext gc) {
		gameoverbg.draw(gc);
		gc.setFill(Color.PAPAYAWHIP);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		Font font = new Font("Agency FB", 80);
		gc.setFont(font);
		gc.fillText("YOUR SCORE", 120,150);
		gc.setFill(Color.BLACK);
		gc.fillText(String.valueOf(Wizard.score),260,250);
		gc.setFont(font);
		gc.setFill(Color.BROWN);
		gc.fillText("HIGH SCORE", 130,350);
		gc.fillText(String.valueOf(GameWindow.high_score), 260, 450);
	}

	public static void startgamefinish(GraphicsContext gc) {
		drawGameover(gc);
	}

}
