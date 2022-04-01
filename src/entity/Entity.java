package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int step;
    public int animation;
    public int animationCounter;
    public int score;
    public int rrCollected;

    public Rectangle solidArea;
    public boolean collision = false;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;


    public void setDefaultValues(int x, int y, int step){

        this.x = x;
        this.y = y;
        this.step = step;
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
            score -= 5;
        }

    }

}
