package dad.pokemonfx.gamelop;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class TileEngine {

	int[][] tileMap = {
			{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
			{2, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
			{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
			{2, 1, 1, 1, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2},
			{2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2},
			{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
	}; 

	Image floorTile = new Image("/images/grassTile.png");
	Image tallGrassTile = new Image("/images/tallGrassTile.png");
	Image flowerTile = new Image("/images/flowerTile.png");
	Image treeTile = new Image("/images/treeTile.png");

	double tileLength = 45.5;
	double tileWidth = 42.5;

	public void generateTiles(GraphicsContext graphicsContext) {

		int mapLength = tileMap.length;
		int mapWidth = tileMap[0].length;

		for (int i = 0; i < mapLength; i++) { 
			for (int j = 0; j < mapWidth; j++) { 

				if (tileMap[i][j] == 0) {
					graphicsContext.drawImage(floorTile, j * tileWidth, i * tileLength);
				}
				if (tileMap[i][j] == 1) {
					graphicsContext.drawImage(tallGrassTile, j * tileWidth, i * tileLength);
				}
				if (tileMap[i][j] == 2) {
					graphicsContext.drawImage(treeTile, j * tileWidth, i * tileLength);
				}
				if (tileMap[i][j] == 3) {
					graphicsContext.drawImage(flowerTile, j * tileWidth, i * tileLength);
				}
				
			}
		}
	}

}
