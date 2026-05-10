package strategy;

import entity.Ghost;
import game.GameConfig;
import map.Map;
import entity.Direction;
import java.util.Random;

public class RandomMovement implements MovementStrategy {
    private Random random = new Random();
    private Direction[] dir = Direction.values();

    @Override
    public void move(Ghost ghost) {
            int speed = ghost.getSpeed();
            Map map = ghost.getMap();
            int tileSize = map.getTileSize();
            int size = ghost.getSize();

            
            int nextX = ghost.getX();
            int nextY = ghost.getY();

            switch (ghost.getDirection()) {
                case UP:
                    nextY -= speed;
                    break;
                case DOWN:
                    nextY += speed;
                    break;
                case LEFT:
                    nextX -= speed;
                    break;
                case RIGHT:
                    nextX += speed;
                    break;
            }

            boolean hitWall = map.isWall(nextX/tileSize, nextY/tileSize) 
                            || map.isWall((nextX + size - 1)/tileSize, nextY/tileSize) 
                            || map.isWall(nextX/tileSize, (nextY + tileSize - 1)/tileSize) 
                            || map.isWall((nextX + tileSize - 1)/tileSize, (nextY + tileSize - 1)/tileSize);

            if (hitWall == false) {
                ghost.setPosition(nextX, nextY);
            } else {
                ghost.setDirection(dir[random.nextInt(dir.length)]);
            }

            if (random.nextInt(100) < GameConfig.GHOST_TURN_CHANCE) {
                ghost.setDirection(dir[random.nextInt(dir.length)]);
            }
    }
}