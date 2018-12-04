package Drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sharedObject.IRenderable;


public class GameScreen implements IRenderable{
	public String image_path = ClassLoader.getSystemResource("image/").toString();
	private Image bg ;
	public GameScreen() {
		// TODO Auto-generated constructor stub
		setImage();
	}
	public void setImage() {
		bg = new Image(image_path+"bg.gif");
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(bg, 0, 0);
		Font font = new Font("Monospace",30);
		gc.setFont(font);
		gc.fillText("Score : "+ String.valueOf(GameWindow.score), 50, 50);
		System.out.println();
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

}
