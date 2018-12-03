package Logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Background extends Entity {
	public String image_path = ClassLoader.getSystemResource("image/").toString();

	public Background(int width , int height) {
		setImage(image_path+"bg.gif",width ,height);
		this.setPosition(width, height);
	}

	@Override
	public void setImage(Image i) {
		image = i;
		width = i.getWidth(); 
		height = i.getHeight();
	}

	@Override
	public void setImage(String filename,int width , int height) {
		Image i = new Image(filename, 700, 900, false, false);
		setImage(i);
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
		return true ;
	}
	
	
	

}
