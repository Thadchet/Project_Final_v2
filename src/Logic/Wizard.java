package Logic;

import javafx.scene.canvas.GraphicsContext;

public class Wizard extends CollidableEntity {
	private static final int speed = 5;
	
	public Wizard(double x , double y) {
		this.x = x ;
		this.y = y;
	}
	
	public void Left() {
		this.x -= this.speed ;
	}
	
	public void Right() {
		this.x += this.speed ;
	}
	
	
	

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}
}
