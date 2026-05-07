package entity;

import java.awt.Graphics;
public abstract class Entity {
    protected int x;
    protected int y;
    protected int speed;

    public Entity( int x, int y, int speed ){
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public abstract void update();

    public abstract void draw(Graphics g);

    public int getX() {
         return x;
    }
    
    public int getY() {
        return y;
    }

    public void setPosition( int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getSpeed(){
        return speed;
    }

    public void setSpeed( int speed){
        this.speed = speed;
    }

}
