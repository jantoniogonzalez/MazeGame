package rewards;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NegativeReward extends Reward{

    public NegativeReward(Game gp){
        this.gp = gp;
        setDefaultValues();
        getRewardImage();
    }

    public void setDefaultValues() {
        value = -350;
        animation = 1;
        animationCounter = 0;
        collected = false;
        isRegular = false;
        solidArea = new Rectangle();
        xOffSet = 3;
        yOffSet = 14;
        width = 39;
        height = 38;
    }
    /**
     Get the sprite images for the regular rewards.
     */
    public void getRewardImage(){
        try {
            img1 = ImageIO.read(new File("resources/enemy/Injury_1.png"));
            img2 = ImageIO.read(new File("resources/enemy/Injury_2.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
