package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        try{
            standingUp = ImageIO.read(getClass().getResourceAsStream("/player/hieu_up_stand.png"));
            walkingUp1 = ImageIO.read(getClass().getResourceAsStream("/player/hieu_up_walk_1.png"));
            walkingUp2 = ImageIO.read(getClass().getResourceAsStream("/player/hieu_up_walk_2.png"));
            standingDown = ImageIO.read(getClass().getResourceAsStream("/player/hieu_down_stand.png"));
            walkingDown1 = ImageIO.read(getClass().getResourceAsStream("/player/hieu_down_walk_1.png"));
            walkingDown2 = ImageIO.read(getClass().getResourceAsStream("/player/hieu_down_walk_2.png"));
            standingLeft = ImageIO.read(getClass().getResourceAsStream("/player/hieu_left_stand.png"));
            walkingLeft1 = ImageIO.read(getClass().getResourceAsStream("/player/hieu_left_walk_2.png"));
            standingRight = ImageIO.read(getClass().getResourceAsStream("/player/hieu_right_stand.png"));
            walkingRight1 = ImageIO.read(getClass().getResourceAsStream("/player/hieu_right_walk_2.png"));


        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true){ // checking if player is moving for animation

            if(keyH.upPressed == true){
                direction = "up";
                y -= speed;

            }
            else if(keyH.downPressed == true){
                direction = "down";
                y += speed;
            }
            else if(keyH.leftPressed == true){
                direction = "left";
                x -= speed;
            }
            else if(keyH.rightPressed == true){
                direction = "right";
                x += speed;
            }

            spriteCounter++;
            if(spriteCounter > 10) {
                if(spriteNum == 1) spriteNum = 2;
                else if (spriteNum == 2) spriteNum = 3;
                else if (spriteNum == 3) spriteNum = 1;
                spriteCounter=0;
            }
        }

    }
    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x,y, gp.tileSize, gp.tileSize);

        BufferedImage image = null; // set a value to prevent error

        switch (direction){
            case "up":
                if(spriteNum == 1){
                    image = standingUp;
                }
                if(spriteNum == 2){
                    image = walkingUp1;
                }
                if(spriteNum == 3){
                    image = walkingUp2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = standingDown;
                }
                if(spriteNum == 2){
                    image = walkingDown1;
                }
                if(spriteNum == 3){
                    image = walkingDown2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = standingLeft;
                }
                if(spriteNum == 2){
                    image = walkingLeft1;
                }
                if(spriteNum == 3){
                    image = standingLeft;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = standingRight;
                }
                if(spriteNum == 2){
                    image = walkingRight1;
                }
                if(spriteNum == 3){
                    image = standingRight;
                }
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
