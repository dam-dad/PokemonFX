package dad.pokemonfx.gameloop;

import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

public class GameLoop extends AnimationTimer {

	private long time;
	private long timeDifference;

	private GraphicsContext graphicsContext;
	
	private TileEngine tileEngine = new TileEngine();
	private Player player;
	
	private Set<KeyCode> input;

	public GameLoop(Set<KeyCode> input, GraphicsContext graphicsContext, Player player) {
		this.tileEngine = new TileEngine();
		this.input = input;
		this.graphicsContext = graphicsContext;
		this.player = player;
	}
	
	@Override
	public void start() {
		this.time = System.nanoTime();
		super.start();
	}

	// method for game loop
	public void handle(long currentNanoTime) {

		timeDifference = (currentNanoTime - time) / 1000;
		
		processInput();
		
		checkCollisions();
		
		update();
		
		render();

		time = currentNanoTime;

	}

	private void update() {
		player.update(timeDifference);
	}

	// procesamos las entradas
	private void processInput() {
		if (input.contains(KeyCode.W)) {
			player.moveUp();
		}
		if (input.contains(KeyCode.A)) {
			player.moveLeft();
		}
		if (input.contains(KeyCode.S)) {
			player.moveDown();
		}
		if (input.contains(KeyCode.D)) {
			player.moveRight();
		}
		if (input.isEmpty()) {
			player.idle();
		}
	}

	// chequeamos colisions
	private void checkCollisions() {
		// TODO Auto-generated method stub
		
	}

	// pinta todo
	private void render() {
		tileEngine.render(graphicsContext);
		player.render(graphicsContext);
	}	

}