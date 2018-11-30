package Logic;

import javafx.scene.image.Image;

public class Wizard extends Entity {
	public Wizard() {
        setImage("hammer.png");
		
		this.setPosition(300, 700);
	
	}
	
	@Override
	public void setImage(String filename) {
		Image i = new Image(filename,80,80,false,false);
        setImage(i);
	}

	@Override
	public void setImage(Image i) {
		image = i;
        width = i.getWidth();
        height = i.getHeight();
	}
}
