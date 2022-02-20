package dad.pokemonfx.gameloop;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class Entity {
	
	public static final double SCALE = 0.43;
	
	protected double posX, posY;
	protected double width, height;
	protected Image image;
	public Rectangle shape;
	
	public abstract void render(GraphicsContext gc);
	public abstract void update(long timeDifference);
	public abstract Shape getShape();
	
	public boolean checkCollision(Entity entity) {
		return (getShape() != null && entity.getShape() != null && getShape().intersects(entity.getShape().getLayoutBounds()));
	}
	
	public Image getImage() {
		return image;
	}

}
