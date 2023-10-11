package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int  x, y;
    public int speed;

//    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // store image
    public BufferedImage standingUp, walkingUp1, walkingUp2, standingDown,
            walkingDown1, walkingDown2, standingLeft, walkingLeft1, standingRight, walkingRight1;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

}
