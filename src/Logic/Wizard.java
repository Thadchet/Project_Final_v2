package Logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Wizard extends Entity {
	public String image_path = ClassLoader.getSystemResource("image/").toString();
	public static double posx = 300;
	public static double posy = 400;
	private double speedx = 2;
	private double speedy = 2;

	public Wizard() {
		setImage(image_path + "wizard1.gif", 170, 170);
		this.setPosition(10, 560);
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
		gc.drawImage(image, x, y);
	}

	@Override
	public boolean isDestroyed() {
		return false;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	public void updatePosstart() {
		Wizard.posx += speedx;
		Wizard.posy += speedy;
		if (Wizard.posx > 500) {
			Wizard.posx = -80;
		}
		if (Wizard.posy < 360) {
			this.speedy = 1.5;
		}
		if (Wizard.posy > 440) {
			this.speedy = -1.5;
		}
		setPosition(posx, posy);
	}

	public void updatePosren() {
		Wizard.posx += speedx;
		Wizard.posy += speedy;
		if (Wizard.posy > 500) {
			this.speedy = -1.5;
		}
		if (Wizard.posy < 100) {
			this.speedy = 1.5;
		}
		if (Wizard.posx < 0) {
			this.speedx = 1.5;
		}
		if (Wizard.posx > 410) {
			this.speedx = -1.5;
		}
		setPosition(posx, posy);
	}

	public double getPosx() {
		return posx;
	}

	public void setPosx(double posx) {
		Wizard.posx = posx;
	}

	public double getPosy() {
		return posy;
	}

	public void setPosy(double posy) {
		Wizard.posy = posy;
	}

}
