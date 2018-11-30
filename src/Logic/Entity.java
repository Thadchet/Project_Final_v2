package Logic;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.IRenderable;

public abstract class Entity implements IRenderable{
	
	protected Image image;
	protected double x,y,width,height,xspeed,yspeed;
	protected int z;
	protected boolean visible,destroyed;
	protected GraphicsContext gc;
	
	protected Entity(){
		x = 0 ;
		y = 0 ;
		yspeed = 0 ;
		xspeed = 0 ;
		visible = true;
		destroyed = false;
	}
	

	public abstract void setImage(Image i);

	public abstract void setImage(String filename,int width , int height);
	
	public boolean isDestroyed(){
		return destroyed;
	}
	
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

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}


	public double getXspeed() {
		return xspeed;
	}

	public void setXspeed(double xspeed) {
		this.xspeed = xspeed;
	}

	public double getYspeed() {
		return yspeed;
	}

	public void setYspeed(double yspeed) {
		this.yspeed = yspeed;
	}

	public boolean isVisible(){
		return visible;
	}
	
	public void render(GraphicsContext gc) {
		if (!destroyed)
			gc.drawImage(image, x, y);
	}
	
	public boolean intersects(Entity s) {
		return s.getBoundary().intersects(this.getBoundary());
	}
	
	public Rectangle2D getBoundary() {
		return new Rectangle2D(x, y, width, height);
	}

	public int getZ(){
		return z;
	}
	
	public void addVelocity(double x, double y) {
		xspeed += x;
		yspeed += y;
	}
	
	public void update(double time) {
		x += xspeed * time;
		y += yspeed * time;

	}
	
	public void erase() {
		this.image=null;
		this.setPosition(9999, 9999);
		this.setXspeed(0);
		this.setYspeed(0);
	}
}

