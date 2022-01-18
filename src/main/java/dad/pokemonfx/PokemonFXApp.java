package dad.pokemonfx;

import static com.almasb.fxgl.dsl.FXGL.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.getInput;
import static com.almasb.fxgl.dsl.FXGL.spawn;
import static dad.pokemonfx.maps.TileType.WALL;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.app.scene.SimpleGameMenu;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.pathfinding.CellState;
import com.almasb.fxgl.pathfinding.astar.AStarGrid;

import dad.pokemonfx.components.Player;
import dad.pokemonfx.maps.MapFactory;
import javafx.scene.input.KeyCode;

public class PokemonFXApp extends GameApplication {

	public static final int TILE_SIZE = 40;

    private AStarGrid grid;

    private Entity player;
    private Player playerComponent;

    public AStarGrid getGrid() {
        return grid;
    }
	
	@Override
	protected void initSettings(GameSettings settings) {
		settings.setTitle("PokemonFX");
        settings.setVersion("0.1");
        settings.setWidth(600);
        settings.setHeight(600);
        settings.setSceneFactory(new SceneFactory() {
            @Override
            public FXGLMenu newGameMenu() {
                return new SimpleGameMenu();
            }
        });
		
	}
	
	@Override
    protected void initInput() {
		
        getInput().addAction(new UserAction("Move Up") {
            @Override
            protected void onActionBegin() {
                playerComponent.moveUp();
            }
        }, KeyCode.UP);

        getInput().addAction(new UserAction("Move Left") {
            @Override
            protected void onActionBegin() {
                playerComponent.moveLeft();
            }
        }, KeyCode.LEFT);

        getInput().addAction(new UserAction("Move Down") {
            @Override
            protected void onActionBegin() {
                playerComponent.moveDown();
            }
        }, KeyCode.DOWN);

        getInput().addAction(new UserAction("Move Right") {
            @Override
            protected void onActionBegin() {
                playerComponent.moveRight();
            }
        }, KeyCode.RIGHT);

    }
	
	@Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new MapFactory());

        Level level = getAssetLoader().loadLevel("0.txt", new TextLevelLoader(40, 40, '0'));
        getGameWorld().setLevel(level);


        grid = AStarGrid.fromWorld(getGameWorld(), 15, 15, 40, 40, type -> {
            if (type.equals(WALL)) {
                return CellState.NOT_WALKABLE;
            }
            return CellState.WALKABLE;
        });

        player = spawn("Player");
        playerComponent = player.getComponent(Player.class);
    }
	
	public static void main(String[] args) {
        launch(args);
    }

}
