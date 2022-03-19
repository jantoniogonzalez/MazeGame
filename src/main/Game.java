package main;

import entity.Enemy;
import entity.Player;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class Game extends JPanel implements Runnable{

    final int tileSizeX = 16;
    final int tileSizeY = 22;
    final int scaled = 3;
    final int FPS = 60;

    public final int trueTileSizeX = tileSizeX * scaled;
    public final int trueTileSizeY = tileSizeY * scaled;
    public final int columns = 18;
    public final int rows = 14;
    public final int screenWidth = trueTileSizeY * columns;
    public final int screenHeight = trueTileSizeX * rows;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    TileManager tM = new TileManager(this, 1);
    Enemy enemy = new Enemy(this);

    int positionX = 100;
    int positionY = 100;
    int step = 5;

    public Game(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLUE);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double interval = 1000000000/FPS;
        double nextReDraw = System.nanoTime() + interval;

        while (gameThread != null) {

            update();

            repaint();

            try {

                double countdown = nextReDraw - System.nanoTime();
                countdown = countdown/1000000;

                if(countdown < 0){
                    countdown = 0;
                }

                Thread.sleep((long) countdown);
                nextReDraw += interval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void update(){

        player.update();
        enemy.update(player);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tM.draw(g2);
        player.draw(g2);
        enemy.draw(g2);

        g2.dispose();
    }

}
