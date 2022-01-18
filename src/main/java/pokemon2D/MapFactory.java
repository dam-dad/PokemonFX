package pokemon2D;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.texture;
import static pokemon2D.TileType.WALL;
import static pokemon2D.TileType.PLAYER;
import static pokemon2D.TileType.FLOOR;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;

import javafx.geometry.Point2D;
import pokemon2D.components.PlayerComponent;

public class MapFactory implements EntityFactory {

    @Spawns("w")
    public Entity newWall(SpawnData data) {
        return entityBuilder(data)
                .type(WALL)
                .viewWithBBox(texture("treeTile.png", 40, 40))
                .build();
    }
    
    @Spawns("x")
    public Entity newFloor(SpawnData data) {
        return entityBuilder(data)
                .type(FLOOR)
                .viewWithBBox(texture("grassTile.png", 40, 40))
                .build();
    }

    @Spawns("Player")
    public Entity newPlayer(SpawnData data) {
        return entityBuilder(data)     		
                .atAnchored(new Point2D(20, 25), new Point2D(60, 60))
                .type(PLAYER)
                .viewWithBBox(texture("idleDown.png", 40, 40))
                .with(new CollidableComponent(true))
                .with(new CellMoveComponent(40, 40, 150))
                .with(new AStarMoveComponent(FXGL.<Pokemon2DApp>getAppCast().getGrid()))
                .with(new PlayerComponent())
                .build();
    }
}
