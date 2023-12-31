package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class SuperObject {
    GamePanel gp;

    public SuperObject(){
        this.gp = gp;
    }
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX,worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 64, 64);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                //  System.out.println("tileNum: " + tileNum);
                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        }
    }

    public double distanceTo(SuperObject obj) {
        return Math.sqrt(Math.pow(this.worldX - obj.worldX, 2) + Math.pow(this.worldY - obj.worldY, 2));
    }
}
