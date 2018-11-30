package Drawing;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class StartWindow extends Canvas{
	private GraphicsContext gc ;
	
	public StartWindow() {
		super(850,500);
		gc = getGraphicsContext2D();
		
	}
	

}
