package map;

import java.awt.Color;
import java.awt.Graphics;

public class Wall {
    private int x;
    private int y;
    private int size;

    public Wall( int x, int y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void draw(Graphics g){
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, size, size);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getSize(){
        return size;
    }
   
}