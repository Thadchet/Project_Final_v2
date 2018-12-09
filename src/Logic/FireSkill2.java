package Logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class FireSkill2 extends Entity implements IRenderable {

	private double bX;
	private double bY;
	private double dXY;
	private double dX;
	private double dY;
	private double i = 0;
	private double cos;
	private double sin;
	private boolean isDestroyed;
	private boolean isVisible;

	public FireSkill2(double x, double y, double bX, double bY) {
		super(x, y); // terminate of object
		this.dX = x - bX;
		this.dY = y - bY;
		this.bX = bX;
		this.bY = bY;
		this.dXY = Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
		this.sin = dY / dXY;
		this.cos = dX / dXY;
		this.isDestroyed = false;
		this.isVisible = true;

	}

	@Override
	public void draw(GraphicsContext gc) {

		if (isVisible() && i < 1) {

			gc.drawImage(RenderableHolder.ballskill2, bX + 170 + dXY * cos * i - 110, bY + dXY * sin * i + 40, 20, 20);

		}
		i = i + 0.1;
		if (i > 1) {
			this.isDestroyed = true;
			this.isVisible = false;
		}
	}

	@Override
	public boolean isDestroyed() {
		return isDestroyed;
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

}
