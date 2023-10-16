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
                for(int i=0;i<3;i++){
                    if(gp.tileM.mapTileNum[i][entityLeftCol][entityTopRow] != -1 && gp.tileM.mapTileNum[i][entityRightCol][entityTopRow] != -1){
                        tileNum1 = gp.tileM.mapTileNum[i][entityLeftCol][entityTopRow];
                        tileNum2 = gp.tileM.mapTileNum[i][entityRightCol][entityTopRow];
                        if(gp.tileM.tile[i][tileNum1].collision == true ||
                                gp.tileM.tile[i][tileNum2].collision == true){
                            entity.collisionOn = true;

                        }
                    }

                }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                for(int i=0; i<3;i++){
                    if(gp.tileM.mapTileNum[i][entityLeftCol][entityBottomRow] != -1 && gp.tileM.mapTileNum[i][entityRightCol][entityBottomRow] != -1){
                        tileNum1 = gp.tileM.mapTileNum[i][entityLeftCol][entityBottomRow];
                        tileNum2 = gp.tileM.mapTileNum[i][entityRightCol][entityBottomRow];
                        if(gp.tileM.tile[i][tileNum1].collision == true ||
                                gp.tileM.tile[i][tileNum2].collision == true){
                            entity.collisionOn = true;
                        }
                    }

                }


                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                for(int i=0;i<3;i++){
                    if(gp.tileM.mapTileNum[i][entityLeftCol][entityTopRow] != -1 && gp.tileM.mapTileNum[i][entityLeftCol][entityBottomRow] != -1){
                        tileNum1 = gp.tileM.mapTileNum[i][entityLeftCol][entityTopRow];
                        tileNum2 = gp.tileM.mapTileNum[i][entityLeftCol][entityBottomRow];

                        if(gp.tileM.tile[i][tileNum1].collision == true ||
                                gp.tileM.tile[i][tileNum2].collision == true){
                            entity.collisionOn = true;
                        }
                    }


                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;

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

                break;
        }
    }
}
