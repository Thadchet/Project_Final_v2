package Logic;

import javafx.scene.image.Image;
import sharedObject.IRenderable;

public abstract class Entity implements IRenderable{
	
	protected Image image;
	protected double x,y ;
	protected double width,height ;
	
	protected Entity() {
		
	}
	
	public double getWidth() {
		return width;
	}
	public double getHeight() {
		return height;
	}
	protected Entity(double x , double y){
		this.x = x ;
		this.y = y ;
	
	}

	public abstract void setImage(Image i);

	public abstract void setImage(String filename,int width , int height);
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
}

