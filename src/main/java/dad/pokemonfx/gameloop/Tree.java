package dad.pokemonfx.gameloop;

import javafx.scene.image.Image;

/**
 *   Clase que genera una entidad de arbol
 */
public class Tree extends StaticEntity {
	
	public Tree(double x, double y) {
		super(new Image("/images/treeTile.png"), x, y);
	}

}
