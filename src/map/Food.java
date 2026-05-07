package map;

import java.awt.Color;
import java.awt.Graphics;
import game.GameConfig;

public class Food {
    private int x;
    private int y;
    private int size;
    private boolean isEaten;

    public Food( int x, int y, int size ){
        this.x = x;
        this.y = y;
        this.size = size;
        this.isEaten = false;
    }

    public void draw( Graphics g ){
        if (isEaten == false){
            g.setColor(Color.ORANGE);
            int foodSize = GameConfig.FOOD_SIZE;
            int offSet = size/2 - foodSize/2;
            g.fillOval(x + offSet, y + offSet, foodSize, foodSize);
        }
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }

    public int getSize(){
        return size;
    }

    public boolean isEaten(){
        return isEaten;
    }

    public void setEaten( boolean eaten){
       this.isEaten = eaten;
    }

}