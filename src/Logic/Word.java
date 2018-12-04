package Logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Word extends Entity {
	private String wordstring ;
	public String image_path = ClassLoader.getSystemResource("ImWord/").toString();
	private boolean isVisible = true;
	private boolean isDestory = false;
	private double speed ;

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Word(String wordstring,String image) {
		setWordstring(wordstring);
		setImage(image ,110,63);
<<<<<<< HEAD
		setSpeed(-1);
||||||| merged common ancestors
		setSpeed(-2);
=======
		setSpeed(-6);
>>>>>>> 3d5d449367b63be3a5aa9b18e1db6aca9bd26324
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
