package main;

import entity.Enemy;
import entity.Player;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class Game extends JPanel implements Runnable{

    final int tileSizeX = 15;
    final int tileSizeY = 22;
    final int scaled = 3;
    final int FPS = 60;

    public final int trueTileSizeX = tileSizeX * scaled;
    public final int trueTileSizeY = tileSizeY * scaled;
    public final int columns = 24;
    public final int rows = 12;
    public final int screenWidth = trueTileSizeX * columns;
    public final int screenHeight = trueTileSizeY * rows;

    public boolean gameOver = false;
    public boolean gameWon = false;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    CollisionChecker collisionChecker = new CollisionChecker(this);
    TileManager tM = new TileManager(this);
    Enemy enemy = new Enemy(this);

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

        double interval = 1000000000.0/FPS;
        double nextReDraw = System.nanoTime() + interval;



        while (gameThread != null && !gameOver) {

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

        player.update(collisionChecker);
        enemy.update(player);
        tM.animationHandler(player);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tM.draw(g2);
        player.draw(g2);
        enemy.draw(g2);

        endGame(g2);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("MV Boli", Font.PLAIN, 45));
        g2.drawString(String.valueOf(player.score), 50, 50);


        g2.dispose();
    }

    public void endGame(Graphics2D g2){
        if(player.solidArea.intersects(enemy.solidArea)){
            player.score -= 5000;
            g2.setColor(Color.RED);
            g2.setFont(new Font("MV Boli", Font.PLAIN, 75));
            g2.drawString("GAME OVER", 300, 400);
            gameOver = true;
        }
        else if(gameWon){
            gameOver = true;
            g2.setColor(Color.YELLOW);
            g2.setFont(new Font("MV Boli", Font.PLAIN, 75));
            g2.drawString("YOU WIN!", 300, 400);
        }
        else if(player.score < -100){
            g2.setColor(Color.RED);
            g2.setFont(new Font("MV Boli", Font.PLAIN, 75));
            g2.drawString("GAME OVER", 300, 400);
            gameOver = true;
        }
    }
}
