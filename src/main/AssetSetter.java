package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import java.util.Random;

import tile.TileImageLoader;
import tile.TileManager;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }
    /*
    public void setObject(){
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 23*gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

        gp.obj[2] = new OBJ_Key();
        gp.obj[2].worldX = 37*gp.tileSize;
        gp.obj[2].worldY = 7 * gp.tileSize;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 10*gp.tileSize;
        gp.obj[3].worldY = 9 * gp.tileSize;

        gp.obj[4] = new OBJ_Door();
        gp.obj[4].worldX = 8*gp.tileSize;
        gp.obj[4].worldY = 28 * gp.tileSize;

        gp.obj[5] = new OBJ_Door();
        gp.obj[5].worldX = 12*gp.tileSize;
        gp.obj[5].worldY = 22* gp.tileSize;

        gp.obj[6] = new OBJ_Chest();
        gp.obj[6].worldX = 10*gp.tileSize;
        gp.obj[6].worldY = 7 * gp.tileSize;
    }
*/
    public void setObjectRandom(){
        Random random = new Random();
        int numKeys = 20;

        for(int i=0; i<numKeys;i++){
            int x = random.nextInt(gp.maxWorldCol);
            int y = random.nextInt(gp.maxWorldRow);

            OBJ_Key newKey = new OBJ_Key();
            newKey.worldX = x * gp.tileSize;
            newKey.worldY = y * gp.tileSize;

            boolean validKey = true;
            for (OBJ_Key existingKey : gp.keys) {
                if (newKey.distanceTo(existingKey) < 10) {
                    validKey = false;
                    break;
                }
            }

            if (validKey) {
                gp.keys.add(newKey);
                System.out.println("Generated key at (" + x + ", " + y + ")");
            } else {
                System.out.println("Invalid key. Regenerating...");
                i--; // Retry generating a key
            }
        }
    }

    public boolean checkValid(OBJ_Key newKey){
        for(int i=0;i< gp.tileM.layerNum; i++){
            for(int j=0; gp.tileM.tile[i][j] != null; j++){
                if(gp.tileM.tile[i][j].collision == true){
                    
                }
            }
        }
    }
}
