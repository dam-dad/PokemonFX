package dad.pokemonfx.gameloop;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


/**
 *   Genera un objeto de transicion que visualmente es igual a un tile de suelo
 */
public class Map1Transition extends StaticEntity{
	
	public Map1Transition(double x, double y) {
		super(new Image("/images/grassTileTransition1.png"), x, y);
	}
	
	@Override
	public Shape getShape() {
		return new Rectangle(posX, posY-48, width, height);
	}
}
