package map;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Wall {
    private int x;
    private int y;
    private int size;
    private BufferedImage image;

    public Wall( int x, int y, int size){
        this.x = x;
        this.y = y;
        this.size = size;

        try {
            image = ImageIO.read(getClass().getResource("/bush_tile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g){
        g.drawImage(image, x, y, size, size, null);
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