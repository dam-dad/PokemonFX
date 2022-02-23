package dad.pokemonfx.gameloop;

import javafx.scene.image.Image;

public class Tree extends StaticEntity {
	
	/**
	 *   Clase que genera una entidad de árbol
	 */
	
	public Tree(double x, double y) {
		super(new Image("/images/treeTile.png"), x, y);
	}

}
