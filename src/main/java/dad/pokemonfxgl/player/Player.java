package dad.pokemonfxgl.player;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;

public class Player extends Component {
	private AStarMoveComponent astar;

	public void moveRight() {
		astar.moveToRightCell();
	}

	public void moveLeft() {
		astar.moveToLeftCell();
	}

	public void moveUp() {
		astar.moveToUpCell();
	}

	public void moveDown() {
		astar.moveToDownCell();
	}

}
