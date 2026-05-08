package entity;

import strategy.MovementStrategy;
import game.GameConfig;
import map.Map;

import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;

public class Ghost extends Entity {
    private Direction direction;
    private int size;
    private MovementStrategy strategy;
    private Random random;
    private Map map;

    public Ghost(int x, int y, int size, MovementStrategy strategy, Map map) {
        super(x, y,GameConfig.GHOST_SPEED);
        this.size = size;
        this.map = map;
        this.strategy = strategy;
        this.random = new Random();
        this.direction = Direction.values()[random.nextInt(Direction.values().length)];
    }

    public void update() {
        move();
    }

    public void move() {
        strategy.move(this);
    }

    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillOval(x, y, size, size);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setStrategy(MovementStrategy strategy) {
        this.strategy = strategy;
    }

    public MovementStrategy getStrategy() {
        return strategy;
    }

    public int getSize() {
        return size;
    }

    public Map getMap() {
        return map;
    }
}