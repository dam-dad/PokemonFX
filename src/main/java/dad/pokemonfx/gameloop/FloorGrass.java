package dad.pokemonfx.gameloop;

import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

public class FloorGrass extends StaticEntity{
	
	/**
	 *  Clase que genera tile de hierba
	 */
	
	public FloorGrass(double x, double y) {
		super(new Image("/images/grassTile.png"), x, y);
	}
	
	@Override
	public Shape getShape() {
		return null;
	}
	
}
