package dad.pokemonfx.gameloop;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class StaticEntity extends Entity {
	
	private Image image;
	
	public StaticEntity(Image image, double x, double y) {
		super();
		this.image = image;
		this.posX = x;
		this.posY = y;
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(image, posX, posY);
	}

	@Override
	public void update(long timeDifference) {
		// do nothing
	}

	@Override
	public Shape getShape() {
		return new Rectangle(posX, posY, width, height);
	}

}
