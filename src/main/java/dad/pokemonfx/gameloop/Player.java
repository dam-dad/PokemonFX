package dad.pokemonfx.gameloop;


import static dad.pokemonfx.gameloop.Direction.DOWN;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends Entity {
	
	private static final double SCALE = 0.43;
	
	private long timeAcc;

	private final List<Image> walkDown = Arrays.asList(
			new Image("/images/walkDown1.png"),
			new Image("/images/walkDown2.png")			
		);

	private final List<Image> walkUp = Arrays.asList(
			new Image("/images/walkUp1.png"),
			new Image("/images/walkUp2.png")			
		);

	private final List<Image> walkRight = Arrays.asList(
			new Image("/images/walkRight1.png"),
			new Image("/images/walkRight2.png")			
		);

	private final List<Image> walkLeft = Arrays.asList(
			new Image("/images/walkLeft1.png"),
			new Image("/images/walkLeft2.png")			
		);
	
	@SuppressWarnings("serial")
	private final Map<Direction, Image> idle = new HashMap<Direction, Image>() {{
		put(Direction.UP, new Image("/images/idleUp.png"));
		put(Direction.DOWN, new Image("/images/idleDown.png"));
		put(Direction.LEFT, new Image("/images/idleLeft.png"));
		put(Direction.RIGHT, new Image("/images/idleRight.png"));
	}};

	int counter = 0;
	int xSpeed;
	int ySpeed;
	boolean isWalking = false;
	private Direction direction;

	public Player(int posX, int posY, int speed) {

		// variables of movement speed
		this.xSpeed = speed;
		this.ySpeed = speed;

		// variables of starting position
		this.posX = posX;
		this.posY = posY;

		// default direction when starting
		this.direction = DOWN;

		// variables of character size
		this.width = (int) (100 * SCALE);
		this.height = (int) (150 * SCALE);
	}

	// methods for movement
	public void moveLeft() {
		isWalking = true;
		direction = Direction.LEFT;
		posX -= xSpeed;
	}

	public void moveRight() {
		isWalking = true;
		direction = Direction.RIGHT;
		posX += xSpeed;
	}

	public void moveUp() {
		isWalking = true;
		direction = Direction.UP;
		posY -= ySpeed;
	}

	public void moveDown() {
		isWalking = true;
		direction = Direction.DOWN;
		posY += ySpeed;
	}
	
	public void idle() {
		isWalking = false;
	}
	
	public void render(GraphicsContext gc) {
		Image currentImage = null;
		
		if (!isWalking) {
			currentImage = idle.get(direction);
		} else {
			switch (direction) {
				case DOWN:currentImage = walkDown.get(counter); break;
				case UP: currentImage = walkUp.get(counter); break;
				case RIGHT: currentImage = walkRight.get(counter); break;
				case LEFT: currentImage = walkLeft.get(counter); break;
			}
		}
		gc.drawImage(currentImage, posX, posY, width, height);
	}
	
	public void update(long timeDifference) {
		if (timeAcc > 200000) {
			counter++;
			if (counter > 1) counter = 0;
			timeAcc = 0;
		}
		timeAcc += timeDifference;
	}
	

}