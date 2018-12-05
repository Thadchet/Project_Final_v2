package Logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Wizard extends Entity {
	public String image_path = ClassLoader.getSystemResource("image/").toString();
	public static int score = 0;
	public static int life = 10;
	private double posx = 300 ;
	private double posy = 400 ;
	private double speedx = 2; 
	private double speedy = 2;

	
	public Wizard() {
		setImage(image_path + "wizard1.gif", 170, 170);
		this.setPosition(10,560);
		
	}
	public void increaseLife() {
		life += 2 ;
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
	
	public void updatePosstart() {
		this.posx += speedx ;
		this.posy += speedy ;
		if(this.posx>500) {
			this.posx = -80 ;
		}
		if(this.posy<360) {
			this.speedy = 1.5;
		}
		if(this.posy>440) {
			this.speedy = -1.5;
		}
		setPosition(posx,posy);
	}
	
	public void updatePosren() {
		this.posx += speedx ;
		this.posy += speedy ;
		if(this.posy>500) {
			this.speedy = -1.5;
		}
		if(this.posy<100) {
			this.speedy = 1.5;
		}
		if(this.posx< 0) {
			this.speedx = 1.5;
		}
		if(this.posx>410) {
			this.speedx = -1.5;
		}
		setPosition(posx,posy);
	}
	public double getPosx() {
		return posx;
	}
	public void setPosx(double posx) {
		this.posx = posx;
	}
	public double getPosy() {
		return posy;
	}
	public void setPosy(double posy) {
		this.posy = posy;
	}
	
	

}
