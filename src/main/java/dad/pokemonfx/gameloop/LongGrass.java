package dad.pokemonfx.gameloop;

import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

/**
 *  Clase que genera tile de hierbaAlta
 */
public class LongGrass extends StaticEntity{
	
	public LongGrass(double x, double y) {
		super(new Image("/images/tallGrassTile.png"), x, y);
	}
	
	@Override
	public Shape getShape() {
		return null;
	}

}
