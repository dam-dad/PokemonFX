package dad.pokemonfx.gameloop;

import java.util.function.Consumer;
import java.util.function.Function;

import dad.pokemonfx.gameloop.Direction;
import javafx.scene.image.Image;

public enum Direction {
	
	UP, DOWN, LEFT, RIGHT

//	UP(e -> e.idleUp, e -> e.walkUp1, e -> e.walkUp2, Player::moveUp),
//	DOWN(e -> e.idleDown, e -> e.walkDown1, e -> e.walkDown2, Player::moveDown),
//	LEFT(e -> e.idleLeft, e -> e.walkLeft1, e -> e.walkLeft2, Player::moveLeft),
//	RIGHT(e -> e.idleRight, e -> e.walkRight1, e -> e.walkRight2, Player::moveRight);
//
//	final Function<Player, Image> image;
//	final Function<Player, Image> image1;
//	final Function<Player, Image> image2;
//	final Consumer<Player> move;
//
//	Direction(Function<Player, Image> image, Function<Player, Image> image1,
//	          Function<Player, Image> image2, Consumer<Player> move) {
//		this.image = image;
//		this.image1 = image1;
//		this.image2 = image2;
//		this.move = move;
//	}
//
//	static final Direction[] directionArray = values();

}