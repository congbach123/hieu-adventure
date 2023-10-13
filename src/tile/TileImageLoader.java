package tile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

import tile.TileManager;

public class TileImageLoader {


    public static void getTileImage(Tile[] tile, String folderPath){
        try{
            for (int i = 0; i < tile.length; i++) {
                tile[i] = new Tile();
                String imagePath = folderPath + "/tile" + String.format("%03d", i) + ".png";
                InputStream stream = TileImageLoader.class.getResourceAsStream(imagePath);
                if (stream != null) {
                    tile[i].image = ImageIO.read(stream);
                } else {
                    System.out.println("Image not found: " + imagePath);
                }
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
