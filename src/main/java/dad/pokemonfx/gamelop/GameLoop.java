package dad.pokemonfx.gamelop;

import static java.util.stream.Stream.of;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class GameLoop extends AnimationTimer {

	private long time1 = System.nanoTime();
	private long time2; 
	private long timeDifference;
	private long interval = 200000000;
	private boolean isBattle = false;
	private GraphicsContext graphicsContext;
	private Player player;
	private ArrayList<String> input;
	//private Battle battle = new Battle();


	// Constructor
	public GameLoop(ArrayList<String> input, GraphicsContext graphicsContext, Player player) {
		this.input = input;
		this.graphicsContext = graphicsContext;
		this.player = player;
	}

	
	// method for game loop
	public void handle(long currentNanoTime) { 

		if (!isBattle) { // If no battle, generate map and check input

			TileEngine tileEngine = new TileEngine();
			tileEngine.generateTiles(graphicsContext);

			of(Direction.directionArray).filter(v -> input.contains(v.name())).findFirst().ifPresent(dir -> {
				time2 = System.nanoTime();
				timeDifference = time2 - time1; 
				time2 = System.nanoTime();
				timeDifference = time2 - time1;

				if (timeDifference < interval) graphicsContext.drawImage(dir.image1.apply(player), player.posX, player.posY, player.width, player.height);
				if (timeDifference > interval && timeDifference < interval * 2) graphicsContext.drawImage(dir.image.apply(player), player.posX, player.posY, player.width, player.height);
				if (timeDifference > interval * 2 && timeDifference < interval * 3) graphicsContext.drawImage(dir.image2.apply(player), player.posX, player.posY, player.width, player.height);
				if (timeDifference > interval * 3) graphicsContext.drawImage(dir.image.apply(player), player.posX, player.posY, player.width, player.height);
				if (timeDifference > interval * 4) time1 = System.nanoTime();
				dir.move.accept(player);
				player.direction = dir;

				// checks for random number 
				/*int randomNumberBattle = ThreadLocalRandom.current().nextInt(2000) + 1;
				if (randomNumberBattle < 10) {
					isBattle = true;
				}	
				*/			
			});

			if (input.size() == 0) graphicsContext.drawImage(player.direction.image.apply(player), player.posX, player.posY, player.width, player.height);
		}
		/*// enter battle screen
		if (isBattle) {

			if (!battle.isBattleStart) { 
				battle.startTimer();
				battle.setIsBattleStart(true);
			}

			//System.out.println(isBattle);
			battle.updateBattle(graphicsContext);
			isBattle = battle.checkBattleOver();
		}
		*/
		
		
	}

}