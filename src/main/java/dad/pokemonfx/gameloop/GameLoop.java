package dad.pokemonfx.gameloop;

import java.util.List;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Shape;

public class GameLoop extends AnimationTimer {

	private long time;
	private long timeDifference;

	private GraphicsContext graphicsContext;
	
	private Player player;
	private List<Entity> objects;
	
	private Set<KeyCode> input;

	public GameLoop(Set<KeyCode> input, GraphicsContext graphicsContext, Player player) {
		this.input = input;
		this.graphicsContext = graphicsContext;
		this.player = player;
		this.objects = Tile.loadTile();
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

		
	}

	// pinta todo
	private void render() {
		objects.stream().forEach(o -> o.render(graphicsContext));
		player.render(graphicsContext);
	}	

}