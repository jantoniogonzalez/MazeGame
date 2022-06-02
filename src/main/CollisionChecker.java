package main;

import entity.Player;

public class CollisionChecker {

    Game gp;

    public CollisionChecker(Game gp){
        this.gp = gp;
    }

    public void checkTile(Player entity){

        int entityLeft = entity.solidArea.x;
        int entityTop = entity.solidArea.y;
        int entityRight = entity.solidArea.x + entity.solidArea.width ;
        int entityBottom = entity.solidArea.y + entity.solidArea.height;

        double entityLeftCol = Math.floor(entityLeft/gp.trueTileSizeX) % gp.columns;
        double entityRightCol = Math.floor(entityRight/gp.trueTileSizeX) % gp.columns;
        double entityTopRow = Math.floor(entityTop/gp.trueTileSizeY) % gp.rows;
        double entityBottomRow = Math.floor(entityBottom/gp.trueTileSizeY) % gp.rows;

        int tileNum1 =0, tileNum2=0;

        switch (entity.direction) {
            case "up" -> {
                entityTopRow = Math.floor((entityTop - entity.step) / gp.trueTileSizeY) % gp.rows;
                tileNum1 = gp.tM.map.map[(int) entityTopRow][(int) entityLeftCol];
                tileNum2 = gp.tM.map.map[(int) entityTopRow][(int) entityRightCol];
            }
            case "right" -> {
                entityRightCol = Math.floor((entityRight + entity.step) / gp.trueTileSizeX) % gp.columns;
                tileNum1 = gp.tM.map.map[(int) entityTopRow][(int) entityRightCol];
                tileNum2 = gp.tM.map.map[(int) entityBottomRow][(int) entityRightCol];
            }
            case "down" -> {
                entityBottomRow = Math.floor((entityBottom + entity.step) / gp.trueTileSizeY) % gp.rows;
                tileNum1 = gp.tM.map.map[(int) entityBottomRow][(int) entityRightCol];
                tileNum2 = gp.tM.map.map[(int) entityBottomRow][(int) entityLeftCol];
            }
            case "left" -> {
                entityLeftCol = Math.floor((entityLeft - entity.step) / gp.trueTileSizeX) % gp.columns;
                tileNum1 = gp.tM.map.map[(int) entityTopRow][(int) entityLeftCol];
                tileNum2 = gp.tM.map.map[(int) entityBottomRow][(int) entityLeftCol];
            }
        }

        entity.collision = tileNum1 == 1 || tileNum2 == 1;
        if(tileNum1 == 7 || tileNum2 == 7){
            gp.gameWon = checkGoalState(entity);
        }
    }

    public boolean checkGoalState(Player p){
        return p.rrCollected == gp.tM.map.numRR;
    }
}
