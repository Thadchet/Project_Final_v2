package Logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Word extends Entity {
	private String wordstring = "a";
	public String image_path = ClassLoader.getSystemResource("image/").toString();
	private boolean isVisible = true ;
	private boolean isDestory = false ;
	public Word() {
		setImage(image_path + "rock.png", 100, 100);
		
		//this.setPosition(300, 50);
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
		Image i = new Image(filename, 60, 60, false, false);
		setImage(i);
	}

	public void updatePos(double time) {
		setPosition(getX(),getY()-time);
		System.out.println(getY());
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(image, x, y);
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return isDestory ;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible ;
	}
	
	public void setIsvisible(boolean isVisible) {
		this.isVisible = isVisible ;
	}
	public void setIsdestory(boolean isDestory) {
		this.isDestory = isDestory ;
	}
	
}
