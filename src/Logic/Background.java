package Logic;

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
	
	
	

}
