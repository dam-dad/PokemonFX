package dad.pokemonfx.gameloop;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Tile {

	private static int[][] tileMap = {
			{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
			{2, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
			{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
			{2, 1, 1, 1, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2},
			{2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2},
			{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
			
	}; 

	private static final Image floorTile = new Image("/images/grassTile.png");
	private static final Image tallGrassTile = new Image("/images/tallGrassTile.png");
	private static final Image flowerTile = new Image("/images/flowerTile.png");
	private static final Image treeTile = new Image("/images/treeTile.png");

	private static double tileLength = 48;
	private static double tileWidth = 48;

	public static List<Entity> loadTile() {
		
		List<Entity> entities = new ArrayList<>();
		
		int mapLength = tileMap.length;
		int mapWidth = tileMap[0].length;

		for (int i = 0; i < mapLength; i++) { 
			for (int j = 0; j < mapWidth; j++) { 

				switch (tileMap[i][j]) {
				case 0: entities.add(new StaticEntity(floorTile, j * tileWidth, i * tileLength)); break;
				case 1: entities.add(new StaticEntity(tallGrassTile, j * tileWidth, i * tileLength)); break;
				case 2: entities.add(new Tree(j * tileWidth, i * tileLength)); break;
				case 3: entities.add(new StaticEntity(flowerTile, j * tileWidth, i * tileLength)); break;
				}
				
			}
		}
		
		return entities;
	}

}
