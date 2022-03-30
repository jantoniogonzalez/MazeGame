package rewards;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NegativeReward extends Reward{

    Game gp;
    public BufferedImage punishment1, punishment2;

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
    }
    /**
     Get the sprite images for the regular rewards.
     */
    public void getRewardImage(){
        try {
            punishment1 = ImageIO.read(new File("resources/enemy/Injury_1.png"));
            punishment2 = ImageIO.read(new File("resources/enemy/Injury_2.png"));
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

            solidArea.x = rx + 3;
            solidArea.y = ry + 14;
            solidArea.width = 39;
            solidArea.height = 38;

            if (animation == 1) {
                image = punishment1;
            } else {
                image = punishment2;
            }
            g2.drawImage(image, rx, ry, gp.trueTileSizeX, gp.trueTileSizeY, null);
        }
    }
}
