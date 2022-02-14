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
			player.setAction(new Action(Direction.UP));
		}
		if (input.contains(KeyCode.A)) {
			player.setAction(new Action(Direction.LEFT));
		}
		if (input.contains(KeyCode.S)) {
			player.setAction(new Action(Direction.DOWN));
		}
		if (input.contains(KeyCode.D)) {
			player.setAction(new Action(Direction.RIGHT));
		}
		if (input.isEmpty()) {
			player.setAction(null);
		}
	}

	// chequeamos colisions
	private void checkCollisions() {
		if(player.getShape().intersects(0, 0, 40, 40)) {
			player.setAction(null);
		}			
	}

	// pinta todo
	private void render() {
		objects.stream().forEach(o -> o.render(graphicsContext));
		player.render(graphicsContext);
	}	

}