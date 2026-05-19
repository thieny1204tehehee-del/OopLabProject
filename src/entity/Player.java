package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

import game.GameConfig;
import map.Map;


public class Player extends Entity {
    private Direction direction;
    private int lives;
    private Map map;
    private BufferedImage upImg, downImg, leftImg, rightImg;

    public Player(int x, int y, int speed, int size, Map map){
        super(x, y, speed, size);
        this.map = map;
        this.direction = Direction.UP;
        this.lives = GameConfig.DEFAULT_LIVES;

        try {
            upImg = ImageIO.read(getClass().getResource("/duck_up.png"));
            downImg = ImageIO.read(getClass().getResource("/duck_down.png"));
            leftImg = ImageIO.read(getClass().getResource("/duck_left.png"));
            rightImg = ImageIO.read(getClass().getResource("/duck_right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        BufferedImage currentImage = null;

        switch (direction) {
            case UP:
                currentImage = upImg;
                break;
            case DOWN:
                currentImage = downImg;
                break;
            case LEFT:
                currentImage = leftImg;
                break;
            case RIGHT:
                currentImage = rightImg;
                break;
        }

        g.drawImage(currentImage, x, y, size, size, null);
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