package main;

import object.*;

import java.util.Random;

import tile.TileImageLoader;
import tile.TileManager;

public class AssetSetter {
    GamePanel gp;
    public int numKeys = 5;
    public int numBoots = 5;
    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }

    public void setChest(){
        SuperObject newChest = new OBJ_Chest();
        newChest.worldX = 20 * gp.tileSize;
        newChest.worldY = 20 * gp.tileSize;
        gp.obj.add(newChest);
    }

    public void setDoor(){
        SuperObject newDoor = new OBJ_Door();
        newDoor.worldX = 22 * gp.tileSize;
        newDoor.worldY = 6 * gp.tileSize;
        gp.obj.add(newDoor);
    }
    public void setBoots(){


       SuperObject newBoot = new OBJ_Boots();
       newBoot.worldX = 10 * gp.tileSize;
       newBoot.worldY = 10 * gp.tileSize;
       gp.obj.add(newBoot);
    }

    public void setKeyRandom(){
        Random random = new Random();


        for(int i=0; i<numKeys;i++){
            int x = random.nextInt(gp.maxWorldCol);
            int y = random.nextInt(gp.maxWorldRow);



            SuperObject newKey = new OBJ_Key();

            newKey.worldX = x * gp.tileSize;
            newKey.worldY = y * gp.tileSize;

            boolean validKey = true;
            for (SuperObject existingKey : gp.obj) {
                if ((existingKey instanceof OBJ_Key && newKey.distanceTo(existingKey) < 10*gp.tileSize) ||
                        (existingKey instanceof OBJ_Boots && newKey.distanceTo(existingKey) < 5)) {

                        validKey = false;
                        break;

                }
                //System.out.println("keydist: "+newKey.distanceTo(existingKey) );
            }

            if (validKey && isValidKeyPlacement(newKey)) {
                gp.obj.add(newKey);
                System.out.println("Generated key at (" + x + ", " + y + ")");
            } else {
                System.out.println("Invalid key. Regenerating...");
                i--; // Retry generating a key
            }
        }
    }

    public boolean isValidKeyPlacement(SuperObject newKey){
        int voidCount = 0;
        for(int i=0;i< gp.tileM.layerNum; i++){

            int tileNum = gp.tileM.mapTileNum[i][newKey.worldX/gp.tileSize][newKey.worldY/gp.tileSize];

            // divine by tilesize to get x,y or col and row
            if(tileNum != -1){
                if(gp.tileM.tile[i][tileNum].collision == true){
                    return false;
                }
            }
            else voidCount++;

        }
        if(voidCount == gp.tileM.layerNum) return false; // CHECK IF THE KEY IS IN THE VOID
        return true;
    }
}
