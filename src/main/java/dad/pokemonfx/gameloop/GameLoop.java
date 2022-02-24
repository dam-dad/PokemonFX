package dad.pokemonfx.gameloop;

import java.util.List;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

/**
 *  Clase que crea un objeto gameloop que realiza acciones cada frame, entre ellas recibir el input, comprobar colisiones 
 *  y pintar el mapa del juego y las entidades
 */
public class GameLoop extends AnimationTimer {

	private long time;
	private long timeDifference;

	private GraphicsContext graphicsContext;

	private Player player;
	private List<Entity> objects;

	private Set<KeyCode> input;
	private boolean nextMap1 = false;
	private boolean nextMap2 = false;
	BooleanProperty hayBatalla = new SimpleBooleanProperty();

	public GameLoop(Set<KeyCode> input, GraphicsContext graphicsContext, Player player) {
		this.input = input;
		this.graphicsContext = graphicsContext;
		this.player = player;
		this.objects = Tile.loadTile(Tile.tileMap1);
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
		if (nextMap1 == true) {
			this.objects = Tile.loadTile(Tile.tileMap2);
			nextMap1 = false;
			player.posY = (Tile.tileMap2.length) * Tile.getTileLength()
					- (Tile.getTileLength() / 2 + Tile.getTileLength());
		} else if (nextMap2 == true) {
			this.objects = Tile.loadTile(Tile.tileMap1);
			nextMap2 = false;
			player.posY = 0;
		}
	}

	// procesamos las entradas
	private void processInput() {

		if (input.contains(KeyCode.W) || input.contains(KeyCode.UP)) {
			player.setAction(new Action(Direction.UP));
		}
		if (input.contains(KeyCode.A) || input.contains(KeyCode.LEFT)) {
			player.setAction(new Action(Direction.LEFT));
		}
		if (input.contains(KeyCode.S) || input.contains(KeyCode.DOWN)) {
			player.setAction(new Action(Direction.DOWN));
		}
		if (input.contains(KeyCode.D) || input.contains(KeyCode.RIGHT)) {
			player.setAction(new Action(Direction.RIGHT));
		}
		if (input.isEmpty()) {
			player.setAction(null);
		}
	}

	// chequeamos colisions
	private void checkCollisions() {

		for (Entity entity : objects) {

			if (player.checkCollision(entity)) {
				if (entity.getImage().getUrl().contains("Transition1")) {
					nextMap1 = true;
				} else if (entity.getImage().getUrl().contains("Transition2")) {
					nextMap2 = true;
				} else if (entity.getImage().getUrl().contains("npc") && input.contains(KeyCode.Z)) {
					hayBatalla.set(true);
				}
				player.setAction(null);
			}

		}

	}

	// pinta todo
	private void render() {
		objects.stream().forEach(o -> o.render(graphicsContext));
		player.render(graphicsContext);
	}

	public BooleanProperty hayBatallaProperty() {
		return this.hayBatalla;
	}
	

	public boolean isHayBatalla() {
		return this.hayBatallaProperty().get();
	}
	

	public void setHayBatalla(final boolean hayBatalla) {
		this.hayBatallaProperty().set(hayBatalla);
	}
	

}