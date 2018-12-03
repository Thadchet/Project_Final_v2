package application;

	
import Drawing.StartWindow;
import javafx.animation.Animation;


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
import javafx.scene.layout.StackPane;


import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Main extends Application {
	

	@Override
	public void start(Stage stage) {
		StackPane root = new StackPane();
		StartWindow startwindow = new StartWindow(stage);
		startwindow.startAnimation();
		stage.show();

	}
	public static void main(String[] args) {
		launch(args);
	}
}

