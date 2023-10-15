package tile;

import tile.Tile;

import java.io.*;

public class MapDataReader {
    public static void readMapData(Tile[] tile, String filePath) {
        try {
            InputStream inputStream = MapDataReader.class.getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                int index = Integer.parseInt(line);
                //tile[index] = new Tile();
                // Check if index is within the range of tiles array
                tile[index].collision = true;

            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
