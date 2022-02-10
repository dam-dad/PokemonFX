package dad.pokemonfx.gameloop;


import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Player extends Entity {
	
	private static final double SCALE = 0.43;
	
	private final Animation walkDown = new Animation(100000, "/images/walkDown1.png", "/images/walkDown2.png");
	private final Animation walkUp = new Animation(100000, "/images/walkUp1.png", "/images/walkUp2.png");
	private final Animation walkRight = new Animation("/images/walkRight1.png", "/images/walkRight2.png");
	private final Animation walkLeft = new Animation("/images/walkLeft1.png", "/images/walkLeft2.png");

	@SuppressWarnings("serial")
	private final Map<Direction, Animation> idle = new HashMap<>() {{
		put(Direction.UP, new Animation("/images/idleUp.png"));
		put(Direction.DOWN, new Animation("/images/idleDown.png"));
		put(Direction.LEFT, new Animation("/images/idleLeft.png"));
		put(Direction.RIGHT, new Animation("/images/idleRight.png"));
	}};
	
	@SuppressWarnings("serial")
	private final Map<Direction, Animation> walk = new HashMap<>() {{
		put(Direction.UP, walkUp);
		put(Direction.DOWN, walkDown);
		put(Direction.LEFT, walkLeft);
		put(Direction.RIGHT, walkRight);
	}};

	private double xSpeed;
	private double ySpeed;
	private boolean isWalking = false;
	private Direction direction;
	private Animation animation;

	public Player(double posX, double posY, double speed) {
		
		// variables of movement speed
		this.xSpeed = speed;
		this.ySpeed = speed;

		// variables of starting position
		this.posX = posX;
		this.posY = posY;

		// default direction when starting
		this.direction = Direction.DOWN;

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
		gc.drawImage(animation.getFrame(), posX, posY, width, height);

		Rectangle shape = (Rectangle) getShape();
		gc.setStroke(Color.YELLOW);
		gc.setFill(Color.YELLOW);
		gc.fillRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
	}
	
	public void update(long timeDifference) {
		if (!isWalking) {
			animation = idle.get(direction);
		} else {
			animation = walk.get(direction);
		}
		animation.update(timeDifference);
	}

	@Override
	public Shape getShape() {
		return new Rectangle(posX, posY + height/2, width, height/2);
	}

}