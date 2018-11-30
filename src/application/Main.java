package application;

import java.util.ArrayList;
import java.util.Iterator;

import Logic.Wizard;
import Logic.Word;
import Logic.Background;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Main extends Application {
	public boolean spaceRepeat = false;

	@Override
	public void start(Stage theStage) {

		theStage.setTitle("Space Adventure!");

		Group root = new Group();

		Scene theScene = new Scene(root);

		theStage.setScene(theScene);

		Canvas canvas = new Canvas(700, 900);

		root.getChildren().add(canvas);

		ArrayList<String> input = new ArrayList<String>();
		ArrayList<String> strings = new ArrayList<String>();

		theScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
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

		theScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();

				input.remove(code);
				boolean spceRepeat;
				if (code == "SPACE")
					spceRepeat = false;
			}
		});

		Background bg = new Background();

		GraphicsContext gc = canvas.getGraphicsContext2D();

		Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
		gc.setFont(theFont);
		gc.setFill(Color.WHITE);
		// gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		theStage.setResizable(false);

		theStage.show();

		LongValue lastNanoTime = new LongValue(System.nanoTime());

		IntValue score = new IntValue(0);

		Wizard wizard = new Wizard();

		ArrayList<Word> wordList = new ArrayList<Word>();

		for (int i = 0; i < 15; i++) {
			Word word = new Word();
			word.setImage("hammer.png");

			double px = 600 * Math.random();
			double py = -900 * Math.random();

			double velY = 20;
			word.setPosition(px, py);
			word.setXspeed(0);
			word.setYspeed(velY);
			wordList.add(word);
		}

		new AnimationTimer() {
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
				/*if (input.contains("SPACE")) {
					
					Bullet a = spaceship.shoot();
					
					bulletcount.add(a);
					input.remove("SPACE");
				}*/

				wizard.update(elapsedTime);

				for (String s : strings) {
					//b.update(elapsedTime);
					
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

				/*for (int i = 0; i < bulletcount.size(); i++) {
					bulletcount.get(i).render(gc);
				}*/

				for (int i = 0; i < wordList.size(); i++) {
					Word word = wordList.get(i);
					word.render(gc);
				}

				String pointsText = "Score : " + (100 * score.value);
				gc.fillText(pointsText, 50, 50);
				gc.strokeText(pointsText, 50, 50);
			}
		}.start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

