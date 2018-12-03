package Drawing;

import java.util.ArrayList;
import java.util.Iterator;

import Logic.Background;
import Logic.Wizard;
import Logic.Word;
import application.IntValue;
import application.LongValue;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GameWindow {

	public AnimationTimer gameAnimation;
	public boolean spaceRepeat = false;
	public String image_path = ClassLoader.getSystemResource("image/").toString();
	private Group pane = new Group();
	private Scene scene = new Scene(pane);
	private ArrayList<String> input = new ArrayList<String>();
	private ArrayList<String> strings = new ArrayList<String>();
	private ArrayList<Word> wordList = new ArrayList<Word>();
	private GraphicsContext gc = StartWindow.gc ;
	private Background bg = StartWindow.bg ;

	public GameWindow(Stage theStage) {
		
		setAction();
		setBackground();
		storeWord();
		theStage.setResizable(false);
		theStage.show();

	}
	public void startanimation() {
		draw(gc);
	}
	
	public void draw(GraphicsContext gc) {
		LongValue lastNanoTime = new LongValue(System.nanoTime());
		IntValue score = new IntValue(0);
		Wizard wizard = new Wizard();
		gameAnimation = new AnimationTimer() {
			public void handle(long currentNanoTime) {
				// calculate time since last update.
				double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
				lastNanoTime.value = currentNanoTime;

				// game logic

				wizard.setXspeed(0);
				wizard.setYspeed(0);
				if (input.contains("LEFT") && wizard.getX() > 0)
					wizard.addVelocity(-250, 0);
				if (input.contains("RIGHT") && wizard.getX()< 620)
					wizard.addVelocity(250, 0);
				if (input.contains("UP") && wizard.getY() > 0)
					wizard.addVelocity(0, -250);
				if (input.contains("DOWN") && wizard.getY()< 770)
					wizard.addVelocity(0, 250);

				wizard.update(elapsedTime);

				for (String s : strings) {
					
                   Iterator<Word> words = wordList.iterator();
                    while ( words.hasNext() )
                     {
                         Word word1 = words.next();
                         if ( word1.getWordstring()==s )
                         {
                         	/*word1.remove();
                         	w.erase();*/
                        	 score.value++;
                         }
                     }
				}

				//////////////////////////////////////

				for (int i = 0; i < wordList.size(); i++) {
					Word word = wordList.get(i);
					/*
					 * if(enemy.intersects(Bullet b)) { enemy.setDead(true); }
					 */
					word.update(elapsedTime);
				}

				////////////////////////////////////////

				gc.clearRect(0, 0, 700, 900);
				bg.render(gc);
				wizard.render(gc);

				for (int i = 0; i < wordList.size(); i++) {
					Word word = wordList.get(i);
					word.render(gc);
				}

				String pointsText = "Score : " + (100 * score.value);
				gc.fillText(pointsText, 50, 50);
				//gc.strokeText(pointsText, 50, 50);
			}
		};
		gameAnimation.start();
	}
	public void setAction() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				String s = "" ;
				if (code == "SPACE" && !spaceRepeat) {
					input.add(code);
					spaceRepeat = true;
				} else if (!input.contains(code) && code != "SPACE") {
					input.add(code);
				}
				s+=code;
				
			}
		});

		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();

				input.remove(code);
				boolean spceRepeat;
				if (code == "SPACE")
					spceRepeat = false;
			}
		});
	}
	public void setBackground() {

		Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
		gc.setFont(theFont);
		gc.setFill(Color.WHITE);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
	}
	public void storeWord() {
		for (int i = 0; i < 35; i++) {
			Word word = new Word();
			word.setImage(image_path+"rock.png",60,60);

			double px = 600 * Math.random();
			double py = -900 * Math.random();

			double velY = 20;
			word.setPosition(px, py);
			word.setXspeed(0);
			word.setYspeed(velY);
			wordList.add(word);
		}
	}

}
