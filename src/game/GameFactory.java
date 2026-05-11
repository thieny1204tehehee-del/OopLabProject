package game;

import entity.Player;
import entity.Ghost;
import map.Map;
import strategy.RandomMovement;

public class GameFactory {

    public static Map createMap() {
        int[][] grid = {
            {1,1,1,1,1,1,1,1,1,1},
            {1,0,0,1,0,0,0,0,0,1},
            {1,0,1,1,0,1,1,1,0,1},
            {1,0,0,0,0,0,0,1,0,1},
            {1,0,1,1,1,1,0,1,0,1},
            {1,0,0,0,0,1,0,0,0,1},
            {1,1,1,1,0,1,1,1,0,1},
            {1,0,0,1,0,0,0,1,0,1},
            {1,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1}
        };
        return new Map(grid);
    }

     public static Player createPlayer(Map map) {
        return new Player(
            GameConfig.TILE_SIZE,
            GameConfig.TILE_SIZE,
            GameConfig.PLAYER_SPEED,
            GameConfig.TILE_SIZE,
            map
        );
    }

    public static Ghost createGhost(Map map) {
        int[] pos = map.getRandomEmptyTile();
        int x = pos[0] * map.getTileSize();
        int y = pos[1] * map.getTileSize();
        return new Ghost(x, y, GameConfig.GHOST_SPEED, GameConfig.GHOST_SIZE, new RandomMovement(), map);
    }
}