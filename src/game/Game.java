package game;

import entity.Entity;
import entity.Ghost;
import entity.Player;
import map.Food;
import map.Map;

import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;


public class Game {
    private static Game instance;
    private Player player;
    private List<Ghost> ghosts;
    private Map map;
    private boolean running;
    private GameState state;

    private Game() {
        this.state = GameState.PLAYING;
        init();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    private void init() {
        map = GameFactory.createMap();
        player = GameFactory.createPlayer(map);
        ghosts = new ArrayList<>();
        ghosts.add(GameFactory.createGhost(map));
        running = true;
    }

    public void start() {
        running = true;
    }

    public void update() {
        if (running == false || state != GameState.PLAYING) return;

        player.update();

        map.eatFood(player);

        for (Ghost g : ghosts) {
            g.update();
        }

        for (Ghost g : ghosts) {
            if (isColliding(g, player)) {
                player.loseLives();
                resetPosition();
                break;
            }
        }

        boolean allFoodEaten = true;
        for (Food f : map.getFoods()) {
            if (f.isEaten() == false) {
                allFoodEaten = false;
                break;
            }
        }
        if (allFoodEaten) {
            state = GameState.WIN;
            running = false;
        }

        if (player.getLives() == 0) {
            state = GameState.GAME_OVER;
            running = false;
        }
    }

    public void render(Graphics g) {
        if (state == GameState.GAME_OVER) {
            g.drawString("GAME OVER", 0, 0);
            return;
        }

        if (state == GameState.WIN) {
            g.drawString("YOU WON!", 0, 0);
            return;
        }

        map.draw(g);
        player.draw(g);
        for (Ghost gh : ghosts) {
            gh.draw(g);
        }
    }

    private boolean isColliding(Entity a, Entity b) {
        return a.getY() < b.getY() + b.getSize() &&
                a.getY() + a.getSize() > b.getY() &&
                a.getX() < b.getX() + b.getSize() &&
                a.getX() + a.getSize() > b.getX();
    }

    private void resetPosition() {
        int tileSize = map.getTileSize();

        player.setPosition(tileSize, tileSize);

        for (Ghost g : ghosts) {
            int[] pos = map.getRandomEmptyTile();
            int x = pos[0] * tileSize;
            int y = pos[1] * tileSize;
            g.setPosition(x, y);
        }
    }

    public void reset() {
        init();
    }

    public Player getPlayer() {
        return player;
    }
}