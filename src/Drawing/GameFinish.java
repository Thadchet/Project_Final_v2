package Drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import sharedObject.RenderableHolder;

public class GameFinish {
	private static String[] textYS = { "Y", "O", "U", "R", " ", "S", "C", "O", "R", "E" };
	private static String[] textHS = { "H", "I", "G", "H", " ", "S", "C", "O", "R", "E" };

	public GameFinish() {
	}

	public static void drawGameover(GraphicsContext gc, GameScreen gamescreen) {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				int i = 1;
				setTextGameover(gc, gamescreen);
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(i);
				setTextGameFinish(gc, gamescreen);
			}
		});
		t1.start();
	}

	public static void drawGameWinner(GraphicsContext gc, GameScreen gamescreen) {
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				int i = 1;
				setTextGameWinner(gc, gamescreen);
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(i);
				setTextGameFinish(gc, gamescreen);
			}
		});
		t2.start();
	}

	public static void setTextGameover(GraphicsContext gc, GameScreen gamescreen) {
		gc.setFill(Color.ORANGERED);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		Font font = new Font("Agency FB", 100);
		gc.setFont(font);
		gc.fillText("GAME OVER", 100, 150);
		gc.strokeText("GAME OVER", 100, 150);
	}

	public static void setTextGameWinner(GraphicsContext gc, GameScreen gamescreen) {
		gc.setFill(Color.ORANGERED);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		Font font = new Font("Agency FB", 100);
		gc.setFont(font);
		gc.fillText("WINNER", 150, 150);
		gc.strokeText("WINNER", 150, 150);
	}

	public static void setTextGameFinish(GraphicsContext gc, GameScreen gamescreen) {
		gc.setFill(Color.GRAY);
		gc.fillRect(0, 0, 550, 750);
		gc.setFill(Color.PAPAYAWHIP);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		Font font = new Font("Agency FB", 80);
		gc.setFont(font);
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				String tempYS = "";
				String tempHS = "";
				for (int i = 0; i < textYS.length; i++) {
					gc.setFont(font);
					tempYS = tempYS + textYS[i];
					tempHS = tempHS + textHS[i];
					gc.fillText(tempYS, 120, 150);
					gc.fillText(tempHS, 120, 350);
					if (!RenderableHolder.typing.isPlaying())
						RenderableHolder.typing.play();
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				gc.setFont(font);
				gc.setFill(Color.BLACK);
				gc.fillText(String.valueOf(gamescreen.getScore()), 250, 250);
				gc.fillText(String.valueOf(GameWindow.high_score), 250, 450);
			}
		});

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				int time = 0;
				while (time < 100) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Font font = new Font("Agency FB", 40);
					gc.setFont(font);
					gc.setFill(Color.FIREBRICK);
					gc.fillText("PLEASE ENTER TO PLAY AGAIN ", 100, 550);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					gc.setFill(Color.GRAY);
					gc.fillRect(100, 450, 500, 100);
					time++;
				}
			}
		});
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t3.start();
		try {
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.start();
	}

	public static void startgamefinish(GraphicsContext gc, boolean isGameover, GameScreen gamescreen) {
		if (isGameover) {
			drawGameover(gc, gamescreen);
		} else {
			drawGameWinner(gc, gamescreen);
		}
	}

}
