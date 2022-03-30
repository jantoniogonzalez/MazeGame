package rewards;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RegularReward extends Reward{

    Game gp;
    public BufferedImage rr1, rr2;

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
    }
    /**
     Get the sprite images for the regular rewards.
     */
    public void getRewardImage(){
        try {
             rr1 = ImageIO.read(new File("resources/rewards/Reward_1.png"));
             rr2 = ImageIO.read(new File("resources/rewards/Reward_2.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void drawReward(Graphics g2, int rx, int ry){

        if(!collected) {

            Color c = new Color(0, 0, 0, 0);

            g2.setColor(c);

            g2.fillRect(rx, ry, gp.trueTileSizeX, gp.trueTileSizeY);

            BufferedImage image;

            solidArea.x = rx + 13;
            solidArea.y = ry + 13;
            solidArea.width = 21;
            solidArea.height = 45;

            if (animation == 1) {
                image = rr1;
            } else {
                image = rr2;
            }
            g2.drawImage(image, rx, ry, gp.trueTileSizeX, gp.trueTileSizeY, null);
        }
    }

}
