package entity;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.geom.Point2D;

public class Enemy extends Entity{

    public Enemy(Game gp){
        this.gp = gp;

        setDefaultValues(400, 400, 2);
        getEnemyImage();

        solidArea = new Rectangle(x + 5, y + 5, 35, 56);
    }

    public void getEnemyImage(){
        try {
            up1 = ImageIO.read(new File("resources/enemy/Enemy_back_1.png"));
            up2 = ImageIO.read(new File("resources/enemy/Enemy_back_2.png"));
            down1 = ImageIO.read(new File("resources/enemy/Enemy_front_1.png"));
            down2 = ImageIO.read(new File("resources/enemy/Enemy_front_2.png"));
            left1 = ImageIO.read(new File("resources/enemy/Enemy_left_1.png"));
            left2 = ImageIO.read(new File("resources/enemy/Enemy_left_2.png"));
            right1 = ImageIO.read(new File("resources/enemy/Enemy_right_1.png"));
            right2 = ImageIO.read(new File("resources/enemy/Enemy_right_2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //simple update function to move in the direction of the player
    public void update(Player pc){

        double distance = Point2D.distance(pc.x, pc.y, this.x, this.y);
        
        if(Point2D.distance(pc.x, pc.y, this.x, this.y - 5) < distance){
            distance = Point2D.distance(pc.x, pc.y, this.x, this.y - 5);
            direction = "up";
            y -= step;
        }
        if(Point2D.distance(pc.x, pc.y, this.x - 5, this.y) < distance){
            distance = Point2D.distance(pc.x, pc.y, this.x - 5, this.y);
            direction = "left";
            x -= step;
        }
        if(Point2D.distance(pc.x, pc.y, this.x, this.y + 5) < distance){
            distance = Point2D.distance(pc.x, pc.y, this.x, this.y + 5);
            direction = "down";
            y += step;
        }
        if(Point2D.distance(pc.x, pc.y, this.x + 5, this.y) < distance){
            direction = "right";
            x += step;
        }

        solidArea.x = x + 5;
        solidArea.y = y + 5;

        animationHandler();

    }
}