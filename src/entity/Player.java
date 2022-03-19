package entity;

import main.Game;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity{

    Game gp;
    KeyHandler keyH;

    public Player(Game gp ,KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){

        x = 100;
        y = 100;
        step = 5;
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

    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(new File("resources/playerImages/Swimmer_back_1.png"));
            up2 = ImageIO.read(new File("resources/playerImages/Swimmer_back_2.png"));
            down1 = ImageIO.read(new File("resources/playerImages/Swimmer_front_1.png"));
            down2 = ImageIO.read(new File("resources/playerImages/Swimmer_front_2.png"));
            left1 = ImageIO.read(new File("resources/playerImages/Swimmer_left_1.png"));
            left2 = ImageIO.read(new File("resources/playerImages/Swimmer_left_2.png"));
            right1 = ImageIO.read(new File("resources/playerImages/Swimmer_right_1.png"));
            right2 = ImageIO.read(new File("resources/playerImages/Swimmer_right_2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){

        if(keyH.north){
            direction = "up";
            y -= step;
        }
        else if(keyH.west){
            direction = "left";
            x -= step;
        }
        else if(keyH.south){
            direction = "down";
            y += step;
        }else if(keyH.east){
            direction = "right";
            x += step;
        }

        animationHandler();

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
