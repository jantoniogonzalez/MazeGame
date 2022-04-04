package entity;

import main.CollisionChecker;
import main.Game;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Player extends Entity{

    KeyHandler keyH;

    public Player(Game gp ,KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        score = 0;
        rrCollected = 0;

        setDefaultValues(100, 100, 4);
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
}
