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
                tileNum1 = gp.tileM.mapTileNumWall[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNumWall[entityRightCol][entityTopRow];
                tileNum3 = gp.tileM.mapTileNumWall2[entityLeftCol][entityTopRow];
                tileNum4 = gp.tileM.mapTileNumWall2[entityRightCol][entityTopRow];
                tileNum5 = gp.tileM.mapTileNumInterior[entityLeftCol][entityTopRow];
                tileNum6 = gp.tileM.mapTileNumInterior[entityRightCol][entityTopRow];
                if(gp.tileM.tileForWall[tileNum1].collision == true ||
                        gp.tileM.tileForWall[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                if(gp.tileM.tileForWall2[tileNum3].collision == true ||
                        gp.tileM.tileForWall2[tileNum4].collision == true){
                    entity.collisionOn = true;
                }
                if(gp.tileM.tileForInterior[tileNum5].collision == true ||
                        gp.tileM.tileForInterior[tileNum6].collision == true){
                    entity.collisionOn = true;
                }

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumWall[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNumWall[entityRightCol][entityBottomRow];
                tileNum3 = gp.tileM.mapTileNumWall2[entityLeftCol][entityBottomRow];
                tileNum4 = gp.tileM.mapTileNumWall2[entityRightCol][entityBottomRow];
                tileNum5 = gp.tileM.mapTileNumInterior[entityLeftCol][entityBottomRow];
                tileNum6 = gp.tileM.mapTileNumInterior[entityRightCol][entityBottomRow];
                if(gp.tileM.tileForWall[tileNum1].collision == true ||
                        gp.tileM.tileForWall[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                if(gp.tileM.tileForWall2[tileNum3].collision == true ||
                        gp.tileM.tileForWall2[tileNum4].collision == true){
                    entity.collisionOn = true;
                }
                if(gp.tileM.tileForInterior[tileNum5].collision == true ||
                        gp.tileM.tileForInterior[tileNum6].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumWall[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNumWall[entityLeftCol][entityBottomRow];
                tileNum3 = gp.tileM.mapTileNumWall2[entityLeftCol][entityTopRow];
                tileNum4 = gp.tileM.mapTileNumWall2[entityLeftCol][entityBottomRow];
                tileNum5 = gp.tileM.mapTileNumInterior[entityLeftCol][entityTopRow];
                tileNum6 = gp.tileM.mapTileNumInterior[entityLeftCol][entityBottomRow];
                if(gp.tileM.tileForWall[tileNum1].collision == true ||
                        gp.tileM.tileForWall[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                if(gp.tileM.tileForWall2[tileNum3].collision == true ||
                        gp.tileM.tileForWall2[tileNum4].collision == true){
                    entity.collisionOn = true;
                }
                if(gp.tileM.tileForInterior[tileNum5].collision == true ||
                        gp.tileM.tileForInterior[tileNum6].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumWall[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNumWall[entityRightCol][entityBottomRow];
                tileNum3 = gp.tileM.mapTileNumWall2[entityRightCol][entityTopRow];
                tileNum4 = gp.tileM.mapTileNumWall2[entityRightCol][entityBottomRow];
                tileNum5 = gp.tileM.mapTileNumInterior[entityRightCol][entityTopRow];
                tileNum6 = gp.tileM.mapTileNumInterior[entityRightCol][entityBottomRow];
                if(gp.tileM.tileForWall[tileNum1].collision == true ||
                        gp.tileM.tileForWall[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                if(gp.tileM.tileForWall2[tileNum3].collision == true ||
                        gp.tileM.tileForWall2[tileNum4].collision == true){
                    entity.collisionOn = true;
                }
                if(gp.tileM.tileForInterior[tileNum5].collision == true ||
                        gp.tileM.tileForInterior[tileNum6].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
