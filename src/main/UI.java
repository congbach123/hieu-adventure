package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font arial_40, arial_80B, futura_40, futura_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public boolean loseCondition = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#");

    public Color colorBase = new Color(0xBDD2B6);
    public Color colorBorder = new Color(0x2C3639);
    public Color colorSpecial = new Color(0xF8EDE3);

    public UI(GamePanel gp){
        this.gp = gp;
/*
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        */
        futura_40 = new Font("futura", Font.PLAIN, 40);
        futura_80B = new Font("futura", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){

        if(gameFinished){

            g2.setFont(futura_40);

            String text;
            int textLength;
            int x,y;

            if(loseCondition){
                text = "You did not escape in time";
            }
            else {
                text = "You escaped after " + dFormat.format(playTime) + " seconds!";
            }
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth / 2 - textLength/2;
            y = gp.screenHeight / 2 - (gp.tileSize*3);

            g2.setColor(colorBorder);
            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    if (i != 0 || j != 0) {
                        g2.drawString(text, x + i, y + j);
                    }
                }
            }
            g2.setColor(colorBase);
            g2.drawString(text,x,y);

//            text = "You took "+ dFormat.format(playTime) + " seconds to escape.";
//            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//
//            x = gp.screenWidth / 2 - textLength/2;
//            y = gp.screenHeight / 2 - (gp.tileSize*4);
//            g2.drawString(text,x,y);

            g2.setFont(futura_80B);

            if(loseCondition){
                text = "GAME OVER";
            }
            else {
                text = "ESCAPED";
            }
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength/2;
            y = gp.screenHeight / 2 - gp.tileSize;

            g2.setColor(colorBorder);
            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    if (i != 0 || j != 0) {
                        g2.drawString(text, x + i, y + j);
                    }
                }
            }
            g2.setColor(colorSpecial);
            g2.drawString(text,x,y);

            gp.gameThread = null;
        }
        else {
            g2.setFont(futura_40);
            g2.setColor(colorBase);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            //CREATE BORDER AROUND TEXT
            g2.setColor(colorBorder);
            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    if (i != 0 || j != 0) {
                        g2.drawString("Key = " + gp.player.hasKey, 112 + i, 80 + j);
                    }
                }
            }
            g2.setColor(colorBase);
            g2.drawString("Key = " + gp.player.hasKey, 112, 80);

            playTime +=(double) 1/60;

            // LOSE SCREEN
            if(playTime >= 60 && gameFinished!=true){
                gameFinished = true;
                loseCondition = true;
            }

            g2.setColor(colorBorder);
            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    if (i != 0 || j != 0) {
                        g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*11 + i, 80 + j);
                    }
                }
            }
            g2.setColor(colorBase);
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*11, 80);
            //MESSAGE
            if(messageOn == true){
                g2.setFont(g2.getFont().deriveFont(30f));
                //g2.drawString(message, gp.screenWidth/2 - gp.tileSize, gp.screenHeight - gp.tileSize);

                int textLength = (int) g2.getFontMetrics().getStringBounds(message, g2).getWidth();
                int x = gp.screenWidth / 2 - textLength/2;
                int y = gp.screenHeight / 2 - (gp.tileSize*3);

                g2.setColor(colorBorder);
                for (int i = -2; i <= 2; i++) {
                    for (int j = -2; j <= 2; j++) {
                        if (i != 0 || j != 0) {
                            g2.drawString(message,x + i, y + j);
                        }
                    }
                }
                g2.setColor(colorBase);
                g2.drawString(message,x,y);

                messageCounter++;

                if(messageCounter > 120){
                    messageCounter =0;
                    messageOn = false;
                }
            }
        }

    }
}
