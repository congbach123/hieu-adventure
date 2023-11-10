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
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2); // center to image not top-left corner
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(21,30, 24, 31 );
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 22;
        worldY = gp.tileSize * 19;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        try{
            walkUp1 = ImageIO.read(getClass().getResourceAsStream("/player/tile006.png"));
            walkUp2 = ImageIO.read(getClass().getResourceAsStream("/player/tile005.png"));
            walkUp3 = ImageIO.read(getClass().getResourceAsStream("/player/tile008.png"));
            walkUp4 = ImageIO.read(getClass().getResourceAsStream("/player/tile007.png"));
            walkDown1 = ImageIO.read(getClass().getResourceAsStream("/player/tile001.png"));
            walkDown2 = ImageIO.read(getClass().getResourceAsStream("/player/tile000.png"));
            walkDown3 = ImageIO.read(getClass().getResourceAsStream("/player/tile003.png"));
            walkDown4 = ImageIO.read(getClass().getResourceAsStream("/player/tile002.png"));
            walkLeft1 = ImageIO.read(getClass().getResourceAsStream("/player/tile016.png"));
            walkLeft2 = ImageIO.read(getClass().getResourceAsStream("/player/tile015.png"));
            walkLeft3 = ImageIO.read(getClass().getResourceAsStream("/player/tile018.png"));
            walkLeft4 = ImageIO.read(getClass().getResourceAsStream("/player/tile017.png"));
            walkRight1 = ImageIO.read(getClass().getResourceAsStream("/player/tile011.png"));
            walkRight2 = ImageIO.read(getClass().getResourceAsStream("/player/tile010.png"));
            walkRight3 = ImageIO.read(getClass().getResourceAsStream("/player/tile013.png"));
            walkRight4 = ImageIO.read(getClass().getResourceAsStream("/player/tile012.png"));


        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true){ // checking if player is moving for animation

            if(keyH.upPressed == true){
                direction = "up";
            }
            else if(keyH.downPressed == true){
                direction = "down";

            }
            else if(keyH.leftPressed == true){
                direction = "left";

            }
            else if(keyH.rightPressed == true){
                direction = "right";

            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // IF COLLISION FALSE, CAN MOVE
            if(collisionOn == false){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            spriteCounter++;
            if(spriteCounter > 10) {
                if(spriteNum == 1) spriteNum = 2;
                else if (spriteNum == 2) spriteNum = 3;
                else if (spriteNum == 3) spriteNum = 4;
                else if (spriteNum == 4) spriteNum = 1;
                spriteCounter=0;
            }
        }

    }
    public void pickUpObject(int i){
        if(i != 999){
            String objectName = gp.obj.get(i).name;

            switch (objectName){
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj.set(i, null);
                    gp.ui.showMessage("Picked up a key!");
                    //System.out.println("Key:" + hasKey);
                    break;
                    /*
                case "Door":
                    if(hasKey > 0){
                        gp.obj.set(i, null);
                        hasKey--;
                        gp.ui.showMessage("Unlocked a door");
                    }
                    //System.out.println("Key:" + hasKey);
                    else{
                        gp.ui.showMessage("You need a key");
                    }
                    break;

                     */
                case "Door":
                    if(hasKey == gp.aSetter.numKeys){
                        gp.ui.gameFinished = true;
                        gp.stopMusic();
                        gp.playSE(4);
                    }
                    else{
                        gp.ui.showMessage("You don't have enough keys");
                    }
                    break;
                case "Boots":
                    gp.playSE(2);
                    speed+=2;
                    gp.obj.set(i, null);
                    gp.ui.showMessage("Picked up a Speed Boots");
                    break;
                case "Chest":
                    gp.playSE(6);
                    gp.easter = true;
                    gp.obj.set(i, null);
//                    gp.ui.gameFinished = true;
//                    gp.stopMusic();
//                    gp.playSE(4);
                    break;
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
                    image = walkUp1;
                }
                if(spriteNum == 2){
                    image = walkUp2;
                }
                if(spriteNum == 3){
                    image = walkUp3;
                }
                if(spriteNum == 4){
                    image = walkUp4;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = walkDown1;
                }
                if(spriteNum == 2){
                    image = walkDown2;
                }
                if(spriteNum == 3){
                    image = walkDown3;
                }
                if(spriteNum == 4){
                    image = walkDown4;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = walkLeft1;
                }
                if(spriteNum == 2){
                    image = walkLeft2;
                }
                if(spriteNum == 3){
                    image = walkLeft3;
                }
                if(spriteNum == 4){
                    image = walkLeft4;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = walkRight1;
                }
                if(spriteNum == 2){
                    image = walkRight2;
                }
                if(spriteNum == 3){
                    image = walkRight3;
                }
                if(spriteNum == 4){
                    image = walkRight4;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

//        // SHOW COLLISION BOX FOR DEBUGGING
//        g2.setColor(Color.RED); // You can choose any color you like
//        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}

