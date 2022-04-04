package entity;

import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int step;
    public int animation;
    public int animationCounter;
    public int score;
    public int rrCollected;

    Game gp;

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

    public void draw(Graphics g2){

        Color c = new Color(0, 0, 0, 0);

        g2.setColor(c);
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
