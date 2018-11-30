package Logic;

import Logic.CollidableEntity;
import Logic.Entity;

public abstract class CollidableEntity extends Entity{
	protected int radius;
	
	protected boolean collideWith(CollidableEntity other){
		return Math.hypot(this.x-other.x, this.y-other.y) <= this.radius+other.radius;
	}
}

