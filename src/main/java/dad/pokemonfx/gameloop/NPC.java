package dad.pokemonfx.gameloop;

import javafx.scene.image.Image;


public class NPC extends StaticEntity{
	
	/**
	 *   Genera un npc en el mundo
	 */

	public NPC(double x, double y) {
		super(new Image("/images/npcTile.png"), x, y);
	}
}
