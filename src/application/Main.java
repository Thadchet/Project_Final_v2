package application;
	
import Drawing.StartWindow;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		StackPane root = new StackPane();
		StartWindow startwindow = new StartWindow();
		
		root.getChildren().add(startwindow);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Tank game");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
