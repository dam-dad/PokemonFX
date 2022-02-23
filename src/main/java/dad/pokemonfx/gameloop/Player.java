package dad.pokemonfx.gameloop;


import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Player extends Entity {
	
	private final Animation walkDown = new Animation("/images/walkDown1.png", "/images/walkDown2.png");
	private final Animation walkUp = new Animation("/images/walkUp1.png", "/images/walkUp2.png");
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

	public double xSpeed;
	public double ySpeed;
	private boolean isWalking = false;
	private Direction direction;
	private Animation animation;
	private Action action;

	public Player(double posX, double posY, double speed) {
		super();
		
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

	public void move(Action action) {
		this.action = action;
	}
	
	// methods for movement
	private void moveLeft() {
		isWalking = true;
		direction = Direction.LEFT;
		posX -= xSpeed;
	}

	private void moveRight() {
		isWalking = true;
		direction = Direction.RIGHT;
		posX += xSpeed;
	}

	private void moveUp() {
		isWalking = true;
		direction = Direction.UP;
		posY -= ySpeed;
	}

	private void moveDown() {
		isWalking = true;
		direction = Direction.DOWN;
		posY += ySpeed;
	}
	
	private void idle() {
		isWalking = false;
	}
	
	public void render(GraphicsContext gc) {
		
		//cuadrado amarillo para testeo de area colisiones
		/*
		Rectangle shape = (Rectangle) getShape();
		gc.setStroke(Color.YELLOW);
		gc.setFill(Color.YELLOW);
		gc.fillRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
		*/
		
		//pintar jugador
		gc.drawImage(animation.getFrame(), posX, posY, width, height);		
	}
	
	public void update(long timeDifference) {
		if (action != null) {
			switch (action.getDirection()) {
			case DOWN: moveDown(); break;
			case UP: moveUp(); break;
			case LEFT: moveLeft(); break;
			case RIGHT: moveRight(); break;
			}
			action = null;
		} else {
			idle();
		}
		
		if (!isWalking) {
			animation = idle.get(direction);
		} else {
			animation = walk.get(direction);
		}
		animation.update(timeDifference);
	}

	@Override
	public Shape getShape() {
		double shapeX = posX;
		double shapeY = posY + height/2;
		if (action != null) {
			switch (action.getDirection()) {
			case DOWN: shapeY += ySpeed; break;
			case UP: shapeY -= ySpeed; break;
			case LEFT: shapeX -= xSpeed; break;
			case RIGHT: shapeX += xSpeed; break;
			}
		} 
		
		return new Rectangle(shapeX, shapeY, width, height/2);
	}

	public void setAction(Action action) {
		this.action = action;
		
	}

}