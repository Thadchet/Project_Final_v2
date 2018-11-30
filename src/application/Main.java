package application;
	
import Drawing.StartWindow;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


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
