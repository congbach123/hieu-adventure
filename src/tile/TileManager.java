package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import tile.TileImageLoader;

public class TileManager {

    GamePanel gp;
    public Tile[] tileForWall2, tileForWall, tileForInterior;
    int mapTileNumWall[][], mapTileNumWall2[][], mapTileNumInterior[][];

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

    public TileManager(GamePanel gp){
        this.gp = gp;

        tileForWall = new Tile[391]; // number of tile, can be changed
        tileForWall2 = new Tile[391];
        tileForInterior = new Tile[391];
        mapTileNumWall = new int[gp.maxWorldCol][gp.maxWorldRow];
        mapTileNumWall2 = new int[gp.maxWorldCol][gp.maxWorldRow];// maxscreencol row
        mapTileNumInterior = new int[gp.maxWorldCol][gp.maxWorldRow];

        TileImageLoader.getTileImage(tileForInterior, "/tiles/wall2");
        loadMap("/maps/map2_interior.txt", mapTileNumInterior);
        TileImageLoader.getTileImage(tileForWall2, "/tiles/wall2");
        loadMap("/maps/map2_wall2.txt", mapTileNumWall2);
        TileImageLoader.getTileImage(tileForWall, "/tiles/wall");
        loadMap("/maps/map2_wall.txt", mapTileNumWall);


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
    public void draw(Graphics2D g2, int [][] mapTileNum, Tile[] tile){

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];


            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                if(mapTileNum[worldCol][worldRow] != -1)

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;


            if(worldCol == gp.maxWorldCol){
                worldCol = 0;

                worldRow++;

            }
        }
    }
}
