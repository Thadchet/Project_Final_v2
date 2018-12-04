package Logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Wizard extends Entity {
	public String image_path = ClassLoader.getSystemResource("image/").toString();
	public static int score = 0;
	public static int life = 5;

	public Wizard() {
		setImage(image_path + "wizard1.gif", 200, 200);
		this.setPosition(300, 500);
	}

	public void decreaseLife() {
		int temp = life-1 ;
		if(temp < 0) {
			temp = 0 ;
		}
		life = temp ;
	}

	public void addScore() {
		this.score += 1;
	}

	@Override
	public void setImage(String filename, int width, int height) {
		Image i = new Image(filename, width, height, false, false);
		setImage(i);
	}

	@Override
	public void setImage(Image i) {
		image = i;
		width = i.getWidth();
		height = i.getHeight();
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(image, x, y);
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
