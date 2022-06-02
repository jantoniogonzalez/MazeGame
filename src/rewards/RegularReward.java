package rewards;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RegularReward extends Reward{

    public RegularReward(Game gp){
        this.gp = gp;
        setDefaultValues();
        getRewardImage();
    }

    public void setDefaultValues() {
        value = 100;
        animation = 1;
        animationCounter = 0;
        collected = false;
        isRegular = true;
        solidArea = new Rectangle();
        xOffSet = 13;
        yOffSet = 13;
        width = 21;
        height = 45;
    }
    /**
     Get the sprite images for the regular rewards.
     */
    public void getRewardImage(){
        try {
             img1 = ImageIO.read(new File("resources/rewards/Reward_1.png"));
             img2 = ImageIO.read(new File("resources/rewards/Reward_2.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
