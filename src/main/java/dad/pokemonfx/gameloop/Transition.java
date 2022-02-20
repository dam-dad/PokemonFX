package dad.pokemonfx.gameloop;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Transition extends StaticEntity{
		

	public Transition(double x, double y) {
		super(new Image("/images/grassTile.png"), x, y);
	}
	
	@Override
	public Shape getShape() {
		return new Rectangle(posX, posY-48, width, height);
	}
	
	
}