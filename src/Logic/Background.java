package Logic;

import javafx.scene.image.Image;

public class Background extends Entity {

	public Background() {
		setImage("rock.png");
		this.setPosition(0, 0);
	}

	@Override
	public void setImage(Image i) {
		image = i;
		width = i.getWidth();
		height = i.getHeight();
	}

	@Override
	public void setImage(String filename) {
		Image i = new Image(filename, 700, 900, false, false);
		setImage(i);
	}
	
	
	

}
