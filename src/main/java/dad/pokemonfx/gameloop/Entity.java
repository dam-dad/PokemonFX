package dad.pokemonfx.gameloop;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

public abstract class Entity {
	
	protected double posX, posY;
	protected double width, height;
	
	public abstract void render(GraphicsContext gc);
	public abstract void update(long timeDifference);
	public abstract Shape getShape();

}
