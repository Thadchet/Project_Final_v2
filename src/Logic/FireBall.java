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
	private boolean isDestroy = false;
	protected double i = 0;
	private double cos;
	private double sin;

	public FireBall(double x, double y, double bX, double bY) {
		super(x, y); // terminate of object
		this.dX = x - bX;
		this.dY = y - bY;
		this.bX = bX;
		this.bY = bY;
		this.dXY = Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
		this.sin = dY / dXY;
		this.cos = dX / dXY;
	}

	@Override
	public void draw(GraphicsContext gc) {

		if (isVisible() && i < 1) {

			gc.drawImage(RenderableHolder.spell, bX + 170 + dXY * cos * i - 110, bY + dXY * sin * i + 40, 20, 20);

		}
		i = i + 0.1;
		if (i > 1) {
			setIsvisible(false);
			setIsdestroy(true);
		}
	}

	@Override
	public boolean isDestroyed() {
		return isDestroy;
	}

	@Override
	public boolean isVisible() {
		return isVisible;
	}

	@Override
	public void setImage(Image i) {
		image = i;
		width = i.getWidth();
		height = i.getHeight();
	}

	@Override
	public void setImage(String filename, int width, int height) {

	}

	public void setIsvisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public void setIsdestroy(boolean isDestroy) {
		this.isDestroy = isDestroy;
	}

	public void updatePoswiz(double x, double y) {
		this.bX = x;
		this.bY = y;
	}

}
