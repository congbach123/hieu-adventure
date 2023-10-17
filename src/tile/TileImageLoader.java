package tile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import tile.TileManager;

public class TileImageLoader {
    public static int getFileLength(Tile[] tile, String folderPath){
        File f = new File("res"+ folderPath);
        String[] files = f.list();

        return files.length;
    }


    public static void getTileImage(Tile[] tile, String folderPath){


        try {
          //  System.out.println("/res"+folderPath);
            File f = new File("res"+ folderPath);
            String[] files = f.list();

           // System.out.println(files.length);
            for (int i = 0; i < files.length-1; i++) {
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
