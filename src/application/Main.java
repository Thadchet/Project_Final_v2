package application;

	
import Drawing.StartWindow;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	

	@Override
	public void start(Stage stage) {
		StartWindow startwindow = new StartWindow(stage);
		startwindow.startAnimation();
		stage.show();

	}
	public static void main(String[] args) {
		launch(args);
	}
}

