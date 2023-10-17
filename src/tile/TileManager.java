package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import tile.TileImageLoader;

public class TileManager {

    final public int layerNum = 3;
    GamePanel gp;
    public Tile[][] tile;
    public int[][][] mapTileNum;
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();
    /*
    public Tile[] getTileForWall() {
        return tileForWall;
    }
    public Tile[] getTileForWall2() {
        return tileForWall2;
    }

    public Tile[] getTileForInterior() {
        return tileForInterior;
    }

    public int[][] getMapTileNumWall() {
        return mapTileNumWall;
    }

    public int[][] getMapTileNumWall2() {
        return mapTileNumWall2;
    }

    public int[][] getMapTileNumInterior() {
        return mapTileNumInterior;
    }
    */

    public TileManager(GamePanel gp){
        this.gp = gp;

        // READ TILE DATA FILE

        tile = new Tile[layerNum][1400];


        TileImageLoader.getTileImage(tile[0], "/tiles/wall2");
        TileImageLoader.getTileImage(tile[1], "/tiles/wall2");
        TileImageLoader.getTileImage(tile[2], "/tiles/wall");
        MapDataReader.readMapData(tile[0], "/tiles/wall2/collision.txt");
        MapDataReader.readMapData(tile[1], "/tiles/wall2/collision.txt");
        MapDataReader.readMapData(tile[2], "/tiles/wall/collision.txt");


        /*
        getTileImage(tile[0], "/tiles/wall2");
        getTileImage(tile[1], "/tiles/wall2");
        getTileImage(tile[2], "/tiles/wall");
        readMapData(tile[0], "/tiles/wall2/collision.txt");
        readMapData(tile[1], "/tiles/wall2/collision.txt");
        readMapData(tile[2], "/tiles/wall/collision.txt");
        */

        mapTileNum = new int[layerNum][gp.maxWorldCol][gp.maxWorldRow];

        loadMap("/maps/map2_interior.txt", mapTileNum[0]);
        loadMap("/maps/map2_wall2.txt", mapTileNum[1]);
        loadMap("/maps/map2_wall.txt", mapTileNum[2]);
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
