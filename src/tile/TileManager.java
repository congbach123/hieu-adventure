package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import tile.TileImageLoader;

public class TileManager {

    final public int layerNum = 9;
    GamePanel gp;
    public Tile[][] tile;
    public int[][][] mapTileNum;
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();

    public TileManager(GamePanel gp){
        this.gp = gp;

        // READ TILE DATA FILE

        tile = new Tile[layerNum][17500];

        /*
        TileImageLoader.getTileImage(tile[0], "/tiles/wall2");
        TileImageLoader.getTileImage(tile[1], "/tiles/wall2");
        TileImageLoader.getTileImage(tile[2], "/tiles/wall");
        MapDataReader.readMapData(tile[0], "/tiles/wall2/collision.txt");
        MapDataReader.readMapData(tile[1], "/tiles/wall2/collision.txt");
        MapDataReader.readMapData(tile[2], "/tiles/wall/collision.txt");
        */

        TileImageLoader.getTileImage(tile[0], "/tiles/Room");
        TileImageLoader.getTileImage(tile[1], "/tiles/Easter");
        for (int i = 2; i <= 5; i++) {
            System.arraycopy(tile[0], 0, tile[i], 0, tile[0].length);
        }
//        TileImageLoader.getTileImage(tile[1], "/tiles/Room");
//        TileImageLoader.getTileImage(tile[2], "/tiles/Room");
//        TileImageLoader.getTileImage(tile[3], "/tiles/Room");
//        TileImageLoader.getTileImage(tile[4], "/tiles/Room");
        TileImageLoader.getTileImage(tile[6], "/tiles/Interiors");
        for (int i = 7; i <= 8; i++) {
            System.arraycopy(tile[6], 0, tile[i], 0, tile[6].length);
        }

//        TileImageLoader.getTileImage(tile[6], "/tiles/Interiors");
//        TileImageLoader.getTileImage(tile[7], "/tiles/Interiors");
        MapDataReader.readMapData(tile[0], "/tiles/Room/collision.txt");
        MapDataReader.readMapData(tile[1], "/tiles/Easter/collision.txt");
        MapDataReader.readMapData(tile[2], "/tiles/Room/collision.txt");
        MapDataReader.readMapData(tile[3], "/tiles/Room/collision.txt");
        MapDataReader.readMapData(tile[4], "/tiles/Room/collision.txt");
        MapDataReader.readMapData(tile[5], "/tiles/Room/collision.txt");
        MapDataReader.readMapData(tile[6], "/tiles/Interiors/collision.txt");
        MapDataReader.readMapData(tile[7], "/tiles/Interiors/collision.txt");
        MapDataReader.readMapData(tile[8], "/tiles/Interiors/collision.txt");

        mapTileNum = new int[layerNum][gp.maxWorldCol][gp.maxWorldRow];

        /* OLD LOAD MAP
        loadMap("/maps/map2_interior.txt", mapTileNum[0]);
        loadMap("/maps/map2_wall2.txt", mapTileNum[1]);
        loadMap("/maps/map2_wall.txt", mapTileNum[2]);

         */
        loadMap("/maps/layer2.9.txt", mapTileNum[0]);
        loadMap("/maps/layer0.txt", mapTileNum[1]);
        loadMap("/maps/layer3.txt", mapTileNum[2]);
        loadMap("/maps/layer4.txt", mapTileNum[3]);
        loadMap("/maps/layer1.txt", mapTileNum[4]);
        loadMap("/maps/layer2.txt", mapTileNum[5]);
        loadMap("/maps/layer5.txt", mapTileNum[6]);
        loadMap("/maps/layer5.2.txt", mapTileNum[7]);
        loadMap("/maps/layer5.1.txt", mapTileNum[8]);
    }

    public void loadMap(String filePath, int[][] mapTileNum){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            // read text file map

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();

                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(",");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }

            }
            br.close();
        }catch (Exception e){

        }
    }
    public void draw(Graphics2D g2, int layerIndex, Tile[] tile){
        for (int worldCol = 0; worldCol < gp.maxWorldCol; worldCol++) {
            for (int worldRow = 0; worldRow < gp.maxWorldRow; worldRow++) {
                int tileNum = mapTileNum[layerIndex][worldCol][worldRow];

                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                // DRAW IN SCREEN
                if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                        worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                    if (tileNum != -1) {
                      //  System.out.println("tileNum: " + tileNum);
                        g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    }
                }
            }
        }
    }
}
