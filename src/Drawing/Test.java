package Drawing;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Test extends Canvas{
	protected GraphicsContext gc ;
	
	public Test(Stage stage) {
		setWidth(800);
		setHeight(450);
		GraphicsContext gc = getGraphicsContext2D();
		StackPane pane = new StackPane();
		pane.getChildren().add(gc.getCanvas());
		Scene scene = new Scene(pane);
		
		
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.GREEN);
	}
	public void startAnimation() {
		draw(gc);
	}
}
