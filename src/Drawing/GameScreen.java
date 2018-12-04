package Drawing;

import Logic.Wizard;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sharedObject.IRenderable;


public class GameScreen implements IRenderable{
	private String image_path = ClassLoader.getSystemResource("image/").toString();
	private Image bg ;
	private Image life ;
	public GameScreen() {
		// TODO Auto-generated constructor stub
		setImage();
	}
	public void setImage() {
		bg = new Image(image_path+"bg.gif");
		life = new Image(image_path+"life.gif");
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(bg, 0, 0);
		Font font = new Font("Monospace",30);
		gc.setFont(font);
		gc.fillText("Score : "+ String.valueOf(Wizard.score), 50, 50);
		gc.fillText(GameWindow.temp,50,100);
		gc.drawImage(life,430,20,40,40);
		gc.fillText(" x "+ String.valueOf(Wizard.life), 470,50);
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
