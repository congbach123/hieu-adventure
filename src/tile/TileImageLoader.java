package tile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

import tile.TileManager;

public class TileImageLoader {


    public static void getTileImage(Tile[] tile){
        try{
            for (int i = 0; i <= 64; i++) {
                tile[i] = new Tile();
                String imagePath = "/tiles/sprite_" + String.format("%02d", i) + ".png";
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
