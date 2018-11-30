package Logic;

import javafx.scene.image.Image;

public class Wizard extends Entity {
	public String image_path = ClassLoader.getSystemResource("image/").toString();
	public Wizard(int x , int y , int width , int height) {
        setImage(image_path+"wizard1.gif",width,height);
		
		this.setPosition(x,y);
	
	}
	
	@Override
	public void setImage(String filename,int width ,int height) {
		Image i = new Image(filename,width,height,false,false);
        setImage(i);
	}

	@Override
	public void setImage(Image i) {
		image = i;
        width = i.getWidth();
        height = i.getHeight();
	}
}
