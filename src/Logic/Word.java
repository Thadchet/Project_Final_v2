package Logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.RenderableHolder;

public class Word extends Entity {
	private String wordstring ;
	public String image_path = ClassLoader.getSystemResource("ImWord/").toString();
	private boolean isVisible = true;
	private boolean isDestory = false;
	private double speed ;
	private Thread t;

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Word(String wordstring,String image,double speed) {
		setWordstring(wordstring);
		setImage(image ,110,63);
		setSpeed(-speed);
	}

	public String getWordstring() {
		return wordstring;
	}

	public void setWordstring(String wordstring) {
		this.wordstring = wordstring;
	}

	@Override
	public void setImage(Image i) {
		image = i;
		width = i.getWidth();
		height = i.getHeight();
	}

	@Override
	public void setImage(String filename, int width, int height) {
		Image i = new Image(filename,width,height, false, false);
		setImage(i);
	}

	public void updatePos(double speed) {
		setPosition(getX(), getY() - speed);
		//System.out.println(getY());
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(image, x, y);
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return isDestory;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible;
	}

	public void setIsvisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public void setIsdestory(boolean isDestory) {
		this.isDestory = isDestory;
	}

}
