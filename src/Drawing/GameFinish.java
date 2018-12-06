package Drawing;

import Logic.Background;
import Logic.Wizard;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import sharedObject.RenderableHolder;

public class GameFinish {

	private static Background gameoverbg = new Background(RenderableHolder.image_path + "gameoverbg.png", 0, 0);

	public GameFinish() {
	}

	public static void drawGameover(GraphicsContext gc,GameScreen gamescreen) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				int i = 1;
				// TODO Auto-generated method stub
				setTextGameover(gc,gamescreen);
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(i);
				setTextGameFinish(gc,gamescreen);
			}
		});
		thread.start();
	}

	public static void drawGameWinner(GraphicsContext gc,GameScreen gamescreen) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				int i = 1;
				// TODO Auto-generated method stub
				setTextGameWinner(gc,gamescreen);
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(i);
				setTextGameFinish(gc,gamescreen);
			}
		});
		thread.start();
	}

	public static void setTextGameover(GraphicsContext gc,GameScreen gamescreen) {
		gc.setFill(Color.ORANGERED);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		Font font = new Font("Agency FB", 100);
		gc.setFont(font);
		gc.fillText("GAME OVER", 100, 150);
		gc.strokeText("GAME OVER", 100, 150);
	}

	public static void setTextGameWinner(GraphicsContext gc,GameScreen gamescreen) {
		gc.setFill(Color.ORANGERED);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		Font font = new Font("Agency FB", 100);
		gc.setFont(font);
		gc.fillText("WINNER", 150, 150);
		gc.strokeText("WINNER", 150, 150);
	}

	public static void setTextGameFinish(GraphicsContext gc,GameScreen gamescreen) {
		gameoverbg.draw(gc);
		gc.setFill(Color.PAPAYAWHIP);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		Font font = new Font("Agency FB", 80);
		gc.setFont(font);
		gc.fillText("YOUR SCORE", 120, 150);
		gc.setFill(Color.BLACK);
		gc.fillText(String.valueOf(gamescreen.getScore()), 260, 250);
		gc.setFont(font);
		gc.setFill(Color.BROWN);
		gc.fillText("HIGH SCORE", 130, 350);
		gc.fillText(String.valueOf(GameWindow.high_score), 260, 450);
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Font font = new Font("Agency FB", 40);
				gc.setFont(font);
				gc.setFill(Color.FIREBRICK);
				gc.fillText("PLEASE ENTER TO PLAY AGAIN ", 100, 550);
			}
		});
		thread.start();
	}

	public static void startgamefinish(GraphicsContext gc, boolean isGameover,GameScreen gamescreen) {
		if (isGameover) {
			drawGameover(gc,gamescreen);
		} else {
			drawGameWinner(gc,gamescreen);
		}
	}

}
