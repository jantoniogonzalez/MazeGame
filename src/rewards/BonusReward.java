package rewards;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BonusReward extends Reward {

    public BonusReward(Game gp){
        this.gp = gp;

        setDefaultValues();
        getRewardImage();
    }

    public void setDefaultValues() {
        value = 500;
        animation = 1;
        animationCounter = 0;
        collected = false;
        isRegular = false;
        solidArea = new Rectangle();
        xOffSet = 10;
        yOffSet = 6;
        width = 25;
        height = 54;
    }
    /**
     Get the sprite images for the regular rewards.
     */
    public void getRewardImage(){
        try {
            img1 = ImageIO.read(new File("resources/rewards/Bonus_1.png"));
            img2 = ImageIO.read(new File("resources/rewards/Bonus_2.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
