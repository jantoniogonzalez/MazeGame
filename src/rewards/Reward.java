package rewards;

import entity.Player;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Reward {

    public int animation;
    public int animationCounter;
    public boolean collected, isRegular;
    public int value, xOffSet, yOffSet, width, height;

    Game gp;

    public BufferedImage img1, img2;

    public Rectangle solidArea;

    /**
     Class handles animation (changes) of sprites. This method is being included to animate
     the bonus rewards, but it can be used by any animated sprite.
     */
    public void animationHandler() {
        animationCounter++;
        if(animationCounter <= 20){
            animation = 1;
        }
        else if(animationCounter <= 40) {
            animation = 2;
        }
        else {
            animationCounter = 0;
        }
    }

    public void checkCollection(Player player){
        if(player.solidArea.intersects(solidArea)){
            player.score += value;
            if(!collected && isRegular){
                player.rrCollected += 1;
            }
            collected = true;
            value = 0;
        }
    }

    public void drawReward(Graphics g2, int rx, int ry){

        if(!collected) {
            Color c = new Color(0, 0, 0, 0);

            g2.setColor(c);

            g2.fillRect(rx, ry, gp.trueTileSizeX, gp.trueTileSizeY);

            BufferedImage image;

            solidArea.x = rx + xOffSet;
            solidArea.y = ry + yOffSet;
            solidArea.width = width;
            solidArea.height = height;

            if (animation == 1) {
                image = img1;
            } else {
                image = img2;
            }
            g2.drawImage(image, rx, ry, gp.trueTileSizeX, gp.trueTileSizeY, null);
        }
    }
}
