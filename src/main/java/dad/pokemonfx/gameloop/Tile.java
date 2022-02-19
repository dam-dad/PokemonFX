package dad.pokemonfx.gameloop;

import java.util.ArrayList;
import java.util.List;

public class Tile {

	private static int[][] tileMap = { 
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
			{ 2, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2 }, 
			{ 2, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2 }, 
			{ 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 1, 1, 1, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 2 }, 
			{ 2, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 1, 1, 1, 2 },
			{ 2, 0, 0, 3, 0, 0, 0, 0, 2, 0, 0, 1, 1, 1, 2 }, 
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2 },
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
	};

	private static double tileLength = 48;
	private static double tileWidth = 48;

	public static List<Entity> loadTile() {

		List<Entity> entities = new ArrayList<>();

		int mapLength = tileMap.length;
		int mapWidth = tileMap[0].length;

		for (int i = 0; i < mapLength; i++) {
			for (int j = 0; j < mapWidth; j++) {

				switch (tileMap[i][j]) {
				case 0:
					entities.add(new FloorGrass(j * tileWidth, i * tileLength));
					break;
				case 1:
					entities.add(new LongGrass(j * tileWidth, i * tileLength));
					break;
				case 2:
					entities.add(new Tree(j * tileWidth, i * tileLength));
					break;
				case 3:
					entities.add(new Flower(j * tileWidth, i * tileLength));
					break;
				}

			}
		}

		return entities;
	}

}
