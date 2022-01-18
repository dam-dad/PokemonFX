package dad.pokemonfx.maps;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.texture;
import static dad.pokemonfx.maps.TileType.FLOOR;
import static dad.pokemonfx.maps.TileType.PLAYER;
import static dad.pokemonfx.maps.TileType.WALL;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;

import dad.pokemonfx.PokemonFXApp;
import dad.pokemonfx.components.Player;
import javafx.geometry.Point2D;

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
                .with(new CellMoveComponent(40, 40, 250))
                .with(new AStarMoveComponent(FXGL.<PokemonFXApp>getAppCast().getGrid()))
                .with(new Player())
                .build();
    }
    
}
