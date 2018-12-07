package Logic;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class SnowSkill1 implements IRenderable{
	private double speedY = -1.3 ;
	private double speedX ;
	private double X ;
	private double Y ;
	private boolean isDestroyed ;
	private boolean isVisible ;
	public SnowSkill1() {
		this.X = Math.random() * 550 ;
		this.Y = new Random().nextInt(750 + 750) - 750 ;
		//System.out.println(Y);
		this.isDestroyed = false ;
		this.isVisible = true ;
		}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
		gc.drawImage(RenderableHolder.snowskill1,X,Y+speedY,30,30);
	}
	public void setPosition(double x , double y) {
		this.X = x ;
		this.Y = y ;
	}
	public void updatePos() {
		speedX = new Random().nextInt(1 + 6) -3 ;
		//System.out.println(speedX);
		setPosition(getX()-speedX, getY() - speedY);
		//System.out.println(getY());
	}

	public double getSpeedY() {
		return speedY;
	}

	public double getX() {
		return X;
	}

	public double getY() {
		return Y;
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return isDestroyed;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible;
	}
	public void setDestroy() {
		this.isDestroyed = true ;
	}
	
}
