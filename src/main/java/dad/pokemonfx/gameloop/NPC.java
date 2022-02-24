package dad.pokemonfx.gameloop;

import javafx.scene.image.Image;

/**
 *   Genera un npc en el mundo
 */
public class NPC extends StaticEntity{
	
	public NPC(double x, double y) {
		super(new Image("/images/npcTile.png"), x, y);
	}
}
