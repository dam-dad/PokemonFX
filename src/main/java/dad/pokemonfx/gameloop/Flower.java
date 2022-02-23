package dad.pokemonfx.gameloop;

import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

public class Flower extends StaticEntity {
	
	/**
	 *  Clase que genera tile de flor
	 */
	
	public Flower(double x, double y) {
		super(new Image("/images/flowerTile.png"), x, y);
	}

	@Override
	public Shape getShape() {
		return null;
	}

}
