package entity;

import strategy.MovementStrategy;
import map.Map;

import java.util.Random;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Ghost extends Entity {
    private Direction direction;
    private MovementStrategy strategy;
    private Random random;
    private Map map;
    private BufferedImage leftImg, rightImg;

    public Ghost(int x, int y,int speed, int size, MovementStrategy strategy, Map map) {
        super(x, y, speed, size);
        this.map = map;
        this.strategy = strategy;
        this.random = new Random();
        this.direction = Direction.values()[random.nextInt(Direction.values().length)];

        try {
            leftImg = ImageIO.read(getClass().getResource("/ghost_left.png"));
            rightImg = ImageIO.read(getClass().getResource("/ghost_right.png"));
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
    

    @Override
    public void update() {
        move();
    }

    @Override
    public void draw(Graphics g) {
        if (direction == Direction.LEFT) {
            g.drawImage(leftImg, x, y, size, size, null);
        } else {
            g.drawImage(rightImg, x, y, size, size, null);
        }
    }

    private void move() {
        strategy.move(this);
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

    public Map getMap() {
        return map;
    }
}