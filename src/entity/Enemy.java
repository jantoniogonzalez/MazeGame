package entity;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.geom.Point2D;

public class Enemy extends Entity{

    Game gp;

    public Enemy(Game gp){
        this.gp = gp;

        setDefaultValues();
        getEnemyImage();
    }

    public void setDefaultValues(){

        x = 300;
        y = 300;
        step = 1;
        direction = "down";
        animation = 1;
        animationCounter = 0;

    }

    public void animationHandler(){

        animationCounter++;

        if(animationCounter <= 20){
            animation = 1;
        }
        else if( animationCounter <= 40){
            animation = 2;
        }
        else{
            animationCounter = 0;
        }

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

        animationHandler();

    }

    public void draw(Graphics g2){

        g2.fillRect(x, y, gp.trueTileSizeX, gp.trueTileSizeY);

        BufferedImage image = null;

        switch (direction){
            case "up":
                if(animation == 1) {
                    image = up1;
                }
                else if(animation == 2){
                    image = up2;
                }
                break;
            case "left":
                if(animation == 1) {
                    image = left1;
                }
                else if(animation == 2){
                    image = left2;
                }
                break;
            case "down":
                if(animation == 1) {
                    image = down1;
                }
                else if(animation == 2){
                    image = down2;
                }
                break;
            case "right":
                if(animation == 1) {
                    image = right1;
                }
                else if(animation == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, x, y, gp.trueTileSizeX, gp.trueTileSizeY, null);
    }

}