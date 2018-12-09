package application;

	
import Drawing.StartGame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sharedObject.RenderableHolder;


public class Main extends Application {
	

	@Override
	public void start(Stage stage) {
		StartGame startwindow = new StartGame(stage);
		startwindow.startAnimation();
		stage.show();
		stage.setOnHiding(new EventHandler<WindowEvent>() {

	         @Override
	         public void handle(WindowEvent event) {
	             Platform.runLater(new Runnable() {

	                 @Override
	                 public void run() {
	                	 RenderableHolder.soundgame.stop();
	                     System.exit(0);
	                 }
	             });
	         }
	     });
	}
	public static void main(String[] args) {
		launch(args);
	}
}
