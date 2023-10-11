package tile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class TileImageLoader {
    public static void getTileImage(){
        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/01_house_1.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/02_house_2.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/03_chest_1.png"));

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
