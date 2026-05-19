package ui;

import game.GameConfig;
import game.Game;
import entity.Direction;
import entity.Player;

import java.awt.Graphics;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Font;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class Ui extends JPanel implements Runnable, KeyListener {
    private Thread gameThread;
    private boolean running = false;

    private final int FPS = GameConfig.FPS;
    private final int tileSize = GameConfig.TILE_SIZE;
    private final int width = GameConfig.MAP_COLS * tileSize;
    private final int height = GameConfig.MAP_ROWS * tileSize;

    private Game game;
    private BufferedImage background;

    public Ui() {
        this.setPreferredSize(new Dimension(width, height));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(this);

        try {
            background = ImageIO.read(getClass().getResource("/background_grass.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        game = Game.getInstance();
    }

    public void startGame() {
        running = true;
        game.start();
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (running) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                game.update();
                repaint();
                delta--;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, width,  height, null);
        game.render(g);

        Player p = game.getPlayer();
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Lives: " + p.getLives(), 10,25);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Player p = game.getPlayer();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                p.setDirection(Direction.UP);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                p.setDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                p.setDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                p.setDirection(Direction.RIGHT);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}