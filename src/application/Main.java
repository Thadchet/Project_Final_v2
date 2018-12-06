package application;

	
import Drawing.StartGame;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	

	@Override
	public void start(Stage stage) {
		StartGame startwindow = new StartGame(stage);
		startwindow.startAnimation();
		stage.show();

	}
	public static void main(String[] args) {
		launch(args);
	}
}

