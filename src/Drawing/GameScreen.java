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
	private Image laser ;
	private Image skill1 ;
	private Image skill2 ;
	private Image skillused ;
	public GameScreen() {
		// TODO Auto-generated constructor stub
		setImage();
	}
	public void setImage() {
		bg = new Image(image_path+"bg.gif");
		life = new Image(image_path+"life.gif");
		laser = new Image(image_path+"laser.gif");
		skill2 = new Image(image_path+"skill1.jpg");
		skill1 = new Image(image_path+"skill2.jpg");
		skillused = new Image(image_path+"skillused.png");
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(bg, 0, 0);
		Font font = new Font("Agency FB",30);
		gc.setFont(font);
		gc.fillText("Score : "+ String.valueOf(Wizard.score), 50, 50);
		gc.fillText(GameWindow.temp,50,100);
		gc.drawImage(life,430,20,40,40);
		gc.drawImage(skill1, 480, 120, 40, 40);
		gc.drawImage(skill2, 480, 70, 40, 40);
		if(GameWindow.skillused1) {
			gc.drawImage(skillused, 480, 70,40,40);
		}
		if(GameWindow.skillused2) {
			gc.drawImage(skillused, 480, 120,40,40);
		}
		
		gc.fillText(" x "+ String.valueOf(Wizard.life), 470,50);
		gc.drawImage(laser,20, 650,570,100);
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
