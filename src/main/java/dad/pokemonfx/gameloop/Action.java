package dad.pokemonfx.gameloop;

public class Action {
	/**
	 * Clase que permite realizar el movimiento de una dirección, necesaria para comprobar las colisiones y permitir el action según 
	 * colision o no
	 */
	
	private Direction direction;
	
	public Action(Direction direction){
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}
}
