package dad.pokemonfx.gameloop;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
	
	protected int posX, posY;
	protected int width, height;
	
	public abstract void render(GraphicsContext gc);
	public abstract void update(long timeDifference);

}
