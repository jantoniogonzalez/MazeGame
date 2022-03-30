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
    boolean isWater1 = true;

    Tile waterTile1, waterTile2, wallTile1, wallTile2, endTile;
    public BufferedImage water1, water2, wall1, wall2, end;

    public TileManager (Game panel, int num){
        this.panel = panel;
        this.map = new Map(num);

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
            water1 = ImageIO.read(new File("resources/tiles/Water_1.png"));
            this.waterTile1 = new Tile(water1, false);
            water2 = ImageIO.read(new File("resources/tiles/Water_2.png"));
            this.waterTile2 = new Tile(water2, false);
            wall1 = ImageIO.read(new File("resources/walls/Wall_1.png"));
            this.wallTile1 = new Tile(wall1, true);
            wall2 = ImageIO.read(new File("resources/walls/Wall_2.png"));
            this.wallTile2 = new Tile(wall2, true);
            end = ImageIO.read(new File("resources/tiles/Exit.png"));
            this.endTile = new Tile(end, false);

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
                        g2.drawImage(wallTile1.image, j* panel.trueTileSizeX, i*panel.trueTileSizeY, panel.trueTileSizeX, panel.trueTileSizeY, null);
                    }else{
                        g2.drawImage(wallTile2.image, j* panel.trueTileSizeX, i*panel.trueTileSizeY, panel.trueTileSizeX, panel.trueTileSizeY, null);
                    }
                }
                else if(map.map[i][j]  == 7){
                    g2.drawImage(endTile.image, j* panel.trueTileSizeX, i*panel.trueTileSizeY, panel.trueTileSizeX, panel.trueTileSizeY, null);
                }
                else{ //water
                    if(isWater1){
                        g2.drawImage(waterTile1.image, j* panel.trueTileSizeX, i*panel.trueTileSizeY, panel.trueTileSizeX, panel.trueTileSizeY, null);
                    }else{
                        g2.drawImage(waterTile2.image, j* panel.trueTileSizeX, i*panel.trueTileSizeY, panel.trueTileSizeX, panel.trueTileSizeY, null);
                    }
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