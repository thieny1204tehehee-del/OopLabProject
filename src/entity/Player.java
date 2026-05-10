package entity;

import java.awt.Color;
import java.awt.Graphics;

import game.GameConfig;
import map.Map;


public class Player extends Entity {
    private Direction direction;
    private int lives;
    private Map map;

    public Player(int x, int y, int speed, int size, Map map){
        super(x, y, speed, size);
        this.map = map;
        this.direction = Direction.UP;
        this.lives = GameConfig.DEFAULT_LIVES;

    }

    @Override
    public void update(){
        move();
    }

     private void move() {
        int nextX = x, nextY = y;// tao vi tri tam thoi
        switch (direction) {
            case UP:    nextY -= speed; break; //doi vi tri theo huong di
            case DOWN:  nextY += speed; break;
            case LEFT:  nextX -= speed; break;
            case RIGHT: nextX += speed; break;
        }

        // Player checks its own wall collision, just like Ghost does
        int ts = map.getTileSize();
        boolean hitWall =
            map.isWall(nextX / ts,            nextY / ts) ||
            map.isWall((nextX + size - 1) / ts, nextY / ts) ||
            map.isWall(nextX / ts,            (nextY + size - 1) / ts) ||
            map.isWall((nextX + size - 1) / ts, (nextY + size - 1) / ts);

        // if hitWall, simply don't move — no babysitter needed 
        if (!hitWall) {
            x = nextX;
            y = nextY;
        }
       
    }
        @Override
        public void draw(Graphics g){
            g.setColor(Color.YELLOW);
            g.fillOval( x, y, size, size );
        }

        public void setDirection(Direction direction){
            this.direction = direction;
        }

        public Direction getDirection(){
            return direction;
        }

        public int getLives(){
            return lives;
        }

        public void loseLives(){
            lives--;
        }

        public void resetLives(){
            lives = GameConfig.DEFAULT_LIVES;
        }
}