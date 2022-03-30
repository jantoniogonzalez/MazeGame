package rewards;

import entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Reward {

    public int animation;
    public int animationCounter;
    public boolean collected, isRegular;
    public int value;

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
}
