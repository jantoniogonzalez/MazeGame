package tile;
import entity.Player;
import main.Game;
import rewards.BonusReward;
import rewards.NegativeReward;
import rewards.RegularReward;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileManager {

    Game panel;
    public Map map;
    BonusReward[] arrBR;
    RegularReward[] arrRR;
    NegativeReward[] arrNR;

    public int animationCounter;
    boolean isWall1 = true;

    public BufferedImage water, wall1, wall2, end;

    public TileManager (Game panel){
        this.panel = panel;
        this.map = new Map();

        arrBR = new BonusReward[map.numBR];
        arrRR = new RegularReward[map.numRR];
        arrNR = new NegativeReward[map.numNR];

        BonusRewardMaker(panel);
        RegularRewardMaker(panel);
        NegativeRewardMaker(panel);

        getTileImage();
    }

    public void getTileImage(){
        try{
            water = ImageIO.read(new File("resources/tiles/Water_1.png"));
            wall1 = ImageIO.read(new File("resources/walls/Wall_1.png"));
            wall2 = ImageIO.read(new File("resources/walls/Wall_2.png"));
            end = ImageIO.read(new File("resources/tiles/Exit.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void BonusRewardMaker(Game gp){
        int n;

        for(n = 0 ; n<map.numBR ; n++){
            arrBR[n] = new BonusReward(gp);
        }
    }

    public void RegularRewardMaker(Game gp){

        int n;

        for(n = 0 ; n<map.numRR;n++){
            arrRR[n] = new RegularReward(gp);
        }
    }

    public void NegativeRewardMaker(Game gp){

        int n;

        for(n = 0 ; n<map.numNR;n++){
            arrNR[n] = new NegativeReward(gp);
        }
    }

    public void draw(Graphics2D g2){

        int rrCounter = 0;
        int brCounter = 0;
        int nrCounter = 0;

        for(int i =0; i < panel.rows; i++){
            for(int j = 0; j < panel.columns; j++){
                if(map.map[i][j]  == 1){ //wall
                    if(isWall1){
                        g2.drawImage(wall1, j* panel.trueTileSizeX, i*panel.trueTileSizeY, panel.trueTileSizeX, panel.trueTileSizeY, null);
                    }else{
                        g2.drawImage(wall2, j* panel.trueTileSizeX, i*panel.trueTileSizeY, panel.trueTileSizeX, panel.trueTileSizeY, null);
                    }
                }
                else if(map.map[i][j]  == 7){
                    g2.drawImage(end, j* panel.trueTileSizeX, i*panel.trueTileSizeY, panel.trueTileSizeX, panel.trueTileSizeY, null);
                }
                else{ //water
                    g2.drawImage(water, j* panel.trueTileSizeX, i*panel.trueTileSizeY, panel.trueTileSizeX, panel.trueTileSizeY, null);
                    if(map.map[i][j]  == 2) {//regular reward
                        arrRR[rrCounter].drawReward(g2, j* panel.trueTileSizeX, i*panel.trueTileSizeY);
                        rrCounter ++;
                    }
                    else if(map.map[i][j]  == 3){//bonus reward
                        arrBR[brCounter].drawReward(g2, j* panel.trueTileSizeX, i*panel.trueTileSizeY);
                        brCounter ++;
                    }
                    else if(map.map[i][j]  == 4){//bonus reward
                        arrNR[nrCounter].drawReward(g2, j* panel.trueTileSizeX, i*panel.trueTileSizeY);
                        nrCounter ++;
                    }
                }
            }
        }
    }

    public void animationHandler(Player p){
        int n = 0;
        int n1 = 0;
        int n2 = 0;

        animationCounter++;

        if(animationCounter <= 60){
            isWall1 = true;
            //isWater1 = true;
        }
        else if(animationCounter <= 120) {
            isWall1 = false;
            //isWater1 = false;
        }
        else {
            animationCounter = 0;
        }

        while(n1 < map.numRR){
            arrRR[n1].animationHandler();
            arrRR[n1].checkCollection(p);
            n1++;
        }
        while(n < map.numBR){
            arrBR[n].animationHandler();
            arrBR[n].checkCollection(p);
            n++;
        }
        while(n2 < map.numNR){
            arrNR[n2].animationHandler();
            arrNR[n2].checkCollection(p);
            n2++;
        }
    }
}