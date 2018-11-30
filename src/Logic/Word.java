package Logic;

import javafx.scene.image.Image;

public class Word extends Entity {
	private String wordstring = "a";
	public String image_path = ClassLoader.getSystemResource("image/").toString();
	
	public Word(int width ,int height) {
		setImage(image_path+"rock.png",width ,height);

		this.setPosition(300, 50);
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
	public void setImage(String filename,int width ,int height) {
		Image i = new Image(filename, 60, 60, false, false);
		setImage(i);
	}
	
}
