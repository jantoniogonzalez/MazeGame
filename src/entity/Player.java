package entity;

import main.CollisionChecker;
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
        score = 0;
        rrCollected = 0;

        setDefaultValues(100, 100, 5);
        getPlayerImage();

        solidArea = new Rectangle(this.x+6, this.y+24, 33, 32);
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

    public void update(CollisionChecker collisionChecker){

        if(keyH.north){
            direction = "up";
        }
        else if(keyH.west){
            direction = "left";
        }
        else if(keyH.south){
            direction = "down";
        }else if(keyH.east){
            direction = "right";
        }

        collisionChecker.checkTile(this);

        if(!collision){
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

        }

        solidArea.x = x+6;
        solidArea.y = y+24;

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
