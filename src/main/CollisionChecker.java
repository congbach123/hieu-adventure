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

        //int tileNum1, tileNum2, tileNum3, tileNum4, tileNum5, tileNum6;

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
                // OLD CODE FOR CHECKING COLLSION BY CORNER
                /*
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

    public int checkObject(Entity entity, boolean player){

        int index = 999;

        for(int i = 0; i < gp.obj.size(); i++){
            if(gp.obj.get(i) !=  null){

                //GET ENTITY'S SOLID AREA POS
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //GET OBJECT'S SOLID AREA POS
                gp.obj.get(i).solidArea.x = gp.obj.get(i).worldX + gp.obj.get(i).solidArea.x;
                gp.obj.get(i).solidArea.y = gp.obj.get(i).worldY + gp.obj.get(i).solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                            if(gp.obj.get(i).collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                            if(gp.obj.get(i).collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                            if(gp.obj.get(i).collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                            if(gp.obj.get(i).collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj.get(i).solidArea.x = gp.obj.get(i).solidAreaDefaultX;
                gp.obj.get(i).solidArea.y = gp.obj.get(i).solidAreaDefaultY;


            }
        }

        return index;
    }
}
