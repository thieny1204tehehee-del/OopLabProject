package entity;

import java.awt.Graphics;
public abstract class Entity {
    protected int x;
    protected int y;
    protected int speed;
    protected int size;

    public Entity( int x, int y, int speed, int size ){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.size = size;
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

    public int getSize() {
        return size;
    }

    public void setSize( int size){
        this.size = size;
    }
}
