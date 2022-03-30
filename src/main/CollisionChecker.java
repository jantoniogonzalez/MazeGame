package main;

import entity.Entity;

public class CollisionChecker {

    Game gp;

    public CollisionChecker(Game gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){

        int entityLeft = entity.solidArea.x;
        int entityTop = entity.solidArea.y;
        int entityRight = entity.solidArea.x + entity.solidArea.width ;
        int entityBottom = entity.solidArea.y + entity.solidArea.height;

        double entityLeftCol = Math.floor(entityLeft/gp.trueTileSizeX) % gp.columns;
        double entityRightCol = Math.floor(entityRight/gp.trueTileSizeX) % gp.columns;
        double entityTopRow = Math.floor(entityTop/gp.trueTileSizeY) % gp.rows;
        double entityBottomRow = Math.floor(entityBottom/gp.trueTileSizeY) % gp.rows;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow = Math.floor((entityTop-entity.step)/gp.trueTileSizeY) % gp.rows;
                tileNum1 = gp.tM.map.map[(int) entityTopRow][(int) entityLeftCol];
                tileNum2 = gp.tM.map.map[(int) entityTopRow][(int) entityRightCol];
                if(tileNum1 == 1 || tileNum2 == 1){
                    entity.collision = true;
                }
                else{
                    entity.collision = false;
                }
                break;
            case "right":
                entityRightCol = Math.floor((entityRight+entity.step)/gp.trueTileSizeX) % gp.columns;
                tileNum1 = gp.tM.map.map[(int) entityTopRow][(int) entityRightCol];
                tileNum2 = gp.tM.map.map[(int) entityBottomRow][(int) entityRightCol];
                if(tileNum1 == 1 || tileNum2 == 1){
                    entity.collision = true;
                }
                else if((tileNum1 == 7 || tileNum2 == 7) && checkGoalState(entity)){
                    gp.gameWon = true;
                }
                else{
                    entity.collision = false;
                }
                break;
            case "down":
                entityBottomRow = Math.floor((entityBottom+entity.step)/gp.trueTileSizeY) % gp.rows;
                tileNum1 = gp.tM.map.map[(int) entityBottomRow][(int) entityRightCol];
                tileNum2 = gp.tM.map.map[(int) entityBottomRow][(int) entityLeftCol];
                if(tileNum1 == 1 || tileNum2 == 1){
                    entity.collision = true;
                }
                else{
                    entity.collision = false;
                }
                break;
            case "left":
                entityLeftCol = Math.floor((entityLeft-entity.step)/gp.trueTileSizeX) % gp.columns;
                tileNum1 = gp.tM.map.map[(int) entityTopRow][(int) entityLeftCol];
                tileNum2 = gp.tM.map.map[(int) entityBottomRow][(int) entityLeftCol];
                if(tileNum1 == 1 || tileNum2 == 1){
                    entity.collision = true;
                }
                else{
                    entity.collision = false;
                }
                break;
        }
    }

    public boolean checkGoalState(Entity entity){
        if(entity.rrCollected == gp.tM.map.numRR){
            return true;
        }
        return false;
    }
}
