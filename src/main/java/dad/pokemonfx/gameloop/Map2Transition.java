package dad.pokemonfx.gameloop;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Map2Transition extends StaticEntity{
	
	/**
	 *   Genera un objeto de transición que visualmente es igual a un tile de suelo
	 */
	
	public Map2Transition(double x, double y) {
		super(new Image("/images/grassTileTransition2.png"), x, y);
	}
	
	@Override
	public Shape getShape() {
		return new Rectangle(posX, posY+48, width, height);
	}
}
