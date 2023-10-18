package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2, tileNum3, tileNum4, tileNum5, tileNum6;

        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                for (int i = 0; i < gp.tileM.layerNum; i++) {
                    for (int row = entityTopRow; row <= entityBottomRow; row++) {
                        for (int col = entityLeftCol; col <= entityRightCol; col++) {
                            if (gp.tileM.mapTileNum[i][col][row] != -1) {
                                int tileNum = gp.tileM.mapTileNum[i][col][row];
                                if (gp.tileM.tile[i][tileNum].collision) {
                                    entity.collisionOn = true;
                                    return; // If collision detected, no need to check further
                                }
                            }
                        }
                    }
                }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                for (int i = 0; i < gp.tileM.layerNum; i++) {
                    for (int row = entityTopRow; row <= entityBottomRow; row++) {
                        for (int col = entityLeftCol; col <= entityRightCol; col++) {
                            if (gp.tileM.mapTileNum[i][col][row] != -1) {
                                int tileNum = gp.tileM.mapTileNum[i][col][row];
                                if (gp.tileM.tile[i][tileNum].collision) {
                                    entity.collisionOn = true;
                                    return; // If collision detected, no need to check further
                                }
                            }
                        }
                    }
                }

                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                for (int i = 0; i < gp.tileM.layerNum; i++) {
                    for (int row = entityTopRow; row <= entityBottomRow; row++) {
                        for (int col = entityLeftCol; col <= entityRightCol; col++) {
                            if (gp.tileM.mapTileNum[i][col][row] != -1) {
                                int tileNum = gp.tileM.mapTileNum[i][col][row];
                                if (gp.tileM.tile[i][tileNum].collision) {
                                    entity.collisionOn = true;
                                    return; // If collision detected, no need to check further
                                }
                            }
                        }
                    }
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;

                /* OLD CODE FOR CHECKING COLLSION BY CORNER
                for(int i=0;i<3;i++){
                    if(gp.tileM.mapTileNum[i][entityRightCol][entityTopRow] != -1 && gp.tileM.mapTileNum[i][entityRightCol][entityBottomRow] != -1){
                        tileNum1 = gp.tileM.mapTileNum[i][entityRightCol][entityTopRow];
                        tileNum2 = gp.tileM.mapTileNum[i][entityRightCol][entityBottomRow];

                        if(gp.tileM.tile[i][tileNum1].collision == true ||
                                gp.tileM.tile[i][tileNum2].collision == true){
                            entity.collisionOn = true;
                        }
                    }


                }
                */
                for (int i = 0; i < gp.tileM.layerNum; i++) {
                    for (int row = entityTopRow; row <= entityBottomRow; row++) {
                        for (int col = entityLeftCol; col <= entityRightCol; col++) {
                            if (gp.tileM.mapTileNum[i][col][row] != -1) {
                                int tileNum = gp.tileM.mapTileNum[i][col][row];
                                if (gp.tileM.tile[i][tileNum].collision) {
                                    entity.collisionOn = true;
                                    return; // If collision detected, no need to check further
                                }
                            }
                        }
                    }
                }
                break;
        }
    }
}
