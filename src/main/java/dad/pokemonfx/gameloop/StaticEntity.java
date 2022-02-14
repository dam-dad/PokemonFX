package dad.pokemonfx.gameloop;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class StaticEntity extends Entity {
	
	protected Image image;
	public Rectangle shape;
	
	public StaticEntity(Image image, double x, double y) {
		super();
		this.image = image;
		this.posX = x;
		this.posY = y;
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(image, posX, posY);
		
		/*if(image.getUrl().contains("tree")) {
			shape = new Rectangle(posX, posY, 48, 48);
			gc.setStroke(Color.YELLOW);
			gc.setFill(Color.YELLOW);
			gc.fillRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
		}	
		*/	
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
