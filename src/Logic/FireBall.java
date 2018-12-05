package Logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class FireBall extends Entity implements IRenderable {
	
	protected double bX;
	protected double bY;
	protected double dXY;
	protected double dX;
	protected double dY;
	private boolean isVisible = true;
	private boolean isDestory = false;
	protected double i = 0 ;
	private Image fire ;
	private double cos ;
	private double sin ;

	public FireBall(double x, double y) {
		super(x,y);
		this.bX = 10;
		this.bY = 570;
		this.dX = Math.abs(x-bX);
		this.dY = Math.abs(y-bY);
		this.dXY = Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
		this.sin = dY/dXY ;
		this.cos = dX/dXY ;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
		if (isVisible() && i < 1 ) {
			gc.drawImage(RenderableHolder.spell,120+dXY*cos*i-80,640-dXY*sin*i ,20,20);
		}
		i = i + 0.1 ;
		if(i > 1) {
			setIsvisible(false);
		}
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

	@Override
	public void setImage(Image i) {
		// TODO Auto-generated method stub
		image = i;
		width = i.getWidth();
		height = i.getHeight();
	}

	@Override
	public void setImage(String filename, int width, int height) {
		// TODO Auto-generated method stub

	}
	public void setIsvisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public void setIsdestory(boolean isDestory) {
		this.isDestory = isDestory;
	}

}
