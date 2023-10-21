package entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int  worldX, worldY;
    public int speed;

//    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // store image
    public BufferedImage walkUp1, walkUp2, walkUp3, walkUp4, walkDown1, walkDown2, walkDown3, walkDown4,walkLeft1, walkLeft2, walkLeft3,walkLeft4,
    walkRight1, walkRight2, walkRight3, walkRight4;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn =false;

}
