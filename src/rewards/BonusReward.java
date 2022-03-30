package rewards;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BonusReward extends Reward {

    Game gp;
    public BufferedImage br1, br2;

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
    }
    /**
     Get the sprite images for the regular rewards.
     */
    public void getRewardImage(){
        try {
            br1 = ImageIO.read(new File("resources/rewards/Bonus_1.png"));
            br2 = ImageIO.read(new File("resources/rewards/Bonus_2.png"));
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

            solidArea.x = rx + 10;
            solidArea.y = ry + 6;
            solidArea.width = 25;
            solidArea.height = 54;

            if (animation == 1) {
                image = br1;
            } else {
                image = br2;
            }
            g2.drawImage(image, rx, ry, gp.trueTileSizeX, gp.trueTileSizeY, null);
        }
    }
}
