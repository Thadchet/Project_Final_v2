package Logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Background extends Entity {
	public String image_path = ClassLoader.getSystemResource("image/").toString();

	public Background(String i,int width , int height) {
		setImage(i,width ,height);
		this.setPosition(0,0);
	}

	@Override
	public void setImage(Image i) {
		image = i;
		width = i.getWidth(); 
		height = i.getHeight();
	}

	@Override
	public void setImage(String filename,int width , int height) {
		Image i = new Image(filename,0,0, false, false);
		setImage(i);
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(image, 0, 0,550,750);
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
