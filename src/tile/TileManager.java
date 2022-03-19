package tile;
import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileManager {

    Game panel;
    Map map;
    int animation, animationCounter;
    Tile waterTile1, waterTile2, wallTile1, wallTile2;
    public BufferedImage water1, water2, wall1, wall2;

    public TileManager (Game panel, int num){
        this.panel = panel;
        this.map = new Map(num);
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

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        //draw 2 borders up and down
        boolean isWall1 = true;
        boolean isWater1 = true;

        for(int i =0; i < panel.rows; i++){
            for(int j = 0; j < panel.columns; j++){
                if(map.map[i][j]  == 1){ //wall
                    if(isWall1){
                        g2.drawImage(wallTile1.image, j* panel.trueTileSizeX, i*panel.trueTileSizeY, panel.trueTileSizeX, panel.trueTileSizeY, null);
                    }else{
                        g2.drawImage(wallTile2.image, j* panel.trueTileSizeX, i*panel.trueTileSizeY, panel.trueTileSizeX, panel.trueTileSizeY, null);
                    }
                }else{ //water
                    if(isWater1){
                        g2.drawImage(waterTile1.image, j* panel.trueTileSizeX, i*panel.trueTileSizeY, panel.trueTileSizeX, panel.trueTileSizeY, null);
                    }else{
                        g2.drawImage(waterTile2.image, j* panel.trueTileSizeX, i*panel.trueTileSizeY, panel.trueTileSizeX, panel.trueTileSizeY, null);
                    }
                }
            }
        }
    }
}
