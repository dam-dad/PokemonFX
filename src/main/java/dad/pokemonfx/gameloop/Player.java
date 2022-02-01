package dad.pokemonfx.gameloop;


import static dad.pokemonfx.gameloop.Direction.DOWN;

import javafx.scene.image.Image;

public class Player {

	Image idleDown; 
	Image walkDown1;
	Image walkDown2;
	Image idleLeft;
	Image walkLeft1;
	Image walkLeft2;
	Image idleRight;
	Image walkRight1;
	Image walkRight2;
	Image idleUp;
	Image walkUp1;
	Image walkUp2;

	int xSpeed;
	int ySpeed;
	int posX, posY;
	int width, height;
	
	final double multiplierWidthWeight = 0.43;

	Direction direction;

	public Player(int posX, int posY, int speed) {

		// images of player
		idleDown = new Image("/images/idleDown.png");
		walkDown1 = new Image("/images/walkDown1.png");
		walkDown2 = new Image("/images/walkDown2.png");
		idleLeft = new Image("/images/idleLeft.png");
		walkLeft1 = new Image("/images/walkLeft1.png");
		walkLeft2 = new Image("/images/walkLeft2.png");
		idleRight = new Image("/images/idleRight.png");
		walkRight1 = new Image("/images/walkRight1.png");
		walkRight2 = new Image("/images/walkRight1.png");
		idleUp = new Image("/images/idleUp.png");
		walkUp1 = new Image("/images/walkUp1.png");
		walkUp2 = new Image("/images/walkUp2.png");

		// variables of movement speed
		xSpeed = speed;
		ySpeed = speed;

		// variables of starting position
		this.posX = posX;
		this.posY = posY;

		// default direction when starting
		direction = DOWN;

		// variables of character size
		width = (int) (100 * multiplierWidthWeight);
		height = (int) (150 * multiplierWidthWeight);
	}


	// methods for movement
	public void moveLeft() {
		posX = posX - xSpeed;
	}

	public void moveRight() {
		posX = posX + xSpeed;
	}

	public void moveUp() {
		posY = posY - ySpeed;
	}

	public void moveDown() {
		posY = posY + ySpeed;
	}

}